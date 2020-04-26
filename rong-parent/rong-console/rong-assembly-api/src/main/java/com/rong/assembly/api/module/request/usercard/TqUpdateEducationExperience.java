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
public class TqUpdateEducationExperience extends TqUserAuthBase {

    @Data
    public static class EducationExperience{
        /**
         * id
         */
        @ApiModelProperty("id，如果是添加，此项为空，如果是编辑，则必填")
        private Long id;

        /**
         * 学校
         */
        @ApiModelProperty("学校")
        private String school;

        /**
         * 专业
         */
        @ApiModelProperty("专业")
        private String major;

        /**
         * 学历0：其他;1：大专;2：本科;3：硕士;4：博士
         */
        @ApiModelProperty("学历0：其他;1：大专;2：本科;3：硕士;4：博士")
        private Integer education;

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
    @ApiModelProperty(value = "更新教育经历列表",required = true)
    @RequireValidator
    private List<EducationExperience> educationExperience;
}
