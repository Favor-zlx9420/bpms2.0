package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-17 10:08
 **/
@Data
public class TvHostSearch {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "热搜词")
    private String hostSearch;

    @ApiModelProperty(value = "类型(1基金2公司3经理)")
    private Integer type;
}
