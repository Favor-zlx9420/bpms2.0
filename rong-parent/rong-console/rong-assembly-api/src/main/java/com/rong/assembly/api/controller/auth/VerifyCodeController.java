package com.rong.assembly.api.controller.auth;

import com.rong.assembly.api.service.TripartiteMessageService;
import com.rong.cache.service.SessionCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.member.module.req.TqGetVerificationCode;
import com.rong.member.module.resp.TmGetVerificationCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码相关
 */
@Api(tags = "获取验证码")
@RestController
@RequestMapping("verify/code/")
public class VerifyCodeController {
    @Autowired
    SessionCache sessionCache;
    private final TripartiteMessageService tripartiteMessageService;

    public VerifyCodeController(TripartiteMessageService tripartiteMessageService) {
        this.tripartiteMessageService = tripartiteMessageService;
    }
    /**
     * 获取手机验证码
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "获取手机验证码")
    @GetMapping("phone")
    public Result<TmGetVerificationCode> getPhoneSms(TqGetVerificationCode req) throws Exception {
        if (!CommonUtil.isPhone(req.getReceiver())) {
            throw new CustomerException("请输入正确的手机号码", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
        }
        req.setCodeType(CommonEnumContainer.TripartiteMessageCodeType.SMS_TEXT.getValue());
        return Result.success(getSms(req));
    }

    /**
     * 获取email验证码
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "获取email验证码")
    @GetMapping("email")
    public Result<TmGetVerificationCode> getEmailSms(TqGetVerificationCode req) {
        if (!CommonUtil.isEmail(req.getReceiver())) {
            throw new CustomerException("请输入正确的email", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
        }
        req.setCodeType(CommonEnumContainer.TripartiteMessageCodeType.EMAIL_TEXT.getValue());
        return Result.success(getSms(req));
    }


    /**
     * 获取验证码
     *
     * @param tq
     * @return
     */
    private TmGetVerificationCode getSms(TqGetVerificationCode tq) {
        String storageSession = sessionCache.getSession(tq.getApiToken(),String.class);
        if(null == storageSession || !CommonUtil.isEqual(storageSession.toLowerCase(),tq.getTicket().toLowerCase())){
            //throw new CustomerException("图形验证码校验失败", CommonEnumContainer.ResultStatus.Token无效);
        }
        if (CommonUtil.isNull(tq.getContentType())) {
            tq.setContentType(CommonEnumContainer.TripartiteMessageContentType.REGISTER.getValue());
        }
        return tripartiteMessageService.getVerificationCode(tq);
    }
}