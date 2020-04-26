package com.rong.assembly.api.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-14 16:10
 **/
@Data
public class TqSearch extends TqPageListBase {
    @ApiModelProperty(value = "搜索关键字", name = "key", required = true)
    private String key;
}
