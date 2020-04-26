package com.rong.common.module;

import com.vitily.mybatis.core.wrapper.PageInfo;
import lombok.Data;

@Data
public class TqPageListBase extends TqBase{
    private PageInfo pageInfo = new PageInfo();
}
