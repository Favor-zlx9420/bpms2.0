package com.rong.console.center.controller.adm;

import com.rong.admin.module.entity.TbAdmFields;
import com.rong.admin.module.query.TsAdmFields;
import com.rong.admin.module.request.TqAdmFields;
import com.rong.admin.module.view.TvAdmFields;
import com.rong.admin.service.AdmFieldsService;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.ADM_FIELDS_PATH)
public class AdmFieldsController extends BaseController<TbAdmFields, TqAdmFields, TvAdmFields, AdmFieldsService> {

    @Autowired
    public AdmFieldsController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ADM_FIELDS_PATH)
                .build()
                , TsAdmFields.Fields.id
        );
    }
}
