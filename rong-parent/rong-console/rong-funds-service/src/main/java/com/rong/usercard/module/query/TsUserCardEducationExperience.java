package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardEducationExperience;
import lombok.Data;

@Data
public class TsUserCardEducationExperience extends QueryInfo<TbUserCardEducationExperience> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        school,
        major,
        education,
        acceDate,
        dimiDate,
        remark;
    }
}