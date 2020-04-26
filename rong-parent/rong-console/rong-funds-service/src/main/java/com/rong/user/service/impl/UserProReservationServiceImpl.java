package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserProReservationMapper;
import com.rong.user.module.entity.TbUserProReservation;
import com.rong.user.module.query.TsUserProReservation;
import com.rong.user.module.request.TqUserProReservation;
import com.rong.user.module.view.TvUserProReservation;
import com.rong.user.service.UserProReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProReservationServiceImpl extends BasicServiceImpl<TbUserProReservation, TqUserProReservation, TvUserProReservation, UserProReservationMapper> implements UserProReservationService {
    @Override
    public List<TvUserProReservation> selectPriFunds(TsUserProReservation query) {
        return mapper.selectPriFunds(query);
    }
}