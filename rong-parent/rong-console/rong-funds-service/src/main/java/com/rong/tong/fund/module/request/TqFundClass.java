package com.rong.tong.fund.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.fund.module.entity.TbFundClass;
import lombok.Data;

@Data
public class TqFundClass extends BaseRequest<TbFundClass, TqFundClass> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
}