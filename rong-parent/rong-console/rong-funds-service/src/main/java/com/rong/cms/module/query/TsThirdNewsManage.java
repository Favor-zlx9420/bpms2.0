package com.rong.cms.module.query;

import com.rong.cms.module.entity.TbThirdNewsManage;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsThirdNewsManage extends QueryInfo<TbThirdNewsManage> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        newsId,
        visible,
        recommend,
        hotSearch;
    }
}