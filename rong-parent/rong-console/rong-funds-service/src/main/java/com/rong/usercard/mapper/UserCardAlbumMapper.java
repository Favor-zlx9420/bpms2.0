package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardAlbum;
import com.rong.usercard.module.view.TvUserCardAlbum;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardAlbumMapper extends CommonBasicMapper<TbUserCardAlbum, TvUserCardAlbum>, MultiTableMapper<TbUserCardAlbum, TvUserCardAlbum> {
}