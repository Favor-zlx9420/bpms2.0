package com.rong.admin.service.impl;

import com.rong.admin.mapper.AdmFieldsMapper;
import com.rong.admin.module.entity.TbAdmColumn;
import com.rong.admin.module.entity.TbAdmFields;
import com.rong.admin.module.query.TsAdmFields;
import com.rong.admin.module.request.TqAdmFields;
import com.rong.admin.module.view.TvAdmFields;
import com.rong.admin.service.AdmFieldsService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AdmFieldsServiceImpl extends BasicServiceImpl<TbAdmFields, TqAdmFields, TvAdmFields, AdmFieldsMapper> implements AdmFieldsService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("ac.name columnName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbAdmColumn.class,"ac"),ac->
                        ac.eqc(CompareAlias.valueOf("ac.id"),CompareAlias.valueOf("e.columnId"))
                        )
                ;
    }

    @Override
    public List<TvAdmFields> getFieldShowByColumnId(long columnId) {

        if(true){
            return mapper.selectViewList(new MultiTableQueryWrapper()
                    .eq(CompareAlias.valueOf(TsAdmFields.Fields.state), CommonEnumContainer.State.NORMAL.getValue())
                    .eq(CompareAlias.valueOf(TsAdmFields.Fields.deltag), CommonEnumContainer.Deltag.NORMAL.getValue())
                    .or(x->x
                            .eq(CompareAlias.valueOf(TsAdmFields.Fields.columnId),columnId)
                            .eq(CompareAlias.valueOf(TsAdmFields.Fields.columnId),0L)
                    )
                    .orderBy(OrderBy.valueOf(Order.ASC,TsAdmFields.Fields.sort))
            );
        }


        List<TvAdmFields> fieldShows = new ArrayList<>();
        List<TvAdmFields> _caches = getCacheFieldShows();
        for(TvAdmFields item:_caches){
            if(CommonUtil.isEqual(item.getColumnId(), columnId) || CommonUtil.isEqual(item.getColumnId(), 0L)){
                fieldShows.add(item);
            }
        }
        fieldShows.sort(new Comparator<TvAdmFields>() {
            @Override
            public int compare(TvAdmFields o1, TvAdmFields o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return fieldShows;
    }

    /*******************************************private*********************************************************/



    private static List<TvAdmFields> cacheFields;
    public List<TvAdmFields> getCacheFieldShows(){
        if(CommonUtil.isNull(cacheFields) || cacheFields.size() < 1){
            cacheFields =  mapper.selectViewList(new MultiTableQueryWrapper()
                    .eq(CompareAlias.valueOf(TsAdmFields.Fields.state), CommonEnumContainer.State.NORMAL.getValue())
                    .orderBy(OrderBy.valueOf(Order.ASC,TsAdmFields.Fields.sort))
            );
        }
        return cacheFields;
    }
}