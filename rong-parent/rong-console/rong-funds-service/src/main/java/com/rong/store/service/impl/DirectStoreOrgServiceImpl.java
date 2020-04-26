package com.rong.store.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.WrapperFactory;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.store.mapper.DirectStoreOrgLabelMapper;
import com.rong.store.mapper.DirectStoreOrgMapper;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import com.rong.store.module.query.TsDirectStoreOrg;
import com.rong.store.module.query.TsDirectStoreOrgLabel;
import com.rong.store.module.request.TqDirectStoreOrg;
import com.rong.store.module.view.TvDirectStoreOrg;
import com.rong.store.service.DirectStoreOrgLabelService;
import com.rong.store.service.DirectStoreOrgService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.query.TsMdInstitution;
import com.vitily.mybatis.core.entity.Element;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DirectStoreOrgServiceImpl extends BasicServiceImpl<TbDirectStoreOrg, TqDirectStoreOrg, TvDirectStoreOrg, DirectStoreOrgMapper> implements DirectStoreOrgService {
    @Autowired
    private DirectStoreOrgLabelMapper directStoreOrgLabelMapper;
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("mi.partyShortName partyShortName,mi.partyFullName partyFullName,mb.userName,mb.realName,mb.createDate regDate")
                .select0(
                        SelectAlias.valueOf("(select count(id)from tb_user_org_fav where party_id=e.party_id and deltag=0) favUserCount",true)
                        ,
                        SelectAlias.valueOf("(select end_date from tb_user_vip_end where user_id=e.user_id and deltag=0) vipEndDay",true)
                        ,
                        SelectAlias.valueOf("(select count(1) from tb_direct_store_user where party_id=e.party_id and deltag=0) customerUserCount",true)
                        ,
                        SelectAlias.valueOf("(select count(1) from tb_direct_store_user where party_id=e.party_id and state in(0,2) and deltag=0) unAuditCustomerUserCount",true)
                )
                .leftJoin(
                        ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMdInstitution.Fields.partyId,"mi"),
                                CompareAlias.valueOf(TsDirectStoreOrg.Fields.partyId,"e")
                        )
                )
                .leftJoin(
                        ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"mb"),
                                CompareAlias.valueOf(TsDirectStoreOrg.Fields.userId,"e")
                        )
                )
                ;
    }

    @Override
    protected void beforeInsert(TqDirectStoreOrg req) {
        super.beforeInsert(req);
    }

    @Override
    protected void afterInsert(TqDirectStoreOrg req) {
        super.afterInsert(req);
        if(!CollectionUtils.isEmpty(req.getLabelIds())){
            TbDirectStoreOrgLabel label = new TbDirectStoreOrgLabel();
            label.setPartyId(req.getEntity().getPartyId());
            label.setVisible(true);
            label.setCreateDate(new Date());
            label.setDeltag(false);
            for(Long labelId : req.getLabelIds()){
                label.setLabelId(labelId);
                directStoreOrgLabelMapper.insert(label);
            }
        }
    }

    @Override
    protected void afterUpdate(TqDirectStoreOrg req) {
        super.afterUpdate(req);
        if(null == req.getLabelIds()){
            return;
        }
        directStoreOrgLabelMapper.updateSelectItem(
                WrapperFactory.updateWrapper().update(
                        Elements.valueOf(TsDirectStoreOrgLabel.Fields.deltag,true)
                        ,
                        Elements.valueOf(TsDirectStoreOrgLabel.Fields.updateDate,new Date())
                )
                .eq(TsDirectStoreOrgLabel.Fields.partyId,req.getEntity().getPartyId())
        );
        if(!CollectionUtils.isEmpty(req.getLabelIds())){
            TbDirectStoreOrgLabel label = new TbDirectStoreOrgLabel();
            label.setPartyId(req.getEntity().getPartyId());
            label.setVisible(true);
            label.setCreateDate(new Date());
            label.setDeltag(false);
            for(Long labelId : req.getLabelIds()){
                if(directStoreOrgLabelMapper.updateSelectItem(
                        WrapperFactory.updateWrapper().update(
                                Elements.valueOf(TsDirectStoreOrgLabel.Fields.deltag,false)
                                ,
                                Elements.valueOf(TsDirectStoreOrgLabel.Fields.updateDate,new Date())
                        )
                                .eq(TsDirectStoreOrgLabel.Fields.partyId,req.getEntity().getPartyId())
                                .eq(TsDirectStoreOrgLabel.Fields.labelId,labelId)
                ) == 0){
                    label.setLabelId(labelId);
                    directStoreOrgLabelMapper.insert(label);
                }
            }
        }
    }
}