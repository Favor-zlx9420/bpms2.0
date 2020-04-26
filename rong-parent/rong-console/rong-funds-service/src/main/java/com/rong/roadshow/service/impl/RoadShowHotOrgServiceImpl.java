package com.rong.roadshow.service.impl;

import com.rong.common.exception.DuplicateDataException;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.roadshow.mapper.RoadShowHotOrgMapper;
import com.rong.roadshow.module.entity.TbRoadShowHotOrg;
import com.rong.roadshow.module.query.TsRoadShowHotOrg;
import com.rong.roadshow.module.request.TqRoadShowHotOrg;
import com.rong.roadshow.module.view.TvRoadShowHotOrg;
import com.rong.roadshow.service.RoadShowHotOrgService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class RoadShowHotOrgServiceImpl extends BasicServiceImpl<TbRoadShowHotOrg, TqRoadShowHotOrg, TvRoadShowHotOrg, RoadShowHotOrgMapper> implements RoadShowHotOrgService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mi.partyShortName,mi.partyFullName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->
                        mi.eqc(CompareAlias.valueOf("e.partyId"),CompareAlias.valueOf("mi.partyId"))
                        )
                ;
    }

    @Override
    protected void beforeInsert(TqRoadShowHotOrg req) {
        super.beforeInsert(req);
        if(mapper.selectCount(new QueryWrapper()
            .eq(TsRoadShowHotOrg.Fields.partyId,req.getEntity().getPartyId())
        ) > 0){
            throw new DuplicateDataException("该机构已经存在，如果已经被删除，请使用恢复功能");
        }
    }
}