package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbMemCreditHistory;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.query.TsMemCreditHistory;
import com.rong.member.module.request.TqMemCreditHistory;
import com.rong.member.module.view.TvMemCreditHistory;
import com.rong.member.mapper.MemCreditHistoryMapper;
import com.rong.member.service.MemCreditHistoryService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Component;

@Component
public class MemCreditHistoryServiceImpl extends BasicServiceImpl<TbMemCreditHistory, TqMemCreditHistory, TvMemCreditHistory, MemCreditHistoryMapper> implements MemCreditHistoryService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("a.user_Name userName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"a"),
                                CompareAlias.valueOf(TsMemCreditHistory.Fields.memberId, ConstValue.MAIN_ALIAS)))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }
}