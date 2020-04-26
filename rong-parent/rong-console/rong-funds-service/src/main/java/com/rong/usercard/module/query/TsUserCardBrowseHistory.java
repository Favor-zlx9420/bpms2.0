package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardBrowseHistory;
import lombok.Data;

@Data
public class TsUserCardBrowseHistory extends QueryInfo<TbUserCardBrowseHistory> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        vistorUserId,
        cardInfoId;
    }
}