package com.rong.cms.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : cms分类
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_cms_category`")
@Data()
@Accessors(chain = true)
public class TbCmsCategory extends BaseEntity<TbCmsCategory> {
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
     * 类型id
     */
    @Column("`type_id`")
    private Long typeId;

    /**
     * 父级id
     */
    @Column("`parent_id`")
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
    private String keyword;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;
}