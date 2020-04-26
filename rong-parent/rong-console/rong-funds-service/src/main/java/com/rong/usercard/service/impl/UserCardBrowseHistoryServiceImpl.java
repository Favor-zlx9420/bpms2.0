package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardBrowseHistoryMapper;
import com.rong.usercard.module.entity.TbUserCardBrowseHistory;
import com.rong.usercard.module.request.TqUserCardBrowseHistory;
import com.rong.usercard.module.view.TvUserCardBrowseHistory;
import com.rong.usercard.service.UserCardBrowseHistoryService;
import org.springframework.stereotype.Service;

@Service
public class UserCardBrowseHistoryServiceImpl extends BasicServiceImpl<TbUserCardBrowseHistory, TqUserCardBrowseHistory, TvUserCardBrowseHistory, UserCardBrowseHistoryMapper> implements UserCardBrowseHistoryService {
}