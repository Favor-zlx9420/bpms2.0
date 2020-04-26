package com.rong.console.center.controller.roadshow;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.roadshow.module.entity.TbRoadShowHotOrg;
import com.rong.roadshow.module.query.TsRoadShowHotOrg;
import com.rong.roadshow.module.request.TqRoadShowHotOrg;
import com.rong.roadshow.module.view.TvRoadShowHotOrg;
import com.rong.roadshow.service.RoadShowHotOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.ROADSHOW_HOT_ORG_PATH)
public class RoadShowHotOrgController extends BaseController<TbRoadShowHotOrg, TqRoadShowHotOrg, TvRoadShowHotOrg, RoadShowHotOrgService> {
    @Autowired
    public RoadShowHotOrgController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ROADSHOW_HOT_ORG_PATH)
                .build(),
                TsRoadShowHotOrg.Fields.id
                );
    }
}
