package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemProFav;
import com.rong.member.module.request.TqMemProFav;
import com.rong.member.module.view.TvMemProFav;
import com.rong.member.mapper.MemProFavMapper;
import com.rong.member.service.MemProFavService;
import org.springframework.stereotype.Component;

@Component
public class MemProFavServiceImpl extends BasicServiceImpl<TbMemProFav, TqMemProFav, TvMemProFav, MemProFavMapper> implements MemProFavService {
}