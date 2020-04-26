package com.rong.assembly.api.service.impl;

import com.rong.assembly.api.service.SmsHelper;
import com.rong.assembly.api.service.TripartiteMessageService;
import com.rong.cache.foreign.SMSVerifyCodeFrequent;
import com.rong.cache.service.CommonServiceCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.cache.service.SMSVerifyFrequentCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.DateUtil;
import com.rong.common.util.EnumHelperUtil;
import com.rong.common.util.NumberUtil;
import com.rong.common.util.StringUtil;
import com.rong.member.mapper.MemBaseMapper;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.req.TqCheckVerificationCode;
import com.rong.member.module.req.TqGetVerificationCode;
import com.rong.member.module.resp.TmGetVerificationCode;
import com.rong.user.mapper.MessageHistoryMapper;
import com.rong.user.module.entity.TbMessageHistory;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TripartiteMessageServiceImpl implements TripartiteMessageService {

    private final MemBaseMapper memBaseMapper;
    private final DictionaryCache dictionaryCache;
    private final SMSVerifyFrequentCache smsVerifyFrequentCache;
    private final CommonServiceCache commonServiceCache;
    @Autowired
    private MessageHistoryMapper messageHistoryMapper;
    @Autowired
    SmsHelper smsHelper;

    @Autowired
    public TripartiteMessageServiceImpl(MemBaseMapper memBaseMapper, DictionaryCache dictionaryCache, SMSVerifyFrequentCache smsVerifyFrequentCache, CommonServiceCache commonServiceCache) {
        this.memBaseMapper = memBaseMapper;
        this.dictionaryCache = dictionaryCache;
        this.smsVerifyFrequentCache = smsVerifyFrequentCache;
        this.commonServiceCache = commonServiceCache;
    }

    /**
     * 获取验证码
     */
    @Override
    public TmGetVerificationCode getVerificationCode(TqGetVerificationCode req) {
        if (CommonUtil.isNull(req.getContentType())) {
            req.setContentType(CommonEnumContainer.TripartiteMessageContentType.REGISTER.getValue());
        }
        //业务检查
        checkVerificationCodeService(req);
        //产生验证码 todo 将该值填充到字典配置中
        String verificationCode = StringUtil.getRandomNumber(4);

        //发送
        CommonEnumContainer.TripartiteMessageContentType contentType = EnumHelperUtil.getByValue(CommonEnumContainer.TripartiteMessageContentType.class, req.getContentType());
        sendVerificationCode(req.getReceiver(),
                contentType, EnumHelperUtil.getByValue(CommonEnumContainer.TripartiteMessageCodeType.class, req.getCodeType()),
                verificationCode
        );


        //是否返回短信内容
        TmGetVerificationCode resp = new TmGetVerificationCode();
        if (CommonUtil.booleanOf(dictionaryCache.getValue(DictionaryKey.Keys.WHETHER_THE_MESSAGE_IS_DISPLAYED_AT_THE_FRONT_DESK.getValue()))) {
            resp.setContent(verificationCode);
        } else {
            resp.setContent("验证码发送成功，请注意查收");
            log.warn("send verifycode:" + verificationCode);
        }
        Integer seconds = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.SEND_CAPTCHA_TIME_INTERVAL.getValue()));
        resp.setSeconds(seconds);//
        return resp;
    }

    @Override
    public Result isLegalVerificationCode(TqCheckVerificationCode req) {

        String cacheKey = req.getReceiver() + "_" + req.getContentType();
        SMSVerifyCodeFrequent exitsEntity = smsVerifyFrequentCache.getFromServer(cacheKey, SMSVerifyCodeFrequent.class);
        if (CommonUtil.isNull(exitsEntity)) {
            return Result.miss(CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION,"验证码不存在");
        }

        //获取每天做多可以输入多少次验证码
        Integer maxCodeErrorCount = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.NUMBER_OF_CAPTCHA_ATTEMPTS_PER_DAY.getValue()));

        if (maxCodeErrorCount != null && maxCodeErrorCount <= exitsEntity.getErrorCount()) {
            return Result.miss(CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION,"您今天已尝试多次输入验证码，请明天此时再做校验");
        }
        if (!CommonUtil.isEqual(req.getVerificationCode(), exitsEntity.getContent())) {
            smsVerifyFrequentCache.upErrorCount(cacheKey);
            return Result.miss(CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION,"验证码校验失败，请重新输入");
        }
        //如果超时了：

        Integer lastSendSMSTime = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.SEND_CAPTCHA_TIME_INTERVAL.getValue()));
        Long restMils = exitsEntity.getMillTime() + lastSendSMSTime * 1000 - System.currentTimeMillis();
        if (restMils <= 0) {
            return Result.miss(CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION,"验证码已经超时，请重新获取",false);
        }
        return Result.success();
    }

    @Override
    public Result<Boolean> removeVerificationCode(TqCheckVerificationCode req) {
        String cacheKey = req.getReceiver() + "_" + req.getContentType();
        smsVerifyFrequentCache.removeFromServer(cacheKey);
        return Result.success();
    }

    /**
     * 校验发送验证码业务类型
     *
     * @param req
     * @
     */
    private void checkVerificationCodeService(TqGetVerificationCode req) {
        CommonEnumContainer.TripartiteMessageContentType contentType = EnumHelperUtil.getByValue(CommonEnumContainer.TripartiteMessageContentType.class, req.getContentType());
        switch (contentType) {
            case REGISTER://需要数据库中不存在     不需要登录：注意客户端不要让用户多次尝试
            {
                // todo 用户注册即算登录啦 2020年1月17日 17:58:45
//                if(memBaseMapper.selectOne(new QueryWrapper<TbMemBase>()
//                        .eq(TsMemBase.Fields.userName,req.getReceiver())
//                        .select(TsMemBase.Fields.id)
//                ) != null){
//                    throw new CustomerException("该号码已被注册，请使用另外一个号码", CommonEnumContainer.ResultStatus.无权限);
//                }
                break;
            }
            case CANCEL_MOBILE_PHONE_NUMBER://需要登录，需要有手机号码
            case AUTHENTICATION:// 信息设置:////需要数据库中存在手机  －－ 需要登录
            {
                break;
            }
            case RETRIEVE_PASSWORD:////
            {
                if(memBaseMapper.selectOne(new QueryWrapper()
                        .eq(TsMemBase.Fields.userName,req.getReceiver())
                        .select(TsMemBase.Fields.id)
                ) == null){
                    throw new NoExistsException("输入的号码不存在，请检查.");
                }
                break;
            }

            default:
                break;
        }
    }

    /**
     * @param receiver    receiver
     * @param contentType 业务类型
     * @param content     内容
     * @return
     */
    private void sendVerificationCode(String receiver, CommonEnumContainer.TripartiteMessageContentType contentType, CommonEnumContainer.TripartiteMessageCodeType codeType, String content) {
        //查看缓存是否存在
        String cacheKey = receiver + "_" + contentType.getValue();
        SMSVerifyCodeFrequent exitsEntity = smsVerifyFrequentCache.getFromServer(cacheKey, SMSVerifyCodeFrequent.class);
        if (exitsEntity != null) {
            //今日是否已尝试验证码匹配次数？：在验证的时候做判断就是
            Integer maxCodeErrorCount = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.NUMBER_OF_CAPTCHA_ATTEMPTS_PER_DAY.getValue()));
            if (maxCodeErrorCount != null && maxCodeErrorCount <= exitsEntity.getErrorCount()) {
                Long restMils = exitsEntity.getMillTime() - System.currentTimeMillis() + 86400000;
                throw new CustomerException("您今天已尝试多次输入验证码，请 " + DateUtil.getRestTime(restMils) + "后再尝试", CommonEnumContainer.ResultStatus.PAGE_REQUEST_EXCEPTION);
            }

            //调用次数是否已到极限？
            //每天发送验证码次数
            Integer maxSendSMSCount = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.MEMBERS_CAN_SEND_SMS_NUMBER_OF_TIMES_PER_DAY.getValue()));
            // null 的话就是5次
            if (maxSendSMSCount != null && maxSendSMSCount <= exitsEntity.getCount()) {
                Long restMils = exitsEntity.getMillTime() - System.currentTimeMillis() + 86400000;
                throw new CustomerException("您今天已尝试发送短信多次，请 " + DateUtil.getRestTime(restMils) + "后再尝试", CommonEnumContainer.ResultStatus.PAGE_REQUEST_EXCEPTION);
            }

            //是否已到达间隔？：单位：秒
            Integer lastSendSMSTime = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.SEND_CAPTCHA_TIME_INTERVAL.getValue()));
            Long restMils = exitsEntity.getMillTime() + lastSendSMSTime * 1000 - System.currentTimeMillis();
            if (restMils >= 0) {
                //计算剩余分钟数：粗略即可
                //restMils／(1000*60)
                throw new CustomerException("请等待 " + DateUtil.getRestTime(restMils) + " 后再尝试发送信息", CommonEnumContainer.ResultStatus.PAGE_REQUEST_EXCEPTION);
            }
        }

        //发送短信
        sendMessage(receiver, contentType, codeType, content, dictionaryCache);
        smsVerifyFrequentCache.setToServer(cacheKey, content);
    }

    /**
     * 发送消息
     *
     * @param receiver
     * @param contentType ：内容类型
     * @param codeType    ： 短信类型
     * @param content
     * @return
     * @
     */
    private void sendMessage(String receiver, CommonEnumContainer.TripartiteMessageContentType contentType, CommonEnumContainer.TripartiteMessageCodeType codeType, String content, DictionaryCache dictionaryCache){
        //发送消息
        //插入数据库
        TbMessageHistory history = new TbMessageHistory();
        history.setCodeType(codeType.getValue());
        history.setContentType(contentType.getValue());
        history.setContent(content);
        history.setDeltag(false);
        history.setCreateDate(new Date());
        history.setTarget(receiver);
        //发送还是测试
        Integer isSend = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.WHETHER_IT_IS_TRUE_TO_SEND_SHORT_MESSAGES.getValue()));
        if (!CommonUtil.isEqual(isSend, 1)) {
            history.setState(-1);
            messageHistoryMapper.insert(history);
            return;//不发送，直接返回
        }
        CustomerException e = null;
        switch (codeType) {
            case SMS_TEXT:
            case SMS_VOICE:
            case SMS_GRAPHIC_CODE:
                switch (contentType) {
                    case REGISTER:
                    case RETRIEVE_PASSWORD:
                        Result result = smsHelper.send(contentType, receiver, content);
                        if (!result.isSucceed()) {
                            e = new CustomerException(result);
                        }
                        history.setState(result.isSucceed() ? CommonEnumContainer.State.NORMAL.getValue(): CommonEnumContainer.State.INVALID.getValue());
                        break;
                    case VERIFY_PHONE_NUMBER:
                        break;
                    default:
                        break;
                }
                break;
            case EMAIL_TEXT:
            case EMAIL_VOICE:
            case EMAIL_GRAPHICS_CODE:
                switch (contentType) {
                    case REGISTER:
                        //sendState = emailImpl.sendRegisterCode(receiver, content);
                        break;
                    case RETRIEVE_PASSWORD:
                        //sendState = emailImpl.sendFindPasswordCode(receiver, content);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        messageHistoryMapper.insert(history);
        if(e != null){
            throw e;
        }
    }

}