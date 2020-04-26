package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.MdSecurityMapper;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.request.TqMdSecurity;
import com.rong.tong.pfunds.module.view.TvMdSecurity;
import com.rong.tong.pfunds.service.MdSecurityService;
import org.springframework.stereotype.Service;

@Service
public class MdSecurityServiceImpl extends FundsBasicServiceImpl<TbMdSecurity, TqMdSecurity, TvMdSecurity, MdSecurityMapper> implements MdSecurityService {
}