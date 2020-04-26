package com.rong.console.center.controller.mem;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.query.TsUserCardInfo;
import com.rong.usercard.module.request.TqUserCardInfo;
import com.rong.usercard.module.view.TvUserCardInfo;
import com.rong.usercard.service.UserCardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.USER_CARD_INFO_PATH)
public class UserCardInfoController extends BaseController<TbUserCardInfo, TqUserCardInfo, TvUserCardInfo, UserCardInfoService> {

    @Autowired
    public UserCardInfoController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.USER_CARD_INFO_PATH)
                .build(),
                TsUserCardInfo.Fields.id
                );
    }

}
