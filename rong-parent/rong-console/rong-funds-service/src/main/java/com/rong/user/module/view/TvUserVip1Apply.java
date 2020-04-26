package com.rong.user.module.view;

import com.rong.user.module.entity.TbUserVip1Apply;
import lombok.Data;

import java.util.Date;

@Data
public class TvUserVip1Apply extends TbUserVip1Apply {
    private String appUserName;
    private String appRealName;
    private Date endDate;
}