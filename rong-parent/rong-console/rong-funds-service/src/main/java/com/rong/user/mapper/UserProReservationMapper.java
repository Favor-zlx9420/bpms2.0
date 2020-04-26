package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserProReservation;
import com.rong.user.module.query.TsUserProReservation;
import com.rong.user.module.view.TvUserProReservation;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface UserProReservationMapper extends CommonBasicMapper<TbUserProReservation, TvUserProReservation>, MultiTableMapper<TbUserProReservation, TvUserProReservation> {
    List<TvUserProReservation> selectPriFunds(TsUserProReservation query);
}