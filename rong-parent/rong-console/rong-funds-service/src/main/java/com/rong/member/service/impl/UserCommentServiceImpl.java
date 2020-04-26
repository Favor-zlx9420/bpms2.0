package com.rong.member.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.member.mapper.UserCommentMapper;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbUserComment;
import com.rong.member.module.request.TqUserComment;
import com.rong.member.module.view.TvUserComment;
import com.rong.member.service.UserCommentService;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.sys.mapper.MessageMapper;
import com.rong.sys.module.entity.TbMessage;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCommentServiceImpl extends BasicServiceImpl<TbUserComment, TqUserComment, TvUserComment, UserCommentMapper> implements UserCommentService {
    @Autowired
    private MessageMapper messageMapper;
    private static MultiTableQueryWrapper multiTableQueryWrapper(){
        return WrapperFactory.multiQueryWrapper()
                .selectAllFiels(true)
                .select("mb.realName,mb.headPortrait,mb.userName commentUserName")
                .select0(
                        SelectAlias.valueOf("case when e.type = 0 then mi.party_short_name when e.type = 1 then mp.name when e.type=2 then ms.sec_short_name else ri.title end targetInfo",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),mb->
                        mb.eqc(CompareAlias.valueOf("e.commentUserId"),CompareAlias.valueOf("mb.id"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->
                        mi.eqc(CompareAlias.valueOf("e.targetId"),CompareAlias.valueOf("mi.partyId"))
                                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.CommentType.ORGANIZATION.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdPeople.class,"mp"), mp->
                        mp.eqc(CompareAlias.valueOf("e.targetId"),CompareAlias.valueOf("mp.personId"))
                                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.CommentType.FUND_MANAGER.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"ms"), ms->
                        ms.eqc(CompareAlias.valueOf("e.targetId"),CompareAlias.valueOf("ms.securityId"))
                                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.CommentType.PRODUCT.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRoadShowInfo.class,"ri"), mb->
                        mb.eqc(CompareAlias.valueOf("e.targetId"),CompareAlias.valueOf("ri.id"))
                                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.CommentType.ROAD_SHOW.getValue())
                )
                ;
    }
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return multiTableQueryWrapper();
    }

    @Override
    protected void afterUpdate(TqUserComment req) {
        super.afterUpdate(req);
        //拒绝理由
        if(CommonUtil.isEqual(req.getEntity().getAuditResult(), CommonEnumContainer.CustomerAuditState.NOT_APPROVED.getValue())){
            if(StringUtil.isEmpty(req.getRejectReason())){
                return;
            }
            TvUserComment comment = mapper.selectOneView(multiTableQueryWrapper().eq(CompareAlias.valueOf("e.id"),req.getEntity().getId()));
            TbMessage message = new TbMessage();
            message.setTitle("评论审核结果通知");
            message.setContent("尊敬的用户，您对["+comment.getTargetInfo()+"]的评论已被拒绝，原因：" + req.getRejectReason());
            message.setType(CommonEnumContainer.MessageType.COMMENT_ON_AUDIT_RESULTS.ordinal());
            message.setViewState(CommonEnumContainer.MessageViewState.DID_NOT_SEE.ordinal());
            message.setContactor(comment.getRealName());
            message.setPhone(comment.getCommentUserName());
            message.setState(CommonEnumContainer.State.NORMAL.getValue());
            message.setDeltag(false);
            message.setCreateDate(new Date());
            message.setMemberId(comment.getCommentUserId());
            message.setRelationId(comment.getId());
            messageMapper.insert(message);
        }
    }
}