package com.rong.cms.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 动态属性生成详情
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_cms_dyn_proper`")
@Data()
@Accessors(chain = true)
public class TbCmsDynProper extends BaseEntity<TbCmsDynProper> {
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
     * 所在类型id
     */
    @Column("`html_type`")
    private Integer htmlType;

    /**
     * 名称
     */
    @Column("`name`")
    private String name;

    /**
     * 值
     */
    @Column("`value`")
    private String value;
}