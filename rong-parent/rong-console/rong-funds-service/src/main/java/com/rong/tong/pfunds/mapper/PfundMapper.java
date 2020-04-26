package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.view.TvPfund;
import com.rong.tong.pfunds.module.view.TvSearchPfundInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PfundMapper extends CommonBasicMapper<TbPfund, TvPfund>, MultiTableMapper<TbPfund, TvPfund> {
    int count(@Param("key") String key);

    List<TvSearchPfundInfo> selectSearchPfundInfoList(@Param("limitStart")Integer limitStart, @Param("limitEnd")Integer limitEnd, @Param("key") String key, @Param("orderBy") String orderBy, Long userId);

    TvSearchPfundInfo getNavDetail(@Param("securityId")Integer securityId);
}