package com.rong.common.util;

import com.vitily.mybatis.util.SelectAlias;

import java.util.ArrayList;
import java.util.List;

public class EnumFieldColUtil {
    public static SelectAlias[] getByEnums(Enum[] fields, String alias, Enum ...exts){
        List<SelectAlias> cols = new ArrayList<>(fields.length);
        for(Enum em:fields){
            boolean has = false;
            for(Enum ext:exts){
                if(ext == em){
                    has = true;
                    break;
                }
            }
            if(!has){
                cols.add(SelectAlias.valueOf(em,alias));
            }
        }
        return cols.toArray(new SelectAlias[0]);
    }
}
