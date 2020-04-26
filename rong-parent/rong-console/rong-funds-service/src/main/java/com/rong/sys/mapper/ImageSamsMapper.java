package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbImageSams;
import com.rong.sys.module.view.TvImageSams;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface ImageSamsMapper extends CommonBasicMapper<TbImageSams, TvImageSams>, MultiTableMapper<TbImageSams, TvImageSams> {
}