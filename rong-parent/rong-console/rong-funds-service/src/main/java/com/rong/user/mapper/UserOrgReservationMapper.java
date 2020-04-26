package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserOrgReservation;
import com.rong.user.module.query.TsUserOrgReservation;
import com.rong.user.module.view.TvUserOrgReservation;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface UserOrgReservationMapper extends CommonBasicMapper<TbUserOrgReservation, TvUserOrgReservation>, MultiTableMapper<TbUserOrgReservation, TvUserOrgReservation> {
    List<TvUserOrgReservation> selectPriOrg(TsUserOrgReservation query);
}