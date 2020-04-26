package com.rong.user.module.view;

import com.rong.user.module.entity.TbUserVip2Apply;
import lombok.Data;

import java.util.Date;

@Data
public class TvUserVip2Apply extends TbUserVip2Apply {
    private String appUserName;
    private String appRealName;
    private Date endDate;
}