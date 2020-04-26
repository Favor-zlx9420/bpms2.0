package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserEmploymentCert;
import com.rong.user.module.view.TvUserEmploymentCert;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserEmploymentCertMapper extends CommonBasicMapper<TbUserEmploymentCert, TvUserEmploymentCert>, MultiTableMapper<TbUserEmploymentCert, TvUserEmploymentCert> {
}