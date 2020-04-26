package com.rong.assembly.api.controller.auth;

import com.rong.assembly.api.module.request.TqResetPassword;
import com.rong.assembly.api.module.request.TqVerify;
import com.rong.assembly.api.service.UserManagerService;
import com.rong.assembly.api.util.ValidateCode;
import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.CommonServiceCache;
import com.rong.cache.service.SessionCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.Result;
import com.rong.common.module.TqUserAuthBase;
import com.rong.common.module.UserInfo;
import com.rong.common.util.CommonUtil;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.req.TqLoginByCode;
import com.rong.member.module.req.TqMemLogin;
import com.rong.member.module.req.TqMemRegister;
import com.rong.member.module.view.TvMemBase;
import com.rong.member.service.MemBaseService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "用户注册登录、图形验证码")
@RequestMapping("auth")
@RestController
public class UserAuthController {
    @Autowired
    CommonServiceCache commonServiceCache;
    @Autowired
    private MemBaseService memBaseService;
    private final UserManagerService userManagerService;
    @Autowired
    private SessionCache sessionCache;
    @Autowired
    public UserAuthController( UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    /**
     * 注册
     * @param req see entity
     * @return result {@link com.rong.common.module.UserInfo}
     */
    @ApiOperation(value = "用户注册/验证码登录")
    @PostMapping("register")
    public Result<UserInfo<TvMemBase>> register(@RequestBody TqMemRegister req){
        return userManagerService.responseUserInfoResult(userManagerService.register(req));
    }
    /**
     * 手机号和验证码登录
     * @param req see entity
     * @return result {@link com.rong.common.module.UserInfo}
     */
    @ApiOperation(value = "手机号和验证码登录")
    @PostMapping("loginByCode")
    public Result<UserInfo<TvMemBase>> flogin(@RequestBody TqLoginByCode req){
        return userManagerService.responseUserInfoResult(userManagerService.loginByCode(req));
    }
    /**
     * 登录
     * @param req see entity
     * @return result {@link com.rong.common.module.UserInfo}
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result<UserInfo<TvMemBase>> login(@RequestBody TqMemLogin req){
        return
                userManagerService.responseUserInfoResult(userManagerService.login(req));
    }


    /**
     * 找回密码-提交
     * @param req
     * @return
     */
    @ApiOperation(value = "找回密码")
    @RequestMapping(value="resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(@RequestBody TqResetPassword req){
        if(!CommonUtil.isPhone(req.getReceiver())){
            throw new CustomerException("请输入正确的手机号码", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
        }
        return userManagerService.resetPassword(req);
    }
    /**
     * 退出
     * @param req
     * @return
     */
    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ResponseBody
    public Result logout(@RequestBody TqUserAuthBase req){
        CommonServiceCache memCache = commonServiceCache.getInstance(DictionaryKey.Keys.MEMBER_OF_THE_TOKEN, CacheSerializableDelegate.jsonSeriaze());
        UserInfo userInfo = memCache.getFromServer(req.getUserAuthToken(), UserInfo.class);
        if(null != userInfo){
            TbMemBase user = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(userInfo.getUserId(), TsMemBase.Fields.type));
            if(null != user){
                memCache.removeFromServer(user.getType()+"-" + userInfo.getUserId());
            }
        }
        memCache.removeFromServer(req.getUserAuthToken());
        return Result.success("退出登录成功");
    }
    /**
     * 获取图形验证码
     * @param req 1
     * @param response 2
     * @return result
     */
    @ApiOperation(value = "普通图形验证码")
    @GetMapping(value = "getImgVerify")
    public Result getImgVerify(TqVerify req, HttpServletResponse response)throws Exception{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        ValidateCode vCode = new ValidateCode(116,32, 4,40);
        sessionCache.setSession(req.getApiToken(),vCode.getCode());
        vCode.write(response.getOutputStream());
        return null;
    }

}
