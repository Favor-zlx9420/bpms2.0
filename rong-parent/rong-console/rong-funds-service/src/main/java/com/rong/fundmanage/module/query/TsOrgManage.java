package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbOrgManage;
import lombok.Data;

@Data
public class TsOrgManage extends QueryInfo<TbOrgManage> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        partyId,
        visible,
        recommend,
        hotSearch,
        type
    }
}