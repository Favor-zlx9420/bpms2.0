package com.rong.store.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemBase;
import com.rong.store.mapper.DirectStoreDesignMapper;
import com.rong.store.module.entity.TbDirectStoreDesign;
import com.rong.store.module.request.TqDirectStoreDesign;
import com.rong.store.module.view.TvDirectStoreDesign;
import com.rong.store.service.DirectStoreDesignService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class DirectStoreDesignServiceImpl extends BasicServiceImpl<TbDirectStoreDesign, TqDirectStoreDesign, TvDirectStoreDesign, DirectStoreDesignMapper> implements DirectStoreDesignService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mb.userName appUserName,mb.realName appRealName,md.partyShortName,md.partyFullName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"md"),md->
                        md.eqc(CompareAlias.valueOf("e.partyId"),CompareAlias.valueOf("md.partyId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),mb->
                        mb.eqc(CompareAlias.valueOf("e.appUserId"),CompareAlias.valueOf("mb.id"))
                        )
                ;
    }
}