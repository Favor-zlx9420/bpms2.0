package com.rong.member.service;

import com.rong.common.service.BasicService;
import com.rong.member.module.entity.TbMemCompanyCreditfile;
import com.rong.member.module.request.TqMemCompanyCreditfile;
import com.rong.member.module.view.TvMemCompanyCreditfile;

public interface MemCompanyCreditfileService extends BasicService<TbMemCompanyCreditfile, TqMemCompanyCreditfile, TvMemCompanyCreditfile> {
    void updateForAuditCreditfile(TqMemCompanyCreditfile req);
}