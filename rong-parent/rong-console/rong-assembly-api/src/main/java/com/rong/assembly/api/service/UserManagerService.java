package com.rong.assembly.api.service;

import com.rong.assembly.api.module.request.TqResetPassword;
import com.rong.assembly.api.module.response.user.TrUserFundAccountIndex;
import com.rong.common.module.Result;
import com.rong.member.module.req.TqLoginByCode;
import com.rong.member.module.req.TqMemLogin;
import com.rong.member.module.req.TqMemRegister;

public interface UserManagerService {
    Result register(TqMemRegister req);
    Result loginByCode(TqLoginByCode req);
    Result login(TqMemLogin req);
    Result resetPassword(TqResetPassword req);
    TrUserFundAccountIndex selectSumFundIndex(Long userId);
    Result responseUserInfoResult(Result result);
}
