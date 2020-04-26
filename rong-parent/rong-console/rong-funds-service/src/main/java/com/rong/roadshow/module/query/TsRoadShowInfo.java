package com.rong.roadshow.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import lombok.Data;

@Data
public class TsRoadShowInfo extends QueryInfo<TbRoadShowInfo> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        orgId,
        type,
        showDate,
        endDate,
        viewUsers,
        cateId,
        labelId,
        presenter,
        title,
        detail,
        coverImageUrl,
        docUrl,
        videoUrl,
        uploadUserId,
        auditUserId,
        presenterIntroduce,
        showDuration,
        labelId0
        ;
    }
}