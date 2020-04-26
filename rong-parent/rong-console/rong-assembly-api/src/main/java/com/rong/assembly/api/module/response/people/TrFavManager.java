package com.rong.assembly.api.module.response.people;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户感兴趣的基金经理
 */
@Data
public class TrFavManager extends TrManager {
    @ApiModelProperty("所在公司简称,以'、'隔开")
    private String companyNames;
    @ApiModelProperty("所在公司职位,以'、'隔开")
    private String companyPosts;
}
