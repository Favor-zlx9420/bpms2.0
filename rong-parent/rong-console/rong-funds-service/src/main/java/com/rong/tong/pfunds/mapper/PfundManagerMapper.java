package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import com.rong.tong.pfunds.module.view.TvPfundManager;
import com.rong.tong.pfunds.module.view.TvSearchPfundManager;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PfundManagerMapper extends CommonBasicMapper<TbPfundManager, TvPfundManager>, MultiTableMapper<TbPfundManager, TvPfundManager> {
    int count(@Param("key") String key);

    List<TvSearchPfundManager> selectSearchPfundManagerList(@Param("limitStart")Integer limitStart, @Param("limitEnd")Integer limitEnd, @Param("key") String key, @Param("orderBy") String orderBy);

    TvSearchPfundManager getTotalReturn(@Param("personId")Integer personId);
}