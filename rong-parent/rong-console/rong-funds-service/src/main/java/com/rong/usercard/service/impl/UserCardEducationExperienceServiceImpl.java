package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardEducationExperienceMapper;
import com.rong.usercard.module.entity.TbUserCardEducationExperience;
import com.rong.usercard.module.request.TqUserCardEducationExperience;
import com.rong.usercard.module.view.TvUserCardEducationExperience;
import com.rong.usercard.service.UserCardEducationExperienceService;
import org.springframework.stereotype.Service;

@Service
public class UserCardEducationExperienceServiceImpl extends BasicServiceImpl<TbUserCardEducationExperience, TqUserCardEducationExperience, TvUserCardEducationExperience, UserCardEducationExperienceMapper> implements UserCardEducationExperienceService {
}