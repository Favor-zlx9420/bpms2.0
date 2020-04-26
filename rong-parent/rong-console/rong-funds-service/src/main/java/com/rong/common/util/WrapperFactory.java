package com.rong.common.util;

import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.TableInfo;

public class WrapperFactory {
    public static QueryWrapper queryWrapper(){
        return new QueryWrapper();
    }
    public static QueryWrapper queryWrapper(Class<?> tbClass){
        return new QueryWrapper(ClassAssociateTableInfo.valueOf(tbClass));
    }
    public static MultiTableQueryWrapper multiQueryWrapper(){
        return new MultiTableQueryWrapper();
    }
    public static MultiTableQueryWrapper multiQueryWrapper(Class<?> tbClass){
        return new MultiTableQueryWrapper(ClassAssociateTableInfo.valueOf(tbClass));
    }
    public static MultiTableQueryWrapper multiQueryWrapper(Class<?> tbClass,String alias){
        return new MultiTableQueryWrapper(ClassAssociateTableInfo.valueOf(tbClass,alias));
    }
    public static UpdateWrapper updateWrapper(){
        return new UpdateWrapper();
    }
}
