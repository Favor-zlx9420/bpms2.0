package com.rong.store.service.impl;

import com.rong.common.exception.DuplicateDataException;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.WrapperFactory;
import com.rong.store.mapper.DirectStoreOrgLabelMapper;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import com.rong.store.module.query.TsDirectStoreOrgLabel;
import com.rong.store.module.request.TqDirectStoreOrgLabel;
import com.rong.store.module.view.TvDirectStoreOrgLabel;
import com.rong.store.service.DirectStoreOrgLabelService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class DirectStoreOrgLabelServiceImpl extends BasicServiceImpl<TbDirectStoreOrgLabel, TqDirectStoreOrgLabel, TvDirectStoreOrgLabel, DirectStoreOrgLabelMapper> implements DirectStoreOrgLabelService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mi.partyShortName,mi.partyFullName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"),mi->
                        mi.eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("e.partyId"))
                )
                ;
    }

    @Override
    protected void beforeInsert(TqDirectStoreOrgLabel req) {
        TbDirectStoreOrgLabel entity = req.getEntity();
        if(mapper.selectCount(
                WrapperFactory.queryWrapper()
                        .eq(TsDirectStoreOrgLabel.Fields.partyId,entity.getPartyId())
                        .eq(TsDirectStoreOrgLabel.Fields.labelId,entity.getLabelId())
        ) > 0){
            throw new DuplicateDataException("已有该产品对应标签，请检查！");
        }
        super.beforeInsert(req);
    }
}