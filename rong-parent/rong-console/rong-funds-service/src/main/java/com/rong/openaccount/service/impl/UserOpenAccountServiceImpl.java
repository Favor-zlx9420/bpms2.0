package com.rong.openaccount.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hitrust.hap.exception.HapException;
import com.mind.platform.system.base.CMData;
import com.mind.platform.system.base.DataRow;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.TongLianCode;
import com.rong.common.module.Result;
import com.rong.common.util.AllinpayH5Auth;
import com.rong.common.util.DateUtil;
import com.rong.common.util.GetRandomNumber;
import com.rong.common.util.HttpCommon;
import com.rong.openaccount.mapper.UserOpenAccountMapper;
import com.rong.openaccount.module.entity.TbUserOpenAccount;
import com.rong.openaccount.module.query.TsUserOpenAccount;
import com.rong.openaccount.module.request.TqUserOpenAccount;
import com.rong.openaccount.service.UserOpenAccountService;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import encryption.DataGramB2cUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class UserOpenAccountServiceImpl implements UserOpenAccountService {

    @Value("${allinpay.api.url}")
    private String allinpayApiUrl;
    @Value("${allinpay.h5.auth.url}")
    private String allinpayH5AuthUrl;
    @Value("${allinpay.h5.auth.pub.key}")
    private String allinPayH5AuthPubKey;

    @Resource
    UserOpenAccountMapper userOpenAccountMapper;

    /**
     * 通联开户做两步操作，1：调用通联机构获取验证码，2：验证验证码，验证成功，则开户成功！
     *
     * @param req
     */
    @Override
    public Result invokeTongOpenAccountGetVerCode(TqUserOpenAccount req) {
        log.info("调用通联机构获取验证码开始......用户ID：" + req.getUserId());
        // 组装请求通联的请求参数
        DataRow transactionDataRow = new CMData();
        DataRow headListRow = new CMData(), requestListRow = new CMData();
        headListRow.put("processing_code", TongLianCode.ProcessingCode.open_account.getValue());
        headListRow.put("inst_id", TongLianCode.inst_id);
        // 生成当前交易时间
        String transDate = DateUtil.dateToDateString(new Date(), DateUtil.yyyyMMddHHmmss);
        headListRow.put("trans_date", transDate.substring(0,8));
        headListRow.put("trans_time", transDate.substring(8,14));
        headListRow.put("ver_num", TongLianCode.ver_num);
        // REQUEST

        // 供应商产品账户，默认开通，传2
        requestListRow.put("prod_flag", "2");
        // 供应商编码 prod_flag = 2 时，必传
        requestListRow.put("supply_inst_code", TongLianCode.supply_inst_code);

        // 交易流水号：规则：时间戳17位 + 随机数（数字字母组合15位）= 32位流水号
        String reqTraceNum = new StringBuilder(DateUtil.dateToDateString(new Date(), DateUtil.yyyyMMddHHmmssSSS)).append(GetRandomNumber.getItemID(15)).toString();
        requestListRow.put("req_trace_num", reqTraceNum);
        // 签约类型
        requestListRow.put("sign_type", TongLianCode.SignType.shortcut_sign.getValue());
        // 银行编码
        requestListRow.put("bnk_id", TongLianCode.bank_id);
        // 账户类型：1：借记卡，6：对公账户
        requestListRow.put("acct_type", "1");
        // 卡号
        requestListRow.put("acct_num", req.getCardNum());
        // 用户名
        requestListRow.put("hld_name", req.getUserName());
        // 手机号码
        requestListRow.put("tel_num", req.getPhoneNum());
        // 证件类型
        requestListRow.put("cer_type", "01");
        // 证件号码
        requestListRow.put("cer_num", req.getIdNum());
        transactionDataRow.put("head", headListRow);
        transactionDataRow.put("request", requestListRow);

        /**
         * 报文请求对象 组装报文，签名，加密
         */
        log.info("输出通联机构获取验证码的请求报文：" + JSONObject.toJSONString(transactionDataRow));
        String msg = null;
        DataRow agreementSignDataRow = null;
        try {
            msg = DataGramB2cUtil.createRequestJrQianyueCryptoMsg(transactionDataRow);
            /**
             * 发送加密的报文请求通联
             */
            String resultHtml = HttpCommon.sendHttpReq(allinpayApiUrl, msg);

            /**
             * 解密通联响应返回的加密串
             */
            agreementSignDataRow = DataGramB2cUtil.parseResponseJrQianyueCryptoMsg(resultHtml);
        } catch (HapException e) {
            e.printStackTrace();
        }

        JSONObject jsonObj = JSON.parseObject(JSON.parseObject(JSONObject.toJSONString(agreementSignDataRow.getAttrValues().get("response"))).getString("attrValues"));

        log.info("输出通联机构获取验证码的响应报文：" + jsonObj.toString());

        if(jsonObj.get("resp_code").toString().equals("1112")){
            // 返回码为1112时，说明通联机构已下发短信验证码给用户，先存入用户开户记录
            TbUserOpenAccount info = new TbUserOpenAccount();
            info.setUserId(req.getUserId());
            info.setUserName(req.getUserName());
            info.setPhoneNum(req.getPhoneNum());
            info.setCardNum(req.getCardNum());
            info.setCardOrg(req.getCardOrg());
            info.setIdNum(req.getIdNum());
            try {
                info.setOpenAccountDate(new SimpleDateFormat(DateUtil.yyyyMMddHHmmss).parse(transDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            info.setReqTraceNum(reqTraceNum);
            info.setOpenAccountStatus(TongLianCode.openAccountStatus.open_account_wait.getValue());
            info.setCreateDate(new Date());
            userOpenAccountMapper.insert(info);
            log.info("调用通联机构获取验证码结束，并写入响应开户数据成功（开户状态为1，待验证码上送验证通过后，更新状态为0）！用户ID：" + req.getUserId());
            return Result.success();
        } else {
            String respMsg = jsonObj.get("resp_msg").toString();
            log.info("调用通联机构获取验证码失败！用户ID：" + req.getUserId(),"，失败原因：" + respMsg);
            return Result.miss(CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION, respMsg);
        }
    }

    /**
     * 通联开户做两步操作，1：调用通联机构获取验证码，2：验证验证码，验证成功，则开户成功！
     *
     * @param req
     */
    @Override
    public Result invokeTongOpenAccountUpSendVerCode(TqUserOpenAccount req) {
        log.info("调用通联机构上送验证码开始......用户ID：" + req.getUserId());
        // 查询用户开户信息
        TbUserOpenAccount info = userOpenAccountMapper.selectOne(new QueryWrapper().eq(TsUserOpenAccount.Fields.userId, req.getUserId()));
        if(null != info){
            // 组装请求通联的请求参数
            DataRow transactionDataRow = new CMData();
            DataRow headListRow = new CMData(), requestListRow = new CMData();
            headListRow.put("processing_code", TongLianCode.ProcessingCode.verify_code.getValue());
            headListRow.put("inst_id", TongLianCode.inst_id);
            headListRow.put("trans_date", DateUtil.dateToDateString(new Date(), DateUtil.yyyyMMdd_EN));
            headListRow.put("trans_time", DateUtil.dateToDateString(new Date(), DateUtil.HHmmss));
            headListRow.put("ver_num", TongLianCode.ver_num);
            // REQUEST
            // 交易流水号：规则：时间戳17位 + 随机数（数字字母组合15位）= 32位流水号
            String reqTraceNum = new StringBuilder(DateUtil.dateToDateString(new Date(), DateUtil.yyyyMMddHHmmssSSS)).append(GetRandomNumber.getItemID(15)).toString();
            requestListRow.put("req_trace_num", reqTraceNum);
            requestListRow.put("org_processing_code", TongLianCode.ProcessingCode.open_account.getValue());
            requestListRow.put("org_req_trace_num", info.getReqTraceNum());
            requestListRow.put("org_trans_date", new SimpleDateFormat(DateUtil.yyyyMMdd_EN).format(info.getOpenAccountDate()));
            requestListRow.put("verify_code", req.getVerCode());
            transactionDataRow.put("head", headListRow);
            transactionDataRow.put("request", requestListRow);

            /**
             * 报文请求对象 组装报文，签名，加密
             */
            log.info("输出上送验证码到通联机构的请求报文：" + JSONObject.toJSONString(transactionDataRow));
            String msg = null;
            DataRow agreementSignDataRow = null;
            try {
                msg = DataGramB2cUtil.createRequestMsgVerifyCryptoMsg(transactionDataRow);
                /**
                 * 发送加密的报文请求通联
                 */
                String resultHtml = HttpCommon.sendHttpReq(allinpayApiUrl, msg);

                /**
                 * 解密通联响应返回的加密串
                 */
                agreementSignDataRow = DataGramB2cUtil.parseResponseMsgVerifyCryptoMsg(resultHtml);
            } catch (HapException e) {
                e.printStackTrace();
            }

            JSONObject jsonObj = JSON.parseObject(JSON.parseObject(JSONObject.toJSONString(agreementSignDataRow.getAttrValues().get("response"))).getString("attrValues"));

            log.info("输出上送验证码到通联机构的响应报文：" + jsonObj.toString());

            if(jsonObj.get("resp_code").toString().equals("0000")){
                // 返回码为0000时，说明通联机构开户成功，将开户状态更新
                info.setSignNum(jsonObj.get("sign_num").toString());
                info.setOpenAccountStatus(TongLianCode.openAccountStatus.open_account_success.getValue());
                info.setOpenAccountDate(new Date());
                info.setUpdateDate(new Date());
                userOpenAccountMapper.updateByPrimary(info);
                log.info("用户ID：{}开户成功，并更新写入数据库开户状态以及通联会员号成功！", info.getUserId());
                return Result.success();
            } else {
                String respMsg = jsonObj.get("resp_msg").toString();
                log.info("用户ID：{}，开户失败，失败原因：{}", info.getUserId(), respMsg);
                return Result.miss(CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION, respMsg);
            }
        }
        log.info("用户ID：{}，数据库没有发送验证码时写入的开户信息！", req.getUserId());
        return Result.miss(CommonEnumContainer.ResultStatus.THE_USER_DOES_NOT_EXIST, "用户userId：" + req.getUserId() + "没有开户记录数据");
    }

    /**
     * 根据用户ID 查询用户开户成功信息
     * @param userId
     * @return
     */
    @Override
    public Result selectUserOpenAccountInfoByUserId(TqUserOpenAccount req) {
        TbUserOpenAccount info = userOpenAccountMapper.selectOne(new QueryWrapper().eq(TsUserOpenAccount.Fields.userId, req.getUserId()).eq(TsUserOpenAccount.Fields.openAccountStatus, TongLianCode.openAccountStatus.open_account_success.getValue()));
        // 确保用户有开户成功的记录
        if(null == info){
            return Result.miss(CommonEnumContainer.ResultStatus.USER_NOT_OPEN_ACCOUNT);
        }
        /**
         * 用户已开户，调用通联H5完成认证
         * 现处理方式：返回需要访问通联H5的url给前端，由前端直接调用通联H5页面
          */
        String linkUrlStr = AllinpayH5Auth.invokeAllinpayH5Auth(allinpayH5AuthUrl, allinPayH5AuthPubKey, req, info);
        return Result.success(linkUrlStr);
    }

}