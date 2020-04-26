package com.rong.console.center.controller.roadshow;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.roadshow.module.entity.TbRoadShowHotUser;
import com.rong.roadshow.module.query.TsRoadShowHotUser;
import com.rong.roadshow.module.request.TqRoadShowHotUser;
import com.rong.roadshow.module.view.TvRoadShowHotUser;
import com.rong.roadshow.service.RoadShowHotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.ROADSHOW_HOT_USER_PATH)
public class RoadShowHotUserController extends BaseController<TbRoadShowHotUser, TqRoadShowHotUser, TvRoadShowHotUser, RoadShowHotUserService> {
    @Autowired
    public RoadShowHotUserController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ROADSHOW_HOT_USER_PATH)
                .build(),
                TsRoadShowHotUser.Fields.id
                );
    }
}
