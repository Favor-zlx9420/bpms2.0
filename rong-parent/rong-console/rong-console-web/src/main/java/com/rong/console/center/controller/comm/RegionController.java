package com.rong.console.center.controller.comm;

import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbRegion;
import com.rong.sys.module.query.TsRegion;
import com.rong.sys.module.request.TqRegion;
import com.rong.sys.module.view.TvRegion;
import com.rong.sys.service.RegionService;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.COMM_REGION_PATH)
public class RegionController extends BaseController<TbRegion, TqRegion, TvRegion, RegionService> {
    @Autowired
    public RegionController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_REGION_PATH)
                .build(), TsRegion.Fields.id
                );
    }/**
     * 列表页面:get
     */
    @Override
    @GetMapping(value = "list")
    public String list(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo)throws Exception{
        return super.list(request,response,queryInfo)+"-tree";
    }

    /**
     * 数据,之后权限会加上
     */
    @Override
    @PostMapping(value = "dataGrid")
    @ResponseBody
    public Result<TvPageList<TvRegion>> dataGrid(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo)throws Exception{
        if(CommonUtil.isNull(queryInfo.getPageInfo())){
            queryInfo.setPageInfo(new PageInfo());
        }
        String parentId = request.getParameter("parentId");
        if(StringUtil.isEmpty(parentId)){
            parentId = "1";
        }
        queryInfo.addCondition(FieldValue.fromCondition("e.parentId.eq",parentId));
        return super.dataGrid(request,response,queryInfo);
    }
    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvRegion be)throws Exception{
        TbRegion parent = service.selectItemByPrimaryKey(
                IdWrapper.valueOf(Long.valueOf(request.getParameter("parentId")), TsRegion.Fields.id, TsRegion.Fields.name)
        );
        be.setParentId(parent.getId());
        be.setParentName(parent.getName());
    }
}
