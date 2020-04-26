package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemBankcard;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBankcard;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.request.TqMemBankcard;
import com.rong.member.module.view.TvMemBankcard;
import com.rong.member.mapper.MemBankcardMapper;
import com.rong.member.service.MemBankcardService;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Component;

@Component
public class MemBankcardServiceImpl extends BasicServiceImpl<TbMemBankcard, TqMemBankcard, TvMemBankcard, MemBankcardMapper> implements MemBankcardService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("a.user_Name userName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"a"),
                                CompareAlias.valueOf(TsMemBankcard.Fields.memberId,"e")))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }
}