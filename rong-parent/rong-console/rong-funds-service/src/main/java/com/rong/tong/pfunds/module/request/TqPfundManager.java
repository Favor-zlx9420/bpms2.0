package com.rong.tong.pfunds.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import lombok.Data;

@Data
public class TqPfundManager extends BaseRequest<TbPfundManager, TqPfundManager> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
    private Long bindUserId;
}