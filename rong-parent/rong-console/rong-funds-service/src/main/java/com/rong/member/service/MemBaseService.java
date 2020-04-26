package com.rong.member.service;

import com.rong.common.service.BasicService;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.request.TqMemBase;
import com.rong.member.module.view.TvMemBase;

public interface MemBaseService extends BasicService<TbMemBase, TqMemBase, TvMemBase> {
    String encryPassword(TbMemBase entity, String customerPassword);
    String createRecommendCode(TbMemBase entity);
}