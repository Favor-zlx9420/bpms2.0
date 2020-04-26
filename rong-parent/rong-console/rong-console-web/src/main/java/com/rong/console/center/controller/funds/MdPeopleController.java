package com.rong.console.center.controller.funds;

import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.query.TsMdPeople;
import com.rong.tong.pfunds.module.request.TqMdPeople;
import com.rong.tong.pfunds.module.view.TvMdPeople;
import com.rong.tong.pfunds.service.MdPeopleService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.MD_PEOPLE_PATH)
public class MdPeopleController extends FundsBaseController<TbMdPeople, TqMdPeople, TvMdPeople, MdPeopleService> {

    @Autowired
    public MdPeopleController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.MD_PEOPLE_PATH)
                .build(), TsMdPeople.Fields.personId);
    }

    /**
     * 查询简单的信息
     * @param queryInfo
     * @return
     */
    @RequestMapping("simple-list")
    @ResponseBody
    public Result simpleList(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo){
        MultiTableQueryWrapper queryWrapper = getMultiQueryInfo(request,queryInfo);
        queryWrapper.selectAllFiels(false)
                .select("e.personId,e.name,e.education");
        wrapQuery(queryWrapper,request);
        return Result.success(service.selectPageList(queryWrapper));
    }
}
