package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbBanner;
import com.rong.sys.module.view.TvBanner;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface BannerMapper extends CommonBasicMapper<TbBanner, TvBanner>, MultiTableMapper<TbBanner, TvBanner> {
}