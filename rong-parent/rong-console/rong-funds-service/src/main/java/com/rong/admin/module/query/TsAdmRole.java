package com.rong.admin.module.query;

import com.rong.admin.module.entity.TbAdmRole;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAdmRole extends QueryInfo<TbAdmRole> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        name,
        description,
        permissionStr;
    }
}