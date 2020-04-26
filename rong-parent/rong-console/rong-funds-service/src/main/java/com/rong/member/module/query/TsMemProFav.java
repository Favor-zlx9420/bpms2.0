package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemProFav;
import lombok.Data;

@Data
public class TsMemProFav extends QueryInfo<TbMemProFav> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        memberId,
        specParProId,
        productId;
    }
}