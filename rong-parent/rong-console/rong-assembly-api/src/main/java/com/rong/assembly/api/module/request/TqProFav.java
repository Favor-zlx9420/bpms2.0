package com.rong.assembly.api.module.request;

import com.rong.common.module.TqUserAuthBase;
import com.vitily.mybatis.core.wrapper.PageInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TqProFav extends TqUserAuthBase {
    PageInfo pageInfo;
}
