package com.rong.roadshow.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 路演分类
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_road_show_category`")
@Data()
@Accessors(chain = true)
public class TbRoadShowCategory extends BaseEntity<TbRoadShowCategory> {
    /**
     * 使用状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 父级id
     */
    @Column("`parent_id`")
    @ApiModelProperty(hidden = true)
    private Long parentId;

    /**
     * 分类名称
     */
    @Column("`name`")
    private String name;

    /**
     * 分类关键字
     */
    @Column("`keyword`")
    @ApiModelProperty(hidden = true)
    private String keyword;

    /**
     * 描述
     */
    @Column("`description`")
    @ApiModelProperty(hidden = true)
    private String description;

    /**
     * 拥有标签列表
     */
    @Column("`label_ids`")
    private String labelIds;

    /**
     * 创建人
     */
    @Column("`create_by`")
    private Long createBy;
}