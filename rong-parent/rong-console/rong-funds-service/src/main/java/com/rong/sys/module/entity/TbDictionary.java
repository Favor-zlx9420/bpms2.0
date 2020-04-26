package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 字典表
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_dictionary`")
@Data()
@Accessors(chain = true)
public class TbDictionary extends BaseEntity<TbDictionary> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 类型
     */
    @Column("`type`")
    private Integer type;

    /**
     * 键
     */
    @Column("`key`")
    private String key;

    /**
     * 值
     */
    @Column("`value`")
    private String value;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;

    /**
     * 值类型
     */
    @Column("`value_type`")
    private String valueType;
}