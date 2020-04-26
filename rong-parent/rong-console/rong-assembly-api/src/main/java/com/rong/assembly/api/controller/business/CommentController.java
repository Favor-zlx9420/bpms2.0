package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.module.request.buz.TqUserComment;
import com.rong.assembly.api.module.request.buz.TqUserCommentReply;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.member.module.view.TvUserComment;
import com.rong.member.service.UserCommentService;
import com.rong.user.module.query.TsUserCommentReply;
import com.rong.user.module.view.TvUserCommentReply;
import com.rong.user.service.UserCommentReplyService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "评论中心")
@RequestMapping("comment")
@RestController
public class CommentController {
    @Autowired
    private UserCommentService userCommentService;
    @Autowired
    private UserCommentReplyService userCommentReplyService;
    @GetMapping("list")
    @ApiOperation(value = "评论列表")
    public Result<TvPageList<TvUserComment>> list(TqUserComment req){
        if(null == req.getPageInfo()){
            req.setPageInfo(new PageInfo());
        }
        if(req.getPageInfo().getPageSize() > 10){
            req.getPageInfo().setPageSize(10);
        }
        TvPageList<TvUserComment> pageList =
                userCommentService.selectPageList(userCommentService.getMultiCommonWrapper()
                        .eq(CompareAlias.valueOf("e.targetId"),req.getTargetId())
                        .eq(CompareAlias.valueOf("e.type"),req.getType())
                        .eq(CompareAlias.valueOf("e.visible"),true)
                        .eq(CompareAlias.valueOf("e.deltag"),false)
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.createDate")))
                        .page(req.getPageInfo())
                );
        for(TvUserComment comment:pageList.getList()){
            comment.setReplies(
                    userCommentReplyService.selectViewList(
                            userCommentReplyService.getMultiCommonWrapper()
                            .eq(CompareAlias.valueOf(TsUserCommentReply.Fields.commentId,"e"),comment.getId())
                            .eq(CompareAlias.valueOf(TsUserCommentReply.Fields.auditResult,"e"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getDesc())
                            .eq(CompareAlias.valueOf("e.visible"),true)
                            .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
                    )
            );
        }
        return Result.success(pageList);
    }
    @GetMapping("reply/list")
    @ApiOperation(value = "某条评论回复列表")
    public Result<TvPageList<TvUserCommentReply>> replyList(TqUserCommentReply req){
        if(null == req.getPageInfo()){
            req.setPageInfo(new PageInfo());
        }
        TvPageList<TvUserCommentReply> pageList =
                userCommentReplyService.selectPageList(userCommentReplyService.getMultiCommonWrapper()
                        .eq(CompareAlias.valueOf("e.commentId"),req.getCommentId())
                        .eq(CompareAlias.valueOf("e.visible"),true)
                        .eq(CompareAlias.valueOf("e.deltag"),false)
                        .eq(CompareAlias.valueOf(TsUserCommentReply.Fields.auditResult,"e"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getDesc())
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.createDate")))
                        .page(req.getPageInfo())
                );
        return Result.success(pageList);
    }
}