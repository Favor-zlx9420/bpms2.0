package com.rong.user.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.user.module.entity.TbUserVip1Apply;
import lombok.Data;

import java.util.Date;

@Data
public class TqUserVip1Apply extends BaseRequest<TbUserVip1Apply, TqUserVip1Apply> {
    private Date beginDate;
    private Date endDate;
}