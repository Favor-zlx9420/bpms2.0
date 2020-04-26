package com.rong.assembly.api.service;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;

public interface SmsHelper {
    Result send(CommonEnumContainer.TripartiteMessageContentType contentType, String phone, String... contents);
}
