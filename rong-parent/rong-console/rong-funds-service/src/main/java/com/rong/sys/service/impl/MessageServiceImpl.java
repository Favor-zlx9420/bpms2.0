package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbMessage;
import com.rong.sys.module.request.TqMessage;
import com.rong.sys.module.view.TvMessage;
import com.rong.sys.mapper.MessageMapper;
import com.rong.sys.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl extends BasicServiceImpl<TbMessage, TqMessage, TvMessage, MessageMapper> implements MessageService {
}