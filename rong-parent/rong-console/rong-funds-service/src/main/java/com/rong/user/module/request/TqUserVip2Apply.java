package com.rong.user.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.user.module.entity.TbUserVip2Apply;
import lombok.Data;

import java.util.Date;

@Data
public class TqUserVip2Apply extends BaseRequest<TbUserVip2Apply, TqUserVip2Apply> {
    private Date endDate;
}