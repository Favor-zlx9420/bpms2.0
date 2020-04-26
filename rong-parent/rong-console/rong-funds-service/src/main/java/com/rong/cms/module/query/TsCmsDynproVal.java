package com.rong.cms.module.query;

import com.rong.cms.module.entity.TbCmsDynproVal;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsCmsDynproVal extends QueryInfo<TbCmsDynproVal> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        newsId,
        properId,
        properName,
        value;
    }
}