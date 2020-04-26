package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbAvailableBank;
import com.rong.sys.module.view.TvAvailableBank;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AvailableBankMapper extends CommonBasicMapper<TbAvailableBank, TvAvailableBank>, MultiTableMapper<TbAvailableBank, TvAvailableBank> {
}