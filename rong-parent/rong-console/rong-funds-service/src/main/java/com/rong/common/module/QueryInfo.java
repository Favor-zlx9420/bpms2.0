package com.rong.common.module;

import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.Sortable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class QueryInfo<T> {
    T entity;
    private PageInfo pageInfo;
    private List<FieldValue> conditionElements = new ArrayList<>();
    private Set<Sortable> orders = new LinkedHashSet<>();
    public Set<Sortable> getOrders() {
        return orders;
    }
    public String getOrderStr(){
        return orders.stream().map(x->{
            return Arrays.stream(x.getSortFields()).map(
                    k->{
                        return k.getItemWithAlias() + ConstValue.BLANK + x.getOrder();
                    }
            ).collect(Collectors.joining(","));
        }).collect(Collectors.joining(","));
        //return orders.stream().collect(Collectors.joining(","));
    }
    public void addCondition(FieldValue fv){
        conditionElements.add(fv);
    }
    public void bindQueryFromRequests(Map<String,String[]> params){
        if(null == params){
            return;
        }
        if(null == conditionElements){
            conditionElements = new ArrayList<>();
        }
        Iterator<Map.Entry<String,String[]>> iterator = params.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String[]> entry = iterator.next();
            if(CommonUtil.isEmpty(entry.getValue())){
                continue;
            }
            String value = entry.getValue()[0];
            if(StringUtil.isEmpty(value)){
                continue;
            }
            String[] keys = entry.getKey().split("\\.");
            if(keys.length < 3){
                continue;
            }
            if("pageInfo".equals(keys[0]) || "orderInfo".equals(keys[0])){
                continue;
            }
            addCondition(FieldValue.fromCondition(entry.getKey(),value));
        }
    }
    public QueryInfo<T> orderBy(Sortable sortable){
        this.orders.add(sortable);
        return this;
    }
}
