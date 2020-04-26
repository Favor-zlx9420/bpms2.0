package com.rong.console.center.controller.mem;

import com.rong.admin.module.foreign.UserStorage;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.user.module.entity.TbUserCommentReply;
import com.rong.user.module.query.TsUserCommentReply;
import com.rong.user.module.request.TqUserCommentReply;
import com.rong.user.module.view.TvUserCommentReply;
import com.rong.user.service.UserCommentReplyService;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.USER_COMMENT_REPLY_PATH)
public class UserCommentReplyController extends BaseController<TbUserCommentReply, TqUserCommentReply, TvUserCommentReply, UserCommentReplyService> {
    private String auditAction;
    @Autowired
    public UserCommentReplyController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.USER_COMMENT_REPLY_PATH)
                .build(),
                TsUserCommentReply.Fields.id
                );
        auditAction = viewModel.getBasePath()+"audit";
    }

    @Override
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("auditAction",auditAction);
        request.setAttribute("hasAuditPermission",admAspect.hasPermission(auditAction,request));
    }

    /**
     * 进入审核页面
     * @param request
     * @param response
     * @param id
     * @return
     */
    @GetMapping(value="audit")
    public String audit(HttpServletRequest request, HttpServletResponse response, Long id)throws Exception{
        TvUserCommentReply view = service.selectOneView(service.getMultiCommonWrapper()
            .eq(CompareAlias.valueOf("e.id"),id)
        );
        request.setAttribute("entity", JSONUtil.toJSONString(view));
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("auditAction",auditAction);
        request.setAttribute("hasAuditPermission",admAspect.hasPermission(auditAction,request));
        return viewModel.getBasePath()+"audit";
    }
    /**
     * 审核：
     * @param request
     * @param response
     * @param req
     * @return
     */
    @PostMapping(value="audit")
    @ResponseBody
    public Result audit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqUserCommentReply req)throws Exception{
        UserStorage loginUser = admAspect.getAdminByServerStorage(request);
        synchronized (getClass()) {
            //审核记录
            req.getEntity().setAuditUserId(loginUser.getId());
            service.updateByPrimary(req);
            return Result.success();
        }
    }
}
