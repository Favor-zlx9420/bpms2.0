package com.rong.common.mapper;

import com.rong.common.module.QueryInfo;
import com.vitily.mybatis.core.provider.MultiSqlProvider;
import com.vitily.mybatis.core.wrapper.PageList;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CommonBasicMapper<T,V>{
    int getCountPaging(QueryInfo<T> query);
    List<V> getListByBean(QueryInfo<T> query);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectPageList")
    PageList<V> selectPageListV(MultiTableQueryWrapper query);
}
