package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserPeopleReservation;
import com.rong.user.module.query.TsUserPeopleReservation;
import com.rong.user.module.view.TvUserPeopleReservation;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface UserPeopleReservationMapper extends CommonBasicMapper<TbUserPeopleReservation, TvUserPeopleReservation>, MultiTableMapper<TbUserPeopleReservation, TvUserPeopleReservation> {
    List<TvUserPeopleReservation> selectPriManager(TsUserPeopleReservation query);
}