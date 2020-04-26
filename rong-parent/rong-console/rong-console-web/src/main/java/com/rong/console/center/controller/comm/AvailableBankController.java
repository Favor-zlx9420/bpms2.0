package com.rong.console.center.controller.comm;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbAvailableBank;
import com.rong.sys.module.query.TsAvailableBank;
import com.rong.sys.module.request.TqAvailableBank;
import com.rong.sys.module.view.TvAvailableBank;
import com.rong.sys.service.AvailableBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.COMM_AVAILABLEBANK_PATH)
public class AvailableBankController extends BaseController<TbAvailableBank, TqAvailableBank, TvAvailableBank, AvailableBankService> {

    @Autowired
    public AvailableBankController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_AVAILABLEBANK_PATH)
                .build(), TsAvailableBank.Fields.id);
    }
    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvAvailableBank be) throws Exception {
        request.setAttribute("BankCardTypeDesc", CommonEnumContainer.BankCardType.values());
    }
    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvAvailableBank view) throws Exception {
        request.setAttribute("BankCardTypeDesc", CommonEnumContainer.BankCardType.values());
    }
}
