package com.rong.console.center.controller.cms;

import com.rong.cms.module.entity.TbCmsCategory;
import com.rong.cms.module.query.TsCmsCategory;
import com.rong.cms.module.request.TqCmsCategory;
import com.rong.cms.module.view.TvCmsCategory;
import com.rong.cms.service.CmsCategoryService;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.CMS_NEWSCATE_PATH)
public class CmsCategoryController extends BaseController<TbCmsCategory, TqCmsCategory, TvCmsCategory, CmsCategoryService> {

    @Autowired
    public CmsCategoryController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.CMS_NEWSCATE_PATH)
                .build(),
                TsCmsCategory.Fields.id
                );
    }
}
