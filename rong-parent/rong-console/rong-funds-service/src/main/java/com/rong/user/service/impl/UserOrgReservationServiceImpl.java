package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserOrgReservationMapper;
import com.rong.user.module.entity.TbUserOrgReservation;
import com.rong.user.module.query.TsUserOrgReservation;
import com.rong.user.module.request.TqUserOrgReservation;
import com.rong.user.module.view.TvUserOrgReservation;
import com.rong.user.service.UserOrgReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrgReservationServiceImpl extends BasicServiceImpl<TbUserOrgReservation, TqUserOrgReservation, TvUserOrgReservation, UserOrgReservationMapper> implements UserOrgReservationService {
    @Override
    public List<TvUserOrgReservation> selectPriOrg(TsUserOrgReservation query) {
        return mapper.selectPriOrg(query);
    }
}