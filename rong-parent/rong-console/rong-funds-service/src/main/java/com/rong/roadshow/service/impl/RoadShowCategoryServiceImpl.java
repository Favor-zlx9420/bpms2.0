package com.rong.roadshow.service.impl;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.query.TsAdmUser;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.roadshow.mapper.RoadShowCategoryMapper;
import com.rong.roadshow.module.entity.TbRoadShowCategory;
import com.rong.roadshow.module.query.TsRoadShowCategory;
import com.rong.roadshow.module.request.TqRoadShowCategory;
import com.rong.roadshow.module.view.TvRoadShowCategory;
import com.rong.roadshow.service.RoadShowCategoryService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Service;

@Service
public class RoadShowCategoryServiceImpl extends BasicServiceImpl<TbRoadShowCategory, TqRoadShowCategory, TvRoadShowCategory, RoadShowCategoryMapper> implements RoadShowCategoryService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper(){
        SelectAlias[] facs = new SelectAlias[TsRoadShowCategory.Fields.values().length];
        int i = 0;
        for(TsRoadShowCategory.Fields f:TsRoadShowCategory.Fields.values()){
            facs[i++] = SelectAlias.valueOf(f, ConstValue.MAIN_ALIAS);
        }
        return new MultiTableQueryWrapper()
                .select0(facs)
                .select("a.userName createName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbAdmUser.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsAdmUser.Fields.id,"a"),
                                CompareAlias.valueOf(TsRoadShowCategory.Fields.createBy,"e")))
                ;
    }
}