package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemPersonalUserinfo;
import com.rong.member.module.request.TqMemPersonalUserinfo;
import com.rong.member.module.view.TvMemPersonalUserinfo;
import com.rong.member.mapper.MemPersonalUserinfoMapper;
import com.rong.member.service.MemPersonalUserinfoService;
import org.springframework.stereotype.Component;

@Component
public class MemPersonalUserinfoServiceImpl extends BasicServiceImpl<TbMemPersonalUserinfo, TqMemPersonalUserinfo, TvMemPersonalUserinfo, MemPersonalUserinfoMapper> implements MemPersonalUserinfoService {
}