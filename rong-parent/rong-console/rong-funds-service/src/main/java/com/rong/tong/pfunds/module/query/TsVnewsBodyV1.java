package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbVnewsBodyV1;
import lombok.Data;

@Data
public class TsVnewsBodyV1 extends QueryInfo<TbVnewsBodyV1> {

    public enum Fields {
        newsId,
        insertTime,
        updateTime,
        tmstamp,
        newsBody;
    }
}