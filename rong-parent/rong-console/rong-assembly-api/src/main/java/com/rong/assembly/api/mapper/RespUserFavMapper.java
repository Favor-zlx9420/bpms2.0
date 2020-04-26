package com.rong.assembly.api.mapper;

import com.rong.assembly.api.module.response.TrFavUser;
import com.vitily.mybatis.core.provider.MultiSqlProvider;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RespUserFavMapper {
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrFavUser> selectFavUsers(MultiTableQueryWrapper wrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectMultiTableCount")
    int selectMultiTableCount(MultiTableQueryWrapper wrapper);
}
