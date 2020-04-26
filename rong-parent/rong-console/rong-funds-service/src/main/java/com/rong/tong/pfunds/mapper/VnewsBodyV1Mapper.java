package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbVnewsBodyV1;
import com.rong.tong.pfunds.module.view.TvVnewsBodyV1;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface VnewsBodyV1Mapper extends CommonBasicMapper<TbVnewsBodyV1, TvVnewsBodyV1>, MultiTableMapper<TbVnewsBodyV1, TvVnewsBodyV1> {
}