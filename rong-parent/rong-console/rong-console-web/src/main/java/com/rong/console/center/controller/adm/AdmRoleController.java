package com.rong.console.center.controller.adm;

import com.rong.admin.module.entity.TbAdmRole;
import com.rong.admin.module.query.TsAdmRole;
import com.rong.admin.module.request.TqAdmRole;
import com.rong.admin.module.view.TvAdmRole;
import com.rong.admin.service.AdmRoleService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.TreeNode;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(RightConst.ADM_ROLE_PATH)
public class AdmRoleController extends BaseController<TbAdmRole, TqAdmRole, TvAdmRole, AdmRoleService> {

    @Autowired
    public AdmRoleController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ADM_ROLE_PATH)
                .build()
                , TsAdmRole.Fields.id
        );
    }

    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvAdmRole be)throws Exception{
        intAttributes(request,response,null);
        be.setState(CommonEnumContainer.State.NORMAL.getValue());
    }
    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvAdmRole view)throws Exception{
        intAttributes(request,response,view);
    }
    @Override
    public void otherEventForViewInit(HttpServletRequest request, HttpServletResponse response, TvAdmRole view)throws Exception{
        intAttributes(request,response,view);
    }
    private void intAttributes(HttpServletRequest request, HttpServletResponse response, TvAdmRole view)throws Exception {
        String permissionStr = (CommonUtil.isNull(view) ?"":view.getPermissionStr());
        List<TreeNode> trees = admColumnService.listPermissionRecursiveColumnsByPid(admAspect.getAdminByServerStorage(request),null,permissionStr, 0);
        request.setAttribute("tree", JSONUtil.toJSONString(trees));
    }
}
