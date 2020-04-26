package com.rong.console.center.controller.mem;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.user.module.entity.TbUserVipEnd;
import com.rong.user.module.query.TsUserVipEnd;
import com.rong.user.module.request.TqUserVipEnd;
import com.rong.user.module.view.TvUserVipEnd;
import com.rong.user.service.UserVipEndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.MEM_LEVEL_PATH)
public class UserVipEndController extends BaseController<TbUserVipEnd, TqUserVipEnd, TvUserVipEnd, UserVipEndService> {

    @Autowired
    public UserVipEndController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.MEM_LEVEL_PATH)
                .build(),
                TsUserVipEnd.Fields.id
                );
    }

}
