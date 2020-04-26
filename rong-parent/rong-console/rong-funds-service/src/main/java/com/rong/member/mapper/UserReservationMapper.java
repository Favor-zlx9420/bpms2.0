package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbUserReservation;
import com.rong.member.module.view.TvUserReservation;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserReservationMapper extends CommonBasicMapper<TbUserReservation, TvUserReservation>, MultiTableMapper<TbUserReservation, TvUserReservation> {
}