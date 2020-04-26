package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员分组
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_group`")
@Data()
@Accessors(chain = true)
public class TbMemGroup extends BaseEntity<TbMemGroup> {
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
     * 分组名称
     */
    @Column("`name`")
    private String name;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;

    /**
     * 默认状态
     */
    @Column("`defaulted`")
    private Boolean defaulted;

    /**
     * 享受价格百分比
     */
    @Column("`percent`")
    private BigDecimal percent;
}