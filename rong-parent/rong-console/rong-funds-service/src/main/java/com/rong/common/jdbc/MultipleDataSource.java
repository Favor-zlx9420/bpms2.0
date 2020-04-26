package com.rong.common.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();
    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }/**
     * 获取数据源
     * @return
     */
    public static String getDatasource() {
        return dataSourceKey.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource(){
        dataSourceKey.remove();
    }
}
