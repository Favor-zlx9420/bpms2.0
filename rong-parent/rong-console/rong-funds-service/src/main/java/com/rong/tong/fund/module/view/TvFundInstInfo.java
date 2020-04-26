package com.rong.tong.fund.module.view;

import com.rong.tong.fund.module.entity.TbFundInstInfo;
import lombok.Data;

@Data
public class TvFundInstInfo extends TbFundInstInfo {
    private Boolean visible = Boolean.TRUE;
    private Boolean recommend = Boolean.FALSE;
    private Boolean hotSearch = Boolean.FALSE;
    private String userName;
    private String realName;
}