package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import com.rong.fundmanage.module.view.TvPeopleManage;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PeopleManageMapper extends CommonBasicMapper<TbPeopleManage, TvPeopleManage>, MultiTableMapper<TbPeopleManage, TvPeopleManage> {
}