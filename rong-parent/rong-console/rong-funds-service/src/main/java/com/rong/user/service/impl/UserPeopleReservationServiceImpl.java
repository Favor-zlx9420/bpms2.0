package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserPeopleReservationMapper;
import com.rong.user.module.entity.TbUserPeopleReservation;
import com.rong.user.module.query.TsUserPeopleReservation;
import com.rong.user.module.request.TqUserPeopleReservation;
import com.rong.user.module.view.TvUserPeopleReservation;
import com.rong.user.service.UserPeopleReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPeopleReservationServiceImpl extends BasicServiceImpl<TbUserPeopleReservation, TqUserPeopleReservation, TvUserPeopleReservation, UserPeopleReservationMapper> implements UserPeopleReservationService {
    @Override
    public List<TvUserPeopleReservation> selectPriManager(TsUserPeopleReservation query) {
        return mapper.selectPriManager(query);
    }
}