package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 物流方式表
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_delivery_way`")
@Data()
@Accessors(chain = true)
public class TbDeliveryWay extends BaseEntity<TbDeliveryWay> {
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
     * 物流名称
     */
    @Column("`name`")
    private String name;

    /**
     * 物流价格
     */
    @Column("`price`")
    private BigDecimal price;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;
}