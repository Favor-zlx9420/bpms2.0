package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 标签表
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_label`")
@Data()
@Accessors(chain = true)
public class TbLabel extends BaseEntity<TbLabel> {
    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 标签名
     */
    @Column("`name`")
    private String name;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;

    /**
     * 类型
     */
    @Column("`type`")
    private Integer type;
}