package com.rong.user.service;

import com.rong.common.service.BasicService;
import com.rong.user.module.entity.TbUserOrgReservation;
import com.rong.user.module.query.TsUserOrgReservation;
import com.rong.user.module.request.TqUserOrgReservation;
import com.rong.user.module.view.TvUserOrgReservation;

import java.util.List;

public interface UserOrgReservationService extends BasicService<TbUserOrgReservation, TqUserOrgReservation, TvUserOrgReservation> {
    List<TvUserOrgReservation> selectPriOrg(TsUserOrgReservation query);
}