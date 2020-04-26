package com.rong.console.center.controller.mem;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.util.StringUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.user.module.entity.TbUserFeedBack;
import com.rong.user.module.query.TsUserFeedBack;
import com.rong.user.module.request.TqUserFeedBack;
import com.rong.user.module.view.TvUserFeedBack;
import com.rong.user.service.UserFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.USER_FEEDBACK_PATH)
public class UserFeedbackController extends BaseController<TbUserFeedBack, TqUserFeedBack, TvUserFeedBack, UserFeedBackService> {
    @Autowired
    public UserFeedbackController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.USER_FEEDBACK_PATH)
                .build(),
                TsUserFeedBack.Fields.id
                );
    }

    @Override
    protected void preUpdate(HttpServletRequest request, HttpServletResponse response, TqUserFeedBack req){
        TbUserFeedBack entity = req.getEntity();
        if(StringUtil.isNotEmpty(entity.getReplyContent())){
            entity.setResult(CommonEnumContainer.DealStatus.PROCESSED.getValue());
            entity.setReplyUserId(admAspect.getAdminByServerStorage(request).getId());
        }
        super.preUpdate(request, response, req);
    }
}
