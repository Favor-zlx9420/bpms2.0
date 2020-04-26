package com.rong.roadshow.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.roadshow.mapper.UserRoadShowFavMapper;
import com.rong.roadshow.module.entity.TbRoadShowCategory;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.entity.TbUserRoadShowFav;
import com.rong.roadshow.module.query.TsRoadShowCategory;
import com.rong.roadshow.module.query.TsRoadShowInfo;
import com.rong.roadshow.module.request.TqUserRoadShowFav;
import com.rong.roadshow.module.view.TvUserRoadShowFav;
import com.rong.roadshow.service.UserRoadShowFavService;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.module.query.TsLabel;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class UserRoadShowFavServiceImpl extends BasicServiceImpl<TbUserRoadShowFav, TqUserRoadShowFav, TvUserRoadShowFav, UserRoadShowFavMapper> implements UserRoadShowFavService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("e.userId,e.roadShowId,rs.title,rs.showDate,rs.presenter,rs.coverImageUrl,rs.viewUsers,rs.showDuration")
                .select("l.name labelName,l0.name labelName0,c.name cateName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRoadShowInfo.class,"rs"),x->
                        x.eqc(CompareAlias.valueOf("e.roadShowId"),CompareAlias.valueOf("rs.id"))
                        )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbLabel.class,"l"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsLabel.Fields.id,"l"),
                                CompareAlias.valueOf(TsRoadShowInfo.Fields.labelId,"rs")))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbLabel.class,"l0"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsLabel.Fields.id,"l0"),
                                CompareAlias.valueOf(TsRoadShowInfo.Fields.labelId,"rs")))

                .leftJoin(ClassAssociateTableInfo.valueOf(TbRoadShowCategory.class,"c"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsRoadShowCategory.Fields.id,"c"),
                                CompareAlias.valueOf(TsRoadShowInfo.Fields.cateId,"rs")))

                ;
    }
}