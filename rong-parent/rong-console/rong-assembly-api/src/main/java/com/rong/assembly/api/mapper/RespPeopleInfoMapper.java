package com.rong.assembly.api.mapper;

import com.rong.assembly.api.module.response.TrCustomerServer;
import com.rong.assembly.api.module.response.TrRespUser;
import com.rong.assembly.api.module.response.people.TrFavManager;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.assembly.api.module.response.people.UserVipInfo;
import com.rong.assembly.api.module.response.user.TrUserFundAccountIndex;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import com.vitily.mybatis.core.provider.MultiSqlProvider;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RespPeopleInfoMapper extends MultiTableMapper<TrManager,TrManager> {
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrRespUser> selectTrRespUsers(MultiTableQueryWrapper wrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrFavManager> selectFavList(MultiTableQueryWrapper wrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrCustomerServer> selectCustomerUsers(MultiTableQueryWrapper wrapper);

    @SelectProvider(type = MultiSqlProvider.class,method = "selectOneView")
    TrUserFundAccountIndex selectSumFundIndex(MultiTableQueryWrapper wrapper);
    @SelectProvider(type = MultiSqlProvider.class,method = "selectViewList")
    List<TrManager> selectManager(MultiTableQueryWrapper wrapper);

    @SelectProvider(type = MultiSqlProvider.class,method = "selectOneView")
    UserVipInfo selectUserVipInfo(MultiTableQueryWrapper wrapper);

}
