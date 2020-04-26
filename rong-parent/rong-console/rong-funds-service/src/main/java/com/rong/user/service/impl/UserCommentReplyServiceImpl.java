package com.rong.user.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbUserComment;
import com.rong.member.module.view.TvUserComment;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.sys.mapper.MessageMapper;
import com.rong.sys.module.entity.TbMessage;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.user.mapper.UserCommentReplyMapper;
import com.rong.user.module.entity.TbUserCommentReply;
import com.rong.user.module.request.TqUserCommentReply;
import com.rong.user.module.view.TvUserCommentReply;
import com.rong.user.service.UserCommentReplyService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCommentReplyServiceImpl extends BasicServiceImpl<TbUserCommentReply, TqUserCommentReply, TvUserCommentReply, UserCommentReplyMapper> implements UserCommentReplyService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mb.realName,mb.headPortrait,mb.userName replyUserName,uc.content commentContent,uc.type commentType")
                .select0(
                        SelectAlias.valueOf("case when uc.type = 0 then mi.party_short_name when uc.type = 1 then mp.name when uc.type=2 then ms.sec_short_name else ri.title end targetInfo",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), mb->
                        mb.eqc(CompareAlias.valueOf("e.replyUserId"),CompareAlias.valueOf("mb.id"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbUserComment.class,"uc"), mb->
                        mb.eqc(CompareAlias.valueOf("uc.id"),CompareAlias.valueOf("e.commentId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->
                        mi.eqc(CompareAlias.valueOf("uc.targetId"),CompareAlias.valueOf("mi.partyId"))
                                .eq(CompareAlias.valueOf("uc.type"), CommonEnumContainer.CommentType.ORGANIZATION.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdPeople.class,"mp"), mp->
                        mp.eqc(CompareAlias.valueOf("uc.targetId"),CompareAlias.valueOf("mp.personId"))
                                .eq(CompareAlias.valueOf("uc.type"), CommonEnumContainer.CommentType.FUND_MANAGER.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"ms"), ms->
                        ms.eqc(CompareAlias.valueOf("uc.targetId"),CompareAlias.valueOf("ms.securityId"))
                                .eq(CompareAlias.valueOf("uc.type"), CommonEnumContainer.CommentType.PRODUCT.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRoadShowInfo.class,"ri"), mb->
                        mb.eqc(CompareAlias.valueOf("uc.targetId"),CompareAlias.valueOf("ri.id"))
                                .eq(CompareAlias.valueOf("uc.type"), CommonEnumContainer.CommentType.ROAD_SHOW.getValue())
                )
                ;
    }


    @Override
    protected void afterUpdate(TqUserCommentReply req) {
        super.afterUpdate(req);
        //拒绝理由
        if(CommonUtil.isEqual(req.getEntity().getAuditResult(), CommonEnumContainer.CustomerAuditState.NOT_APPROVED.getValue())){
            if(StringUtil.isEmpty(req.getRejectReason())){
                return;
            }
            TvUserCommentReply comment = mapper.selectOneView(getMultiCommonWrapper().eq(CompareAlias.valueOf("e.id"),req.getEntity().getId()));
            TbMessage message = new TbMessage();
            message.setTitle("回复审核结果通知");
            message.setContent("尊敬的用户，您对["+comment.getTargetInfo()+"]的回复已被拒绝，原因：" + req.getRejectReason());
            message.setType(CommonEnumContainer.MessageType.REPLY_TO_AUDIT_RESULTS.ordinal());
            message.setViewState(CommonEnumContainer.MessageViewState.DID_NOT_SEE.ordinal());
            message.setContactor(comment.getRealName());
            message.setPhone(comment.getReplyUserName());
            message.setState(CommonEnumContainer.State.NORMAL.getValue());
            message.setDeltag(false);
            message.setCreateDate(new Date());
            message.setMemberId(comment.getReplyUserId());
            message.setRelationId(comment.getId());
            messageMapper.insert(message);
        }
    }

}