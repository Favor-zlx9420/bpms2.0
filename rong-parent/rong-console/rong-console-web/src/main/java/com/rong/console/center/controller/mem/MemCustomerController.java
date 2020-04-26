package com.rong.console.center.controller.mem;

import com.rong.common.module.Result;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.member.module.entity.TbMemCustomer;
import com.rong.member.module.query.TsMemCustomer;
import com.rong.member.module.request.TqMemCustomer;
import com.rong.member.module.view.TvMemCustomer;
import com.rong.member.service.MemCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.MEM_CUSTOMER_PATH)
public class MemCustomerController extends BaseController<TbMemCustomer, TqMemCustomer, TvMemCustomer, MemCustomerService> {

    private String auditAction = "audit";
    @Autowired
    public MemCustomerController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.MEM_CUSTOMER_PATH)
                .build(),
                TsMemCustomer.Fields.id
                );
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("auditAction",viewModel.getBasePath() + auditAction);
    }
    @PostMapping(value = "audit")
    @ResponseBody
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqMemCustomer req)throws Exception{
        //bean.setReqIp(HttpClientUtil.getIP(request));
        preUpdate(request,response,req);
        return Result.success(service.updateByPrimary(req),"更新成功！");
    }

}
