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
public class TvDateBoundary {

    @ApiModelProperty(value = "开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
