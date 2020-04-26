package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.view.TvMdPeople;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MdPeopleMapper extends CommonBasicMapper<TbMdPeople, TvMdPeople>, MultiTableMapper<TbMdPeople, TvMdPeople> {

    List<TvMdPeople> getListByPfundSecurityId(@Param("securityId")Integer securityId);

    List<TvMdPeople> getListByFundSecurityId(@Param("securityId")Integer securityId);
}