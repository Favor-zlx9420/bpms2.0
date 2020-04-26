package com.rong.admin.mapper;

import com.rong.admin.module.entity.TbAdmMsg;
import com.rong.admin.module.view.TvAdmMsg;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdmMsgMapper extends CommonBasicMapper<TbAdmMsg, TvAdmMsg>, MultiTableMapper<TbAdmMsg, TvAdmMsg> {
}