package com.rong.assembly.api.mapper;

import com.rong.tong.pfunds.module.view.TvNewsCate;
import com.vitily.mybatis.core.provider.MultiSqlProvider;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CmsMapper {
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TvNewsCate> cmsCateList(MultiTableQueryWrapper wrapper);

}
