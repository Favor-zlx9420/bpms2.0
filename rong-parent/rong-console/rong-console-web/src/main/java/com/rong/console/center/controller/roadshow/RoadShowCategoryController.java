package com.rong.console.center.controller.roadshow;

import com.rong.admin.module.foreign.UserStorage;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.StringUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.roadshow.module.entity.TbRoadShowCategory;
import com.rong.roadshow.module.query.TsRoadShowCategory;
import com.rong.roadshow.module.request.TqRoadShowCategory;
import com.rong.roadshow.module.view.TvRoadShowCategory;
import com.rong.roadshow.service.RoadShowCategoryService;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(RightConst.ROADSHOW_CATE_PATH)
public class RoadShowCategoryController extends BaseController<TbRoadShowCategory, TqRoadShowCategory, TvRoadShowCategory, RoadShowCategoryService> {
    @Autowired
    private LabelService labelService;
    @Autowired
    public RoadShowCategoryController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ROADSHOW_CATE_PATH)
                .build(),
                TsRoadShowCategory.Fields.id
                );
    }
    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvRoadShowCategory be)throws Exception{
        String parentId = request.getParameter("parentId");
        if(StringUtil.isNotEmpty(parentId)){
            be.setParentId(Long.valueOf(parentId));
        }else{
            be.setParentId(0L);
        }
        intAttributes(request, response, null);
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvRoadShowCategory view) throws Exception {
        intAttributes(request, response, view);
    }

    @Override
    public void otherEventForViewInit(HttpServletRequest request, HttpServletResponse response, TvRoadShowCategory view) throws Exception {
        intAttributes(request, response, view);
    }

    private void intAttributes(HttpServletRequest request, HttpServletResponse response, TvRoadShowCategory view) throws Exception {
        List labelList = labelService.selectList(
                new QueryWrapper()
                        .eq(TsLabel.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                        .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.ROADSHOW_LABEL.getValue())
                        .eq(TsLabel.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        request.setAttribute("labelList", labelList);
    }


    @Override
    @PostMapping(value="add")
    @ResponseBody
    public Result add_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqRoadShowCategory req)throws Exception{
        preInsert(request,response,req);
        UserStorage adminStorage = admAspect.getAdminByServerStorage(request);
        req.getEntity().setCreateBy(adminStorage.getId());
        return Result.success(service.insert(req),"操作成功");
    }
}
