package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardAlbumMapper;
import com.rong.usercard.module.entity.TbUserCardAlbum;
import com.rong.usercard.module.request.TqUserCardAlbum;
import com.rong.usercard.module.view.TvUserCardAlbum;
import com.rong.usercard.service.UserCardAlbumService;
import org.springframework.stereotype.Service;

@Service
public class UserCardAlbumServiceImpl extends BasicServiceImpl<TbUserCardAlbum, TqUserCardAlbum, TvUserCardAlbum, UserCardAlbumMapper> implements UserCardAlbumService {
}