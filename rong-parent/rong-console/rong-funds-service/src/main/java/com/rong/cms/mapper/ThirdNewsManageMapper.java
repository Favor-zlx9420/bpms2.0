package com.rong.cms.mapper;

import com.rong.cms.module.entity.TbThirdNewsManage;
import com.rong.cms.module.view.TvThirdNewsManage;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface ThirdNewsManageMapper extends CommonBasicMapper<TbThirdNewsManage, TvThirdNewsManage>, MultiTableMapper<TbThirdNewsManage, TvThirdNewsManage> {
}