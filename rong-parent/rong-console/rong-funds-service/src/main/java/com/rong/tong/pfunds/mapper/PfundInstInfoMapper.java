package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvSearchPfundInstInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PfundInstInfoMapper extends CommonBasicMapper<TbPfundInstInfo, TvPfundInstInfo>, MultiTableMapper<TbPfundInstInfo, TvPfundInstInfo> {
    int count(@Param("key") String key);

    List<TvSearchPfundInstInfo> selectSearchPfundInstInfoList(@Param("limitStart")Integer limitStart, @Param("limitEnd")Integer limitEnd, @Param("key") String key, @Param("orderBy") String orderBy);

    TvSearchPfundInstInfo getTotalReturn(@Param("partyId")Integer partyId);
}