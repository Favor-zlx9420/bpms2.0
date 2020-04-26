package com.rong.assembly.api.controller.openaccount;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.Result;
import com.rong.common.util.StringUtil;
import com.rong.openaccount.module.request.TqUserOpenAccount;
import com.rong.openaccount.service.UserOpenAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户开户
 * 用户开户分为两步：1：获取由通联机构下发的验证码
 *  2：上送通联机构下发的验证码完成开户
 *
 * @author zhanglei
 * @date 2020-04-17
 */
@Api(tags = "用户开户")
@RestController
@RequestMapping("user/openAccount")
public class OpenAccountController {

    @Autowired
    private UserOpenAccountService userOpenAccountService;

    /**
     * 1：获取由通联机构下发的验证码
     * @param req
     * @return
     */
    @ApiOperation(value = "用户开户时获取验证码")
    @PostMapping("userOpenAccountGetVerCode")
    public Result userOpenAccountGetVerCode(@RequestBody TqUserOpenAccount req) {
        /**
         * 校验用户开户请求参数
         */
        if (null == req || StringUtil.isEmpty(req.getUserName()) || StringUtil.isEmpty(req.getIdNum()) || StringUtil.isEmpty(req.getCardNum()) || StringUtil.isEmpty(req.getCardOrg()) || StringUtil.isEmpty(req.getPhoneNum())) {
            throw new CustomerException("用户开户，请求参数校验不通过", CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE);
        }

        // 调用通联机构进行开户(获取验证码)
        return userOpenAccountService.invokeTongOpenAccountGetVerCode(req);
    }

    /**
     * 2：上送通联机构下发的验证码完成开户
     * @param req
     * @return
     */
    @ApiOperation(value = "用户开户时上送验证码")
    @PostMapping("userOpenAccountUpSendVerCode")
    public Result userOpenAccountUpSendVerCode(@RequestBody TqUserOpenAccount req) {
        /**
         * 校验用户开户请求参数
         */
        if (null == req || StringUtil.isEmpty(req.getVerCode()) ) {
            throw new CustomerException("用户开户，请求参数校验不通过", CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE);
        }

        // 调用通联机构进行开户（上送验证码成功，则开户成功）
        return userOpenAccountService.invokeTongOpenAccountUpSendVerCode(req);
    }

    /**
     * 开户成功，部分页面走通联H5页面（暂定，后期走api）
     */
    @ApiOperation(value = "跳转通联支付H5页面")
    @PostMapping("skipAllinpayH5Page")
    public Result skipAllinpayH5Page(@RequestBody TqUserOpenAccount req) {
        /**
         * 校验用户H5认证的请求参数
         */
        if (null == req || StringUtil.isEmpty(req.getFlag()) || null == req.getUserId() ) {
            throw new CustomerException("通联H5认证，请求参数校验不通过", CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE);
        }

        // 查询用户开户信息
        return userOpenAccountService.selectUserOpenAccountInfoByUserId(req);
    }

}
