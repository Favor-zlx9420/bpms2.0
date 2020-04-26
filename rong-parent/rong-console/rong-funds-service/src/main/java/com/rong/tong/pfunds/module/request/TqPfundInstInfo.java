package com.rong.tong.pfunds.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import lombok.Data;

@Data
public class TqPfundInstInfo extends BaseRequest<TbPfundInstInfo, TqPfundInstInfo> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
}