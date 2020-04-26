package com.rong.cms.module.view;

import com.rong.cms.module.entity.TbCmsDynproVal;
import lombok.Data;

@Data
public class TvCmsDynproVal extends TbCmsDynproVal {
    /**
     * 属性名称
     */
    private String proName;
    /**
     * 属性名
     */
    private String proValue;
    /**
     * 类型名称
     */
    private Integer htmlType;
}