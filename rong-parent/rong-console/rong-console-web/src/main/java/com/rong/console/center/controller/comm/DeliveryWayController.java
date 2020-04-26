package com.rong.console.center.controller.comm;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbDeliveryWay;
import com.rong.sys.module.query.TsDeliveryWay;
import com.rong.sys.module.request.TqDeliveryWay;
import com.rong.sys.module.view.TvDeliveryWay;
import com.rong.sys.service.DeliveryWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.COMM_DELIVERY_PATH)
public class DeliveryWayController extends BaseController<TbDeliveryWay, TqDeliveryWay, TvDeliveryWay, DeliveryWayService> {

    @Autowired
    public DeliveryWayController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_DELIVERY_PATH)
                .build(), TsDeliveryWay.Fields.id);
    }
}
