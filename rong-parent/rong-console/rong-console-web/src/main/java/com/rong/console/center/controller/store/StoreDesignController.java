package com.rong.console.center.controller.store;

import com.rong.admin.module.foreign.UserStorage;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.store.module.entity.TbDirectStoreDesign;
import com.rong.store.module.query.TsDirectStoreDesign;
import com.rong.store.module.request.TqDirectStoreDesign;
import com.rong.store.module.view.TvDirectStoreDesign;
import com.rong.store.service.DirectStoreDesignService;
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
@RequestMapping(RightConst.STORE_DESIGN_PATH)
public class StoreDesignController extends BaseController<TbDirectStoreDesign, TqDirectStoreDesign, TvDirectStoreDesign, DirectStoreDesignService> {
    private String auditAction;
    @Autowired
    public StoreDesignController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.STORE_DESIGN_PATH)
                .build(),
                TsDirectStoreDesign.Fields.id
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
        TvDirectStoreDesign entity = service.selectViewByPrimaryKey(service.getMultiCommonIdWrapper(id));
        request.setAttribute("entity", JSONUtil.toJSONString(entity));
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
    public Result audit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqDirectStoreDesign req)throws Exception{
        UserStorage userStorage = admAspect.getAdminByServerStorage(request);
        synchronized (getClass()) {
            //审核记录
            req.getEntity().setAuditUserId(userStorage.getId());
            service.updateByPrimary(req);
            return Result.success();
        }
    }
}
