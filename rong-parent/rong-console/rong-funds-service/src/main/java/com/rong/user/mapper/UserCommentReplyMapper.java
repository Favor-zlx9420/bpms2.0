package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserCommentReply;
import com.rong.user.module.view.TvUserCommentReply;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCommentReplyMapper extends CommonBasicMapper<TbUserCommentReply, TvUserCommentReply>, MultiTableMapper<TbUserCommentReply, TvUserCommentReply> {
}