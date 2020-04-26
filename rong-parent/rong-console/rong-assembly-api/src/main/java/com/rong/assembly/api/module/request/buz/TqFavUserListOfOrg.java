package com.rong.assembly.api.module.request.buz;

import com.rong.common.module.TqBase;
import com.vitily.mybatis.core.wrapper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFavUserListOfOrg extends TqBase {
    @ApiModelProperty(value = "机构id",required = true)
    private Long partyId;
    PageInfo pageInfo;
}
