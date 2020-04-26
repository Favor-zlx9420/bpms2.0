package com.rong.user.service;

import com.rong.common.service.BasicService;
import com.rong.user.module.entity.TbMessageHistory;
import com.rong.user.module.request.TqMessageHistory;
import com.rong.user.module.view.TvMessageHistory;

public interface MessageHistoryService extends BasicService<TbMessageHistory, TqMessageHistory, TvMessageHistory> {
}