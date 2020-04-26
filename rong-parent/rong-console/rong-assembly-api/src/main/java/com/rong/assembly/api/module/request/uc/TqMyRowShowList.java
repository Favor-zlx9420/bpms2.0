package com.rong.assembly.api.module.request.uc;

import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.vitily.mybatis.core.wrapper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqMyRowShowList extends TqMySimpleList {
    @ApiModelProperty("路演分类")
    private Long cateId;
    @ApiModelProperty("路演标签")
    private Long labelId;
    @ApiModelProperty("审核状态（0：待审核，-1：审核失败，1：审核通过，2：再次提交）")
    private Integer state;
    PageInfo pageInfo;
}
