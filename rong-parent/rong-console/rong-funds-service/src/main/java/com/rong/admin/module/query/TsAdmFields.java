package com.rong.admin.module.query;

import com.rong.admin.module.entity.TbAdmFields;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAdmFields extends QueryInfo<TbAdmFields> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        admUserId,
        columnId,
        tableName,
        tableAlias,
        tableHeader,
        fieldName,
        visibleTemplate,
        rowWidth,
        sortable,
        rowGroup,
        colGroup,
        fixed;
    }
}