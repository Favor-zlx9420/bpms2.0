package com.rong.console.center.controller.adm;

import com.rong.admin.module.entity.TbAdmMemorandum;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.module.query.TsAdmMemorandum;
import com.rong.admin.module.request.TqAdmMemorandum;
import com.rong.admin.module.view.TvAdmMemorandum;
import com.rong.admin.service.AdmMemorandumService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.NoPermissionException;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(RightConst.ADM_AIDE_MEMOIRE_PATH)
public class AdmMemorandumController extends BaseController<TbAdmMemorandum, TqAdmMemorandum, TvAdmMemorandum, AdmMemorandumService> {

    @Autowired
    LabelService labelService;

    @Autowired
    public AdmMemorandumController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ADM_AIDE_MEMOIRE_PATH)
                .build()
                , TsAdmMemorandum.Fields.id
        );
    }

    /**
     * 列表页面:get
     */
    @GetMapping(value = "my-list")
    public String list(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo) throws Exception {
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        //not sort
        MultiTableQueryWrapper queryWrapper = service.getMultiCommonWrapper();
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("theads", "[]");
        request.setAttribute("pageList", JSONUtil.toJSONString(service.selectPageList(queryWrapper)));
        request.setAttribute("queryEntity", JSONUtil.toJSONString(queryInfo));
        request.setAttribute("hasAddPermission", admAspect.hasPermission(viewModel.getAddAction(), request));
        request.setAttribute("hasEditPermission", admAspect.hasPermission(viewModel.getEditAction(), request));
        request.setAttribute("hasViewPermission", admAspect.hasPermission(viewModel.getViewAction(), request));
        request.setAttribute("AideMemoireStateDesc", CommonEnumContainer.AdmMemorandumState.values());
        return viewModel.getBasePath() + "list";
    }

    @Override
    @PostMapping(value = "dataGrid")
    @ResponseBody
    public Result<TvPageList<TvAdmMemorandum>> dataGrid(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo) throws Exception {
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        //queryEntity.getEntity().setAdmUserId(storage.getId());
        return super.dataGrid(request, response, queryInfo);
    }

    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvAdmMemorandum view) throws Exception {
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        intAttributes(request, response, null);
    }

    @Override
    @PostMapping(value = "add")
    @ResponseBody
    public Result add_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqAdmMemorandum req) throws Exception {
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        req.getEntity().setAdmUserId(storage.getId());
        return super.add_post(request, response, req);
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvAdmMemorandum view) throws Exception {
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        if (!CommonUtil.isEqual(view.getAdmUserId(), storage.getId())) {
            throw new NoPermissionException("您无权查看该条信息！");
        }
        intAttributes(request, response, view);
    }

    @Override
    public void otherEventForViewInit(HttpServletRequest request, HttpServletResponse response, TvAdmMemorandum view) throws Exception {
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        if (!CommonUtil.isEqual(view.getAdmUserId(), storage.getId())) {
            throw new NoPermissionException("您无权查看该条信息！");
        }
        intAttributes(request, response, view);
    }

    private void intAttributes(HttpServletRequest request, HttpServletResponse response, TvAdmMemorandum view) throws Exception {
        //标签
        List labels = labelService.selectList(new QueryWrapper()
            .eq(TsLabel.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.SYSTEM_MESSAGES.getValue())
        );
        request.setAttribute("labels", labels);
        request.setAttribute("AdmMemorandumDesc", CommonEnumContainer.AdmMemorandumState.values());
    }
}
