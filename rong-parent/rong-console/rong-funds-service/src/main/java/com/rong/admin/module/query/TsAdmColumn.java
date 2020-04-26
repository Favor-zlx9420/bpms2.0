package com.rong.admin.module.query;

import com.rong.admin.module.entity.TbAdmColumn;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAdmColumn extends QueryInfo<TbAdmColumn> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        parentId,
        name,
        urlLink,
        isBtn,
        rightUrls,
        icon,
        visible;
    }
}