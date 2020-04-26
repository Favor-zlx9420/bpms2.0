package com.rong.assembly.api.util;

import com.rong.common.util.CommonUtil;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.SelectAlias;

public class SortUtil {
    public static void setOrgSort(MultiTableQueryWrapper queryWrapper, PageInfo pageInfo){
        if(CommonUtil.isNormalSql(pageInfo.getSortField())){
            queryWrapper.orderBy(OrderBy.valueOf(Order.valueOf(pageInfo.getSortDistanct().toUpperCase()),SelectAlias.valueOf(pageInfo.getSortField(),true)));
        }
    }
}
