package com.rong.console.center.controller.adm;

import com.rong.admin.module.entity.TbAdmLog;
import com.rong.admin.module.query.TsAdmLog;
import com.rong.admin.module.request.TqAdmLog;
import com.rong.admin.module.view.TvAdmLog;
import com.rong.admin.service.AdmLogService;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.ADM_LOG_PATH)
public class AdmLogController extends BaseController<TbAdmLog, TqAdmLog, TvAdmLog, AdmLogService> {

    @Autowired
    public AdmLogController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ADM_LOG_PATH)
                .build()
                , TsAdmLog.Fields.id
        );
    }
}
