package com.rong.tong.pfunds.module.view;

import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TvVnewsContentV1 extends TbVnewsContentV1 {
    private Boolean visible = Boolean.TRUE;
    private Boolean recommend = Boolean.FALSE;
    private Boolean hotSearch = Boolean.FALSE;
    private BigDecimal sort;
}