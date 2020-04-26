package com.rong.usercard.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.vitily.mybatis.core.wrapper.PageInfo;
import lombok.Data;

@Data
public class TqUserCardInfo extends BaseRequest<TbUserCardInfo, TqUserCardInfo> {
    /**
     * 分页对象
     */
    public PageInfo pageInfo;
}