package com.rong.user.service;

import com.rong.common.service.BasicService;
import com.rong.user.module.entity.TbUserPeopleReservation;
import com.rong.user.module.query.TsUserPeopleReservation;
import com.rong.user.module.request.TqUserPeopleReservation;
import com.rong.user.module.view.TvUserPeopleReservation;

import java.util.List;

public interface UserPeopleReservationService extends BasicService<TbUserPeopleReservation, TqUserPeopleReservation, TvUserPeopleReservation> {
    List<TvUserPeopleReservation> selectPriManager(TsUserPeopleReservation query);
}