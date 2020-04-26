package com.rong.assembly.api.mapper;

import com.rong.assembly.api.module.response.product.TrFavFund;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.assembly.api.module.response.product.TrRepSecurity;
import com.rong.assembly.api.module.response.product.TrSummaryNav;
import com.rong.tong.pfunds.module.view.TvHisNav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import com.vitily.mybatis.core.provider.MultiSqlProvider;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RespTrFundsMapper extends MultiTableMapper<TrFunds,TrFunds> {
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TvHisNav> selectHisNavList(MultiTableQueryWrapper queryWrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectOneView")
    TvHisNav selectHisNavOne(MultiTableQueryWrapper queryWrapper);

    @SelectProvider(type = MultiSqlProvider.class,method = "selectOneView")
    TrSummaryNav selectSummaryNav(MultiTableQueryWrapper queryWrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrFavFund> selectFavFunds(MultiTableQueryWrapper queryWrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrRepSecurity> selectRepFunds(MultiTableQueryWrapper queryWrapper);

}
