package com.rong.user.module.view;

import com.rong.user.module.entity.TbUserFeedBack;
import lombok.Data;

@Data
public class TvUserFeedBack extends TbUserFeedBack {
    private String submitUserName;
    private String submitUserRealName;
}