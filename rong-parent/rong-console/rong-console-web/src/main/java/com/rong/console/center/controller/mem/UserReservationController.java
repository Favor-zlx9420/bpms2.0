package com.rong.console.center.controller.mem;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.util.StringUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.member.module.entity.TbUserReservation;
import com.rong.member.module.query.TsMemCustomer;
import com.rong.member.module.request.TqUserReservation;
import com.rong.member.module.view.TvUserReservation;
import com.rong.member.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.USER_RESERVATION_PATH)
public class UserReservationController extends BaseController<TbUserReservation, TqUserReservation, TvUserReservation, UserReservationService> {

    @Autowired
    public UserReservationController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.USER_RESERVATION_PATH)
                .build(),
                TsMemCustomer.Fields.id
                );
    }

    @Override
    protected void preUpdate(HttpServletRequest request, HttpServletResponse response, TqUserReservation req){
        super.preUpdate(request, response, req);
        TbUserReservation entity = req.getEntity();
        if(StringUtil.isNotEmpty(entity.getReplyContent())){
            entity.setDealStatus(CommonEnumContainer.ReservationDealStatus.PROCESSED.getValue());
            entity.setDualUserId(admAspect.getAdminByServerStorage(request).getId());
        }
    }
}
