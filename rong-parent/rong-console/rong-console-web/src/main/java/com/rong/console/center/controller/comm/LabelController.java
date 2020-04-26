package com.rong.console.center.controller.comm;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.module.request.TqLabel;
import com.rong.sys.module.view.TvLabel;
import com.rong.sys.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.COMM_LABEL_PATH)
public class LabelController extends BaseController<TbLabel, TqLabel, TvLabel, LabelService> {

    @Autowired
    public LabelController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_LABEL_PATH)
                .build(), TsLabel.Fields.id);
    }

}
