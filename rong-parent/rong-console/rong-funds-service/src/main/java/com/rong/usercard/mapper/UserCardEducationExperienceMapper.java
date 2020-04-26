package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardEducationExperience;
import com.rong.usercard.module.view.TvUserCardEducationExperience;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardEducationExperienceMapper extends CommonBasicMapper<TbUserCardEducationExperience, TvUserCardEducationExperience>, MultiTableMapper<TbUserCardEducationExperience, TvUserCardEducationExperience> {
}