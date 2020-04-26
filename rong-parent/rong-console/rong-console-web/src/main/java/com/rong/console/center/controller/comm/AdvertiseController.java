package com.rong.console.center.controller.comm;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbAdvertise;
import com.rong.sys.module.query.TsAdvertise;
import com.rong.sys.module.request.TqAdvertise;
import com.rong.sys.module.view.TvAdvertise;
import com.rong.sys.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.COMM_ADVERTISE_PATH)
public class AdvertiseController extends BaseController<TbAdvertise, TqAdvertise, TvAdvertise, AdvertiseService> {

    @Autowired
    public AdvertiseController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_ADVERTISE_PATH)
                .build(), TsAdvertise.Fields.id);
    }

    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvAdvertise be) throws Exception {
        super.otherEventForAddInit(request, response, be);
        be.setState(CommonEnumContainer.State.NORMAL.getValue());
    }
}
