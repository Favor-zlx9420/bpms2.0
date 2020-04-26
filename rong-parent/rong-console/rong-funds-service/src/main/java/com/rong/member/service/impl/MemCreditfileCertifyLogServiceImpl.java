package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbMemCreditfileCertifyLog;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.query.TsMemCreditfileCertifyLog;
import com.rong.member.module.request.TqMemCreditfileCertifyLog;
import com.rong.member.module.view.TvMemCreditfileCertifyLog;
import com.rong.member.mapper.MemCreditfileCertifyLogMapper;
import com.rong.member.service.MemCreditfileCertifyLogService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Component;

@Component
public class MemCreditfileCertifyLogServiceImpl extends BasicServiceImpl<TbMemCreditfileCertifyLog, TqMemCreditfileCertifyLog, TvMemCreditfileCertifyLog, MemCreditfileCertifyLogMapper> implements MemCreditfileCertifyLogService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("a.user_Name userName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"a"),
                                CompareAlias.valueOf(TsMemCreditfileCertifyLog.Fields.memberId, ConstValue.MAIN_ALIAS)))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }
}