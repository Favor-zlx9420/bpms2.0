package com.rong.user.module.view;

import com.rong.user.module.entity.TbMessageHistory;
import lombok.Data;

@Data
public class TvMessageHistory extends TbMessageHistory {
    private String userName;
    private String realName;
    private String dayCount;
}