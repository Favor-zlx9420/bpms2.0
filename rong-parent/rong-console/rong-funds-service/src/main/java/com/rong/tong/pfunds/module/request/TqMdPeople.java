package com.rong.tong.pfunds.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import lombok.Data;

@Data
public class TqMdPeople extends BaseRequest<TbMdPeople, TqMdPeople> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
    private Long bindUserId;
}