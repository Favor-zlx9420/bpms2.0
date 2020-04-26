package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 个人信息管理模块
 * @author      : Administrator
 * @createDate  : 2020-03-10
 */
@Table("`tb_user_card_module`")
@Data()
@Accessors(chain = true)
public class TbUserCardModule extends BaseEntity<TbUserCardModule> {
    /**
     * 排序：越小越在前面
     */
    @Column("`sort`")
    @ApiModelProperty("排序：越小越在前面")
    private BigDecimal sort;

    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 类型（0：个人简介，1：工作经历，2：教育经历，3：个人荣誉，4：我的产品，5：我的相册，6：自定义模块）
     */
    @Column("`type`")
    @ApiModelProperty("类型（0：个人简介，1：工作经历，2：教育经历，3：个人荣誉，4：我的产品，5：我的相册，6：自定义模块）")
    private Integer type;

    @Column("`title`")
    @ApiModelProperty("模块标题")
    private String title;

    @Column("`content`")
    @ApiModelProperty("模块内容")
    private String content;

}