package com.rong.tong.fund.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.fund.module.entity.TbFundInstInfo;
import lombok.Data;

@Data
public class TqFundInstInfo extends BaseRequest<TbFundInstInfo, TqFundInstInfo> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
}