package com.rong.assembly.api.module.request.usercard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import com.rong.common.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TqUpdateWorkExperience extends TqUserAuthBase {

    @Data
    public static class WorkExperience{
        /**
         * id
         */
        @ApiModelProperty("id，如果是添加，此项为空，如果是编辑，则必填")
        private Long id;

        /**
         * 公司/企业
         */
        @ApiModelProperty("公司/企业")
        @RequireValidator
        private String company;

        /**
         * 职务
         */
        @ApiModelProperty("职务")
        private String post;

        /**
         * 开始时间
         */
        @JsonFormat(pattern = DateUtil.yyyy_MM_EN)
        @ApiModelProperty("开始时间")
        private Date acceDate;

        /**
         * 结束时间
         */
        @JsonFormat(pattern = DateUtil.yyyy_MM_EN)
        @ApiModelProperty("结束时间")
        private Date dimiDate;

        /**
         * 说明
         */
        @ApiModelProperty("说明")
        private String remark;
    }
    @ApiModelProperty(value = "更新工作经历列表",required = true)
    @RequireValidator
    private List<WorkExperience> workExperience;
}
