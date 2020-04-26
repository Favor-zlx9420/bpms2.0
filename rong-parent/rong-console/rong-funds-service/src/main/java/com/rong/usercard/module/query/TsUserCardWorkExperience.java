package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardWorkExperience;
import lombok.Data;

@Data
public class TsUserCardWorkExperience extends QueryInfo<TbUserCardWorkExperience> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        company,
        post,
        acceDate,
        dimiDate,
        remark;
    }
}