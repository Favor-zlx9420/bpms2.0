package com.rong.usercard.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rong.common.module.BaseEntity;
import com.rong.common.util.DateUtil;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 名片教育经历表
 * @author      : Administrator
 * @createDate  : 2020-03-09
 */
@Table("`tb_user_card_education_experience`")
@Data()
@Accessors(chain = true)
public class TbUserCardEducationExperience extends BaseEntity<TbUserCardEducationExperience> {
    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 学校
     */
    @Column("`school`")
    @ApiModelProperty("学校")
    private String school;

    /**
     * 专业
     */
    @Column("`major`")
    @ApiModelProperty("专业")
    private String major;

    /**
     * 学历0：其他;1：大专;2：本科;3：硕士;4：博士
     */
    @Column("`education`")
    @ApiModelProperty("学历0：其他;1：大专;2：本科;3：硕士;4：博士")
    private Integer education;

    /**
     * 开始时间
     */
    @Column("`acce_date`")
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = DateUtil.yyyy_MM_EN)
    private Date acceDate;

    /**
     * 结束时间
     */
    @Column("`dimi_date`")
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = DateUtil.yyyy_MM_EN)
    private Date dimiDate;

    /**
     * 说明
     */
    @Column("`remark`")
    @ApiModelProperty("说明")
    private String remark;
}