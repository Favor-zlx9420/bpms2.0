package com.rong.console.center.controller.funds;

import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.query.TsMdSecurity;
import com.rong.tong.pfunds.module.request.TqMdSecurity;
import com.rong.tong.pfunds.module.view.TvMdSecurity;
import com.rong.tong.pfunds.service.MdSecurityService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.MD_SECURITY_PATH)
public class MdSecurityController extends FundsBaseController<TbMdSecurity, TqMdSecurity, TvMdSecurity, MdSecurityService> {

    @Autowired
    public MdSecurityController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.MD_SECURITY_PATH)
                .build(), TsMdSecurity.Fields.securityId);
    }

    /**
     * 查询简单的信息
     * @param queryInfo
     * @return
     */
    @RequestMapping("simple-list")
    @ResponseBody
    @Override
    public Result simpleList(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo){
        MultiTableQueryWrapper queryWrapper = getMultiQueryInfo(request,queryInfo);
        queryWrapper.selectAllFiels(false)
                .select("e.securityId,e.secShortName,e.secFullName");
        wrapQuery(queryWrapper,request);
        return Result.success(service.selectPageList(queryWrapper));
    }
}
