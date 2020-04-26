package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardCustomModel;
import lombok.Data;

@Data
public class TsUserCardCustomModel extends QueryInfo<TbUserCardCustomModel> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        modelTitle,
        modelContent,
        remark;
    }
}