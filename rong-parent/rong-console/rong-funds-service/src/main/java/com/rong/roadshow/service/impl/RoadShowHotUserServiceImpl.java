package com.rong.roadshow.service.impl;

import com.rong.common.exception.DuplicateDataException;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.roadshow.mapper.RoadShowHotUserMapper;
import com.rong.roadshow.module.entity.TbRoadShowHotUser;
import com.rong.roadshow.module.query.TsRoadShowHotUser;
import com.rong.roadshow.module.request.TqRoadShowHotUser;
import com.rong.roadshow.module.view.TvRoadShowHotUser;
import com.rong.roadshow.service.RoadShowHotUserService;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import org.springframework.stereotype.Service;

@Service
public class RoadShowHotUserServiceImpl extends BasicServiceImpl<TbRoadShowHotUser, TqRoadShowHotUser, TvRoadShowHotUser, RoadShowHotUserMapper> implements RoadShowHotUserService {


    @Override
    protected void beforeInsert(TqRoadShowHotUser req) {
        super.beforeInsert(req);
        if(mapper.selectCount(new QueryWrapper()
                .eq(TsRoadShowHotUser.Fields.presenter,req.getEntity().getPresenter())
        ) > 0){
            throw new DuplicateDataException("该用户已经存在，如果已经被删除，请使用恢复功能");
        }
    }
}