package com.rong.roadshow.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import lombok.Data;

@Data
public class TqRoadShowInfo extends BaseRequest<TbRoadShowInfo, TqRoadShowInfo> {
    private String rejectReason;
}