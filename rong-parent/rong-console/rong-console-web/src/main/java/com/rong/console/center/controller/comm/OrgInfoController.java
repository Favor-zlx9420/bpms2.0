package com.rong.console.center.controller.comm;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbOrgInfo;
import com.rong.sys.module.query.TsOrgInfo;
import com.rong.sys.module.request.TqOrgInfo;
import com.rong.sys.module.view.TvOrgInfo;
import com.rong.sys.service.OrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.COMM_ORG_INFO_PATH)
public class OrgInfoController extends BaseController<TbOrgInfo, TqOrgInfo, TvOrgInfo, OrgInfoService> {

    @Autowired
    public OrgInfoController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_ORG_INFO_PATH)
                .build(), TsOrgInfo.Fields.id);
    }
}
