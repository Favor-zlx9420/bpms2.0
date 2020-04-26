package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardWorkExperienceMapper;
import com.rong.usercard.module.entity.TbUserCardWorkExperience;
import com.rong.usercard.module.request.TqUserCardWorkExperience;
import com.rong.usercard.module.view.TvUserCardWorkExperience;
import com.rong.usercard.service.UserCardWorkExperienceService;
import org.springframework.stereotype.Service;

@Service
public class UserCardWorkExperienceServiceImpl extends BasicServiceImpl<TbUserCardWorkExperience, TqUserCardWorkExperience, TvUserCardWorkExperience, UserCardWorkExperienceMapper> implements UserCardWorkExperienceService {
}