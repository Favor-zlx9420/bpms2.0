package com.rong.tong.pfunds.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TqVnewsContentV1 extends BaseRequest<TbVnewsContentV1, TqVnewsContentV1> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
    private BigDecimal sort;
}