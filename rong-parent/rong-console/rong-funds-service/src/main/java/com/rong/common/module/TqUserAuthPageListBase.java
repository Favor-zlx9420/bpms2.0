package com.rong.common.module;

import com.vitily.mybatis.core.wrapper.PageInfo;
import lombok.Data;

@Data
public class TqUserAuthPageListBase extends TqUserAuthBase{
    private PageInfo pageInfo = new PageInfo();
}
