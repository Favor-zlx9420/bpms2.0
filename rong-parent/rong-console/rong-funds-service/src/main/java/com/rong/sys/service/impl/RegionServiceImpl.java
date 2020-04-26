package com.rong.sys.service.impl;

import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbRegion;
import com.rong.sys.module.query.TsRegion;
import com.rong.sys.module.request.TqRegion;
import com.rong.sys.module.view.TvRegion;
import com.rong.sys.mapper.RegionMapper;
import com.rong.sys.service.RegionService;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Component;

@Component
public class RegionServiceImpl extends BasicServiceImpl<TbRegion, TqRegion, TvRegion, RegionMapper> implements RegionService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper(){
        SelectAlias[] facs = new SelectAlias[TsRegion.Fields.values().length];
        int i = 0;
        for(TsRegion.Fields f:TsRegion.Fields.values()){
            facs[i++] = SelectAlias.valueOf(f, ConstValue.MAIN_ALIAS);
        }

        return new MultiTableQueryWrapper()
                .select0(facs)
                .select0(
                        SelectAlias.valueOf("(select ifnull(max(1),0) from tb_comm_region where parent_id=e.id limit 1) hasSon",true)
                        ,
                        SelectAlias.valueOf("(select name from tb_comm_region where id=e.parent_id limit 1) parentName",true)
                )
                ;
    }
    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }
}