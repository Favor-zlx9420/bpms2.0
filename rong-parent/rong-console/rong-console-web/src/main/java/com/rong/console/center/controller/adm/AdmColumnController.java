package com.rong.console.center.controller.adm;

import com.rong.admin.module.entity.TbAdmColumn;
import com.rong.admin.module.query.TsAdmColumn;
import com.rong.admin.module.request.TqAdmColumn;
import com.rong.admin.module.view.TvAdmColumn;
import com.rong.admin.service.AdmColumnService;
import com.rong.common.module.QueryInfo;
import com.rong.common.util.StringUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.vitily.mybatis.core.wrapper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.ADM_COLUMN_PATH)
public class AdmColumnController extends BaseController<TbAdmColumn, TqAdmColumn, TvAdmColumn, AdmColumnService> {
    @Autowired
    public AdmColumnController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ADM_COLUMN_PATH)
                .build()
                , TsAdmColumn.Fields.id
        );
    }
    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvAdmColumn be)throws Exception{
        String parentId = request.getParameter("parentId");
        if(StringUtil.isNotEmpty(parentId)){
            be.setParentId(Long.valueOf(parentId));
        }else{
            be.setParentId(0L);
        }
    }
    /**
     * 列表页面:get
     */
    @Override
    public String list(HttpServletRequest request,HttpServletResponse response, QueryInfo queryInfo)throws Exception{
        queryInfo.setPageInfo(new PageInfo());
        queryInfo.getPageInfo().setPageSize(PageInfo.COMMON_MAX_PAGE_SIZE);
        return super.list(request,response,queryInfo)+"-tree";
    }
}
