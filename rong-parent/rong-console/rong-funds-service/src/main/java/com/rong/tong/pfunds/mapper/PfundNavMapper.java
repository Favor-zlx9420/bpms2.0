package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundNav;
import com.rong.tong.pfunds.module.view.TvHisNav;
import com.rong.tong.pfunds.module.view.TvPfundNav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PfundNavMapper extends CommonBasicMapper<TbPfundNav, TvPfundNav>, MultiTableMapper<TbPfundNav, TvPfundNav> {
    List<TvHisNav> accumNavTrendOfManager(@Param("personId") Long personId, @Param("startDate") Date startDate, @Param("endDate")Date endDate);
    List<TvHisNav> accumNavTrendOfOrg(@Param("partyId") Long partyId, @Param("startDate") Date startDate, @Param("endDate")Date endDate);
}