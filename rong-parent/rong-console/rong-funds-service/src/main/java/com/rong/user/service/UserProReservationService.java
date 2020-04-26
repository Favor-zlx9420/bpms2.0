package com.rong.user.service;

import com.rong.common.service.BasicService;
import com.rong.user.module.entity.TbUserProReservation;
import com.rong.user.module.query.TsUserProReservation;
import com.rong.user.module.request.TqUserProReservation;
import com.rong.user.module.view.TvUserProReservation;

import java.util.List;

public interface UserProReservationService extends BasicService<TbUserProReservation, TqUserProReservation, TvUserProReservation> {
    List<TvUserProReservation> selectPriFunds(TsUserProReservation query);
}