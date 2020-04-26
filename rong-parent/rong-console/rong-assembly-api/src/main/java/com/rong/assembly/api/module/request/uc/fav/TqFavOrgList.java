package com.rong.assembly.api.module.request.uc.fav;

import com.rong.common.module.TqUserAuthBase;
import com.vitily.mybatis.core.wrapper.PageInfo;
import lombok.Data;

@Data
public class TqFavOrgList extends TqUserAuthBase {
    PageInfo pageInfo;
}
