package com.rong.common.module;

import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.Sortable;
import com.vitily.mybatis.util.AliasItem;
import com.vitily.mybatis.util.CollectionUtils;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;

import java.util.Arrays;

@SuppressWarnings("unused")
public class ColumnOrderBy implements Sortable {
    private SelectAlias[] fields;
    private Order order;
    public ColumnOrderBy(Order order, SelectAlias ...fields){
        if(CollectionUtils.isEmpty(fields)){
            throw new RuntimeException("no sort field");
        }
        this.fields = fields;
        this.order = order;
    }
    public static ColumnOrderBy valueOf(Order order, SelectAlias ...fields){
        return new ColumnOrderBy(order,fields);
    }
    public static ColumnOrderBy valueOf(Order order, String fields){
        SelectAlias[] aliasItems = Arrays.stream(fields.split(",")).map(SelectAlias::valueOf).toArray(SelectAlias[]::new);
        return valueOf(order,aliasItems);
    }

    @Override
    public SelectAlias[] getSortFields() {
        return fields;
    }

    @Override
    public Order getOrder() {
        return order;
    }
}
