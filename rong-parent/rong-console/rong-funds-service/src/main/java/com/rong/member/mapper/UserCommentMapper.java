package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbUserComment;
import com.rong.member.module.view.TvUserComment;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCommentMapper extends CommonBasicMapper<TbUserComment, TvUserComment>, MultiTableMapper<TbUserComment, TvUserComment> {
}