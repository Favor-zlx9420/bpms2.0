package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemCompanyUserinfo;
import com.rong.member.module.request.TqMemCompanyUserinfo;
import com.rong.member.module.view.TvMemCompanyUserinfo;
import com.rong.member.mapper.MemCompanyUserinfoMapper;
import com.rong.member.service.MemCompanyUserinfoService;
import org.springframework.stereotype.Component;

@Component
public class MemCompanyUserinfoServiceImpl extends BasicServiceImpl<TbMemCompanyUserinfo, TqMemCompanyUserinfo, TvMemCompanyUserinfo, MemCompanyUserinfoMapper> implements MemCompanyUserinfoService {
}