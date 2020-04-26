package com.rong.assembly.api.mapper;

import com.rong.assembly.api.module.response.org.TrRespOrg;
import com.rong.assembly.api.module.response.store.TrStoreDesign;
import com.rong.assembly.api.module.response.store.TrStoreInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import com.vitily.mybatis.core.provider.MultiSqlProvider;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RespOrgInfoMapper extends MultiTableMapper<TrRespOrg,TrRespOrg> {
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrStoreDesign> selectStoreDesignList(MultiTableQueryWrapper wrapper);

    @SelectProvider(type = MultiSqlProvider.class,method = "selectOneView")
    TrStoreInfo selectStoreInfo(MultiTableQueryWrapper wrapper);

}
