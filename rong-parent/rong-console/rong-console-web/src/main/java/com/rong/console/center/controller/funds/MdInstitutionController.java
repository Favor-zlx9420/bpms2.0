package com.rong.console.center.controller.funds;

import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.query.TsMdInstitution;
import com.rong.tong.pfunds.module.request.TqMdInstitution;
import com.rong.tong.pfunds.module.view.TvMdInstitution;
import com.rong.tong.pfunds.service.MdInstitutionService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.MD_INSTITUTION_PATH)
public class MdInstitutionController extends FundsBaseController<TbMdInstitution, TqMdInstitution, TvMdInstitution, MdInstitutionService> {

    @Autowired
    public MdInstitutionController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.MD_INSTITUTION_PATH)
                .build(), TsMdInstitution.Fields.partyId);
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
                .select("e.partyId,e.partyFullName,e.partyShortName");
        wrapQuery(queryWrapper,request);
        return Result.success(service.selectPageList(queryWrapper));
    }
}
