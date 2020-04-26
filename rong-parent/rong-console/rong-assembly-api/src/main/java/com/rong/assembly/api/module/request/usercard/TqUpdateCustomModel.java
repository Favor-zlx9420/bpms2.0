package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TqUpdateCustomModel extends TqUserAuthBase {

    @Data
    public static class CustomModel{
        /**
         * id
         */
        @ApiModelProperty("id，如果是添加，此项为空，如果是编辑，则必填")
        private Long id;

        /**
         * 标题
         */
        @ApiModelProperty("模块标题")
        private String modelTitle;

        /**
         * 模块内容
         */
        @ApiModelProperty("模块内容")
        private String modelContent;

        /**
         * 说明
         */
        @ApiModelProperty("说明")
        private String remark;
    }
    @ApiModelProperty(value = "更新自定义模块列表",required = true)
    @RequireValidator
    private List<CustomModel> customModels;
}
