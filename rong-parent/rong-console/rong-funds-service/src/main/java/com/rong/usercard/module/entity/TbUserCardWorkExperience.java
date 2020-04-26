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
 * @description : 名片工作经历表
 * @author      : Administrator
 * @createDate  : 2020-03-09
 */
@Table("`tb_user_card_work_experience`")
@Data()
@Accessors(chain = true)
public class TbUserCardWorkExperience extends BaseEntity<TbUserCardWorkExperience> {
    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 公司/企业
     */
    @Column("`company`")
    @ApiModelProperty("公司/企业")
    private String company;

    /**
     * 职务
     */
    @Column("`post`")
    @ApiModelProperty("职务")
    private String post;

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