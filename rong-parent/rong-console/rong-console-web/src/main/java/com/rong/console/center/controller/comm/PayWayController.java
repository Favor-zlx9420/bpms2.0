package com.rong.console.center.controller.comm;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbPayWay;
import com.rong.sys.module.query.TsPayWay;
import com.rong.sys.module.request.TqPayWay;
import com.rong.sys.module.view.TvPayWay;
import com.rong.sys.service.PayWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.COMM_PAYWAY_PATH)
public class PayWayController extends BaseController<TbPayWay, TqPayWay, TvPayWay, PayWayService> {

    @Autowired
    public PayWayController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_PAYWAY_PATH)
                .build(), TsPayWay.Fields.id
        );
    }
}
