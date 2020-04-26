package com.rong.assembly.api.service;

import com.rong.common.module.Result;
import com.rong.member.module.req.TqCheckVerificationCode;
import com.rong.member.module.req.TqGetVerificationCode;
import com.rong.member.module.resp.TmGetVerificationCode;

public interface TripartiteMessageService {
    TmGetVerificationCode getVerificationCode(TqGetVerificationCode req);
    Result<Boolean> isLegalVerificationCode(TqCheckVerificationCode req);
    Result removeVerificationCode(TqCheckVerificationCode req);
}
