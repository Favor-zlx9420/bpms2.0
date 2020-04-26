package com.rong.auth.module.query;

import com.rong.auth.module.entity.TbAuthRoleResources;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAuthRoleResources extends QueryInfo<TbAuthRoleResources> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        roleId,
        resourcesId;
    }
}