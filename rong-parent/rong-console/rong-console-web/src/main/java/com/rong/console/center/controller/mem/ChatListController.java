package com.rong.console.center.controller.mem;

import com.rong.admin.module.foreign.UserStorage;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.member.module.query.TsMemCustomer;
import com.rong.store.module.entity.TbDirectStoreServiceHistory;
import com.rong.store.module.request.TqDirectStoreServiceHistory;
import com.rong.store.module.view.TvDirectStoreServiceHistory;
import com.rong.store.service.DirectStoreServiceHistoryService;
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
@RequestMapping(RightConst.CHAT_LIST_PATH)
public class ChatListController extends BaseController<TbDirectStoreServiceHistory, TqDirectStoreServiceHistory, TvDirectStoreServiceHistory, DirectStoreServiceHistoryService> {

    private String auditAction;
    @Autowired
    public ChatListController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.CHAT_LIST_PATH)
                .build(),
                TsMemCustomer.Fields.id
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
        TvDirectStoreServiceHistory view = service.selectOneView(service.getMultiCommonWrapper()
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
     */
    @PostMapping(value="audit")
    @ResponseBody
    public Result audit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqDirectStoreServiceHistory req)throws Exception{
        UserStorage loginUser = admAspect.getAdminByServerStorage(request);
        synchronized (getClass()) {
            //审核记录
            service.updateByPrimary(req);
            return Result.success();
        }
    }
}
