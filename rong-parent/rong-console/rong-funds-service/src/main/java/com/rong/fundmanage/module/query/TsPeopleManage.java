package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import lombok.Data;

@Data
public class TsPeopleManage extends QueryInfo<TbPeopleManage> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        personId,
        visible,
        recommend,
        hotSearch,
        userId;
    }
}