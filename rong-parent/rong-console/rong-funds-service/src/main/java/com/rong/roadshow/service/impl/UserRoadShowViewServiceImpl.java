package com.rong.roadshow.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.roadshow.mapper.UserRoadShowViewMapper;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.entity.TbUserRoadShowView;
import com.rong.roadshow.module.request.TqUserRoadShowView;
import com.rong.roadshow.module.view.TvUserRoadShowView;
import com.rong.roadshow.service.UserRoadShowViewService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class UserRoadShowViewServiceImpl extends BasicServiceImpl<TbUserRoadShowView, TqUserRoadShowView, TvUserRoadShowView, UserRoadShowViewMapper> implements UserRoadShowViewService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .select("e.userId,e.roadShowId,rs.title,rs.showDate,rs.presenter,rs.coverImageUrl,rs.viewUsers,rs.endDate,rs.showDuration")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRoadShowInfo.class,"rs"), x->
                        x.eqc(CompareAlias.valueOf("e.roadShowId"),CompareAlias.valueOf("rs.id"))
                )
                ;
    }
}