package com.rong.member.service;

import com.rong.common.service.BasicService;
import com.rong.member.module.entity.TbMemPersonalCreditfile;
import com.rong.member.module.request.TqMemPersonalCreditfile;
import com.rong.member.module.view.TvMemPersonalCreditfile;

public interface MemPersonalCreditfileService extends BasicService<TbMemPersonalCreditfile, TqMemPersonalCreditfile, TvMemPersonalCreditfile> {
    void updateForAuditCreditfile(TqMemPersonalCreditfile req);
}