package com.rong.tong.pfunds.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.pfunds.module.entity.TbPfund;
import lombok.Data;

import java.util.List;

@Data
public class TqPfund extends BaseRequest<TbPfund, TqPfund> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
    private List<Long> labelIds;
    /**
     * 推荐理由
     */
    private String labelReason;
    /**
     * 自定义标签0
     */
    private String labelVar0;
    /**
     * 自定义标签1
     */
    private String labelVar1;
}