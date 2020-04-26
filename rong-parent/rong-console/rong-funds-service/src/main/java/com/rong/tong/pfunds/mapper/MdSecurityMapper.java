package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.view.TvMdSecurity;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MdSecurityMapper extends CommonBasicMapper<TbMdSecurity, TvMdSecurity>, MultiTableMapper<TbMdSecurity, TvMdSecurity> {
}