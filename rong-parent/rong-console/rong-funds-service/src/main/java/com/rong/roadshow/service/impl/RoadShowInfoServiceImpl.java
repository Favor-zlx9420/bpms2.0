package com.rong.roadshow.service.impl;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.member.module.entity.TbMemBase;
import com.rong.roadshow.mapper.RoadShowInfoMapper;
import com.rong.roadshow.module.entity.TbRoadShowCategory;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.query.TsRoadShowCategory;
import com.rong.roadshow.module.query.TsRoadShowInfo;
import com.rong.roadshow.module.request.TqRoadShowInfo;
import com.rong.roadshow.module.view.TvRoadShowInfo;
import com.rong.roadshow.service.RoadShowInfoService;
import com.rong.sys.mapper.MessageMapper;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.module.entity.TbMessage;
import com.rong.sys.module.query.TsLabel;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoadShowInfoServiceImpl extends BasicServiceImpl<TbRoadShowInfo, TqRoadShowInfo, TvRoadShowInfo, RoadShowInfoMapper> implements RoadShowInfoService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("l.name labelName,l0.name labelName0,c.name cateName,i.partyShortName orgName,i.legalRep")
                .select0(
                        SelectAlias.valueOf("(select count(id) from tb_road_show_info where org_id=e.org_id) as showCount",true)
                )
                .select0(
                        SelectAlias.valueOf("(case when e.from = 0 then mb.user_name else au.user_name end) uploadUserName",true)
                        ,
                        SelectAlias.valueOf("(case when e.from = 0 then mb.phone else au.phone end) uploadUserPhone",true)
                        ,
                        SelectAlias.valueOf("(case when e.from = 0 then mb.real_name else au.real_name end) uploadUserRealName",true)
                        ,
                        SelectAlias.valueOf("(select create_date from tb_comm_message where relation_id=e.id and type="+ CommonEnumContainer.MessageType.ROAD_SHOW_AUDIT_RESULTS.ordinal() +" order by id desc limit 1) rejectTime",true)
                        ,
                        SelectAlias.valueOf("(select content from tb_comm_message where relation_id=e.id and type="+ CommonEnumContainer.MessageType.ROAD_SHOW_AUDIT_RESULTS.ordinal() +" order by id desc limit 1) rejectDescription",true)
                 )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbLabel.class,"l"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsLabel.Fields.id,"l"),
                                CompareAlias.valueOf(TsRoadShowInfo.Fields.labelId,"e")))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbLabel.class,"l0"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsLabel.Fields.id,"l0"),
                                CompareAlias.valueOf(TsRoadShowInfo.Fields.labelId,"e")))

                .leftJoin(ClassAssociateTableInfo.valueOf(TbRoadShowCategory.class,"c"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsRoadShowCategory.Fields.id,"c"),
                                CompareAlias.valueOf(TsRoadShowInfo.Fields.cateId,"e")))

                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),
                        x->x.eqc(
                                CompareAlias.valueOf("mb.id"),
                                CompareAlias.valueOf("e.uploadUserId")))

                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"i"),
                        x->x.eqc(
                                CompareAlias.valueOf("i.partyId"),
                                CompareAlias.valueOf("e.orgId")))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbAdmUser.class,"au"),
                        x->x.eqc(
                                CompareAlias.valueOf("au.id"),
                                CompareAlias.valueOf("e.admUserId")))
                ;
    }

    @Override
    public int countOrgs() {
        return mapper.countOrgs();
    }


    @Override
    protected void afterUpdate(TqRoadShowInfo req) {
        super.afterUpdate(req);
        //拒绝理由
        if(CommonUtil.isEqual(req.getEntity().getState(), CommonEnumContainer.CustomerAuditState.NOT_APPROVED.getValue())){
            if(StringUtil.isEmpty(req.getRejectReason())){
                return;
            }
            TvRoadShowInfo roadShowInfo = mapper.selectOneView(getMultiCommonWrapper().eq(CompareAlias.valueOf("e.id"),req.getEntity().getId()));
            TbMessage message = new TbMessage();
            message.setTitle("路演审核结果通知");
            message.setContent(req.getRejectReason());
            message.setType(CommonEnumContainer.MessageType.ROAD_SHOW_AUDIT_RESULTS.ordinal());
            message.setViewState(CommonEnumContainer.MessageViewState.DID_NOT_SEE.ordinal());
            message.setContactor(roadShowInfo.getUploadUserRealName());
            message.setPhone(roadShowInfo.getUploadUserPhone());
            message.setState(CommonEnumContainer.State.NORMAL.getValue());
            message.setDeltag(false);
            message.setCreateDate(new Date());
            message.setMemberId(roadShowInfo.getUploadUserId());
            message.setRelationId(roadShowInfo.getId());
            messageMapper.insert(message);
        }
    }
}