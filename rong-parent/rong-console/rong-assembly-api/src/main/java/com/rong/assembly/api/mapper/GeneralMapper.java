package com.rong.assembly.api.mapper;

import com.vitily.mybatis.core.provider.MultiSqlProvider;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface GeneralMapper {
    @SelectProvider(type = MultiSqlProvider.class,method = "selectMultiTableCount")
    int count(MultiTableQueryWrapper wrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<Long> selectLongList(MultiTableQueryWrapper wrapper);

}
