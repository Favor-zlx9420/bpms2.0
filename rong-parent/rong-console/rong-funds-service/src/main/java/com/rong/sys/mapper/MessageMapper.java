package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbMessage;
import com.rong.sys.module.view.TvMessage;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MessageMapper extends CommonBasicMapper<TbMessage, TvMessage>, MultiTableMapper<TbMessage, TvMessage> {
}