package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.fundmanage.mapper.OrgProxyMapper;
import com.rong.fundmanage.module.entity.TbOrgProxy;
import com.rong.fundmanage.module.query.TsOrgProxy;
import com.rong.fundmanage.module.request.TqOrgProxy;
import com.rong.fundmanage.module.view.TvOrgProxy;
import com.rong.fundmanage.service.OrgProxyService;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.query.TsMdInstitution;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class OrgProxyServiceImpl extends BasicServiceImpl<TbOrgProxy, TqOrgProxy, TvOrgProxy, OrgProxyMapper> implements OrgProxyService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper(){
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("mb.userName,mb.realName,i.partyShortName,i.partyFullName,mb.position position,mb.nickName nickName,mb.headPortrait headPortrait")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"mb"),
                                CompareAlias.valueOf(TsOrgProxy.Fields.userId,"e")))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"i"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMdInstitution.Fields.partyId,"i"),
                                CompareAlias.valueOf(TsOrgProxy.Fields.partyId,"e")))
                ;
    }

}