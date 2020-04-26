package com.rong.console.center.controller.roadshow;

import com.alibaba.fastjson.JSON;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.module.view.TvAdmFields;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.query.TsRoadShowCategory;
import com.rong.roadshow.module.query.TsRoadShowInfo;
import com.rong.roadshow.module.request.TqRoadShowInfo;
import com.rong.roadshow.module.view.TvRoadShowInfo;
import com.rong.roadshow.service.RoadShowCategoryService;
import com.rong.roadshow.service.RoadShowInfoService;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
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
import java.util.List;

@Controller
@RequestMapping(RightConst.ROADSHOW_INFO_PATH)
public class RoadShowInfoController extends BaseController<TbRoadShowInfo, TqRoadShowInfo, TvRoadShowInfo, RoadShowInfoService> {
    @Autowired
    private RoadShowCategoryService roadShowCategoryService;
    @Autowired
    private LabelService labelService;
    private String auditAction;
    @Autowired
    public RoadShowInfoController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ROADSHOW_INFO_PATH)
                .build(), TsRoadShowInfo.Fields.id);
        auditAction = viewModel.getBasePath() + "audit";
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
        TvRoadShowInfo entity = service.selectViewByPrimaryKey(service.getMultiCommonIdWrapper(id));
        //实际费用默认为预计费用
        request.setAttribute("entity", JSONUtil.toJSONString(entity));
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("auditAction",auditAction);
        request.setAttribute("hasAuditPermission",admAspect.hasPermission(auditAction,request));
        otherEventForEditInit(request,response,entity);
        return viewModel.getBasePath()+"audit";
    }
    @PostMapping(value="audit")
    @ResponseBody
    public Result audit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqRoadShowInfo req)throws Exception{
        UserStorage loginUser = admAspect.getAdminByServerStorage(request);
        req.getEntity().setAuditUserId(loginUser.getId());
        synchronized (getClass()) {
            return Result.success(service.updateByPrimary(req));
        }
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("sysType", SysEnumContainer.LabelType.ROADSHOW_LABEL.getValue());
        request.setAttribute("auditAction",auditAction);
        request.setAttribute("hasAuditPermission",admAspect.hasPermission(auditAction,request));
    }

    @GetMapping("audit-list")
    public String auditList(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo) throws Exception {

        //可能继承类需要做一些额外的 事情
        otherEventForListInit(request,response);
        //获得显示的列
        Long columnId = admColumnService.hashAuthSingle(viewModel.getBasePath()+"audit-list");
        List<TvAdmFields> theads=admFieldsService.getFieldShowByColumnId(columnId);
        request.setAttribute("theads", JSONUtil.toJSONString(theads,PRO_FILTER_ITEMS));
        request.setAttribute("queryInfo", JSONUtil.toJSONString(queryInfo));
        request.setAttribute("hasAddPermission",admAspect.hasPermission(viewModel.getAddAction(),request));
        request.setAttribute("hasEditPermission",admAspect.hasPermission(viewModel.getEditAction(),request));
        request.setAttribute("hasViewPermission",admAspect.hasPermission(viewModel.getViewAction(),request));
        request.setAttribute("hasDelPermission",admAspect.hasPermission(viewModel.getDelOrRecAction(),request));
        request.setAttribute("hasDataGridPermission",admAspect.hasPermission(viewModel.getDataGridAction(),request));
        request.setAttribute("viewModel", this.viewModel);
        ViewModel vm = new ViewModel.Builder()
                .basePath(RightConst.ROADSHOW_INFO_PATH)
                .build();
        vm.setListAction(vm.getBasePath() + "audit-list");

        request.setAttribute("viewModel", vm);
        return "base/list";
    }


    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request) {
        super.wrapQuery(queryWrapper, request);
        String keyword = request.getParameter("keyword");
        if(StringUtil.isNotEmpty(keyword)){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("mb.realName"),keyword)
                    .like(CompareAlias.valueOf("au.realName"),keyword)
                    .like(CompareAlias.valueOf("e.title"),keyword)
                    .like(CompareAlias.valueOf("e.presenter"),keyword)
            );
        }
    }


    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvRoadShowInfo be) throws Exception {
        super.otherEventForAddInit(request, response, be);
        attributes(request);
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvRoadShowInfo view) throws Exception {
        super.otherEventForEditInit(request, response, view);
        attributes(request);
    }
    private void attributes(HttpServletRequest request){
        request.setAttribute("labels", JSON.toJSONString(labelService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.ROADSHOW_LABEL.getValue())
                        .eq(TsLabel.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                        .eq(TsLabel.Fields.deltag, false)
        )));
        request.setAttribute("cates",JSON.toJSONString(roadShowCategoryService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsRoadShowCategory.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                        .eq(TsRoadShowCategory.Fields.deltag, false)
        )));
    }

    @Override
    public Result add_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqRoadShowInfo req) throws Exception {
        TbRoadShowInfo entity = req.getEntity();
        entity.setFrom(1);
        entity.setAdmUserId(admAspect.getAdminByServerStorage(request).getId());
        return super.add_post(request, response, req);
    }
}
