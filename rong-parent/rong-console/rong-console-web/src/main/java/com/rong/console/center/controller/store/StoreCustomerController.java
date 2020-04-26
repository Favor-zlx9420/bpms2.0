package com.rong.console.center.controller.store;

import com.rong.admin.module.foreign.UserStorage;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.service.MemBaseService;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.query.TsDirectStoreUser;
import com.rong.store.module.request.TqDirectStoreUser;
import com.rong.store.module.view.TvDirectStoreUser;
import com.rong.store.service.DirectStoreUserService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
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
@RequestMapping(RightConst.STORE_CUSTOMER_PATH)
public class StoreCustomerController extends BaseController<TbDirectStoreUser, TqDirectStoreUser, TvDirectStoreUser, DirectStoreUserService> {
    @Autowired
    private MemBaseService memBaseService;
    private String auditAction;
    @Autowired
    public StoreCustomerController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.STORE_CUSTOMER_PATH)
                .build(),
                TsDirectStoreUser.Fields.id
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
        TbDirectStoreUser entity = service.selectItemByPrimaryKey(IdWrapper.valueOf(id));
        TbMemBase user = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(entity.getUserId()));
        request.setAttribute("entity", JSONUtil.toJSONString(entity));
        request.setAttribute("user", JSONUtil.toJSONString(user));
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
    public Result audit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqDirectStoreUser req)throws Exception{
        UserStorage loginUser = admAspect.getAdminByServerStorage(request);
        synchronized (getClass()) {
            //审核记录
            service.updateByPrimary(req);
            return Result.success();
        }
    }
}
