package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbAvailableBank;
import com.rong.sys.module.request.TqAvailableBank;
import com.rong.sys.module.view.TvAvailableBank;
import com.rong.sys.mapper.AvailableBankMapper;
import com.rong.sys.service.AvailableBankService;
import org.springframework.stereotype.Service;

@Service
public class AvailableBankServiceImpl extends BasicServiceImpl<TbAvailableBank, TqAvailableBank, TvAvailableBank, AvailableBankMapper> implements AvailableBankService {
}