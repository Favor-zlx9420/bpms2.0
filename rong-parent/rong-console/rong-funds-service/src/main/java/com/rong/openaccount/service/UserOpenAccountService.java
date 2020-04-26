package com.rong.openaccount.service;

import com.rong.common.module.Result;
import com.rong.common.service.BasicService;
import com.rong.openaccount.module.entity.TbUserOpenAccount;
import com.rong.openaccount.module.request.TqUserOpenAccount;
import com.rong.openaccount.module.view.TvUserOpenAccount;

public interface UserOpenAccountService {

    /**
     * 调用通联进行开户(获取验证码)
     * @param req
     */
    Result invokeTongOpenAccountGetVerCode(TqUserOpenAccount req);

    /**
     * 调用通联进行开户(上送验证码)
     * @param req
     */
    Result invokeTongOpenAccountUpSendVerCode(TqUserOpenAccount req);

    /**
     * 查询用户开户信息
     * @param req
     */
    Result selectUserOpenAccountInfoByUserId(TqUserOpenAccount req);
}