package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardWorkExperience;
import com.rong.usercard.module.view.TvUserCardWorkExperience;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardWorkExperienceMapper extends CommonBasicMapper<TbUserCardWorkExperience, TvUserCardWorkExperience>, MultiTableMapper<TbUserCardWorkExperience, TvUserCardWorkExperience> {
}