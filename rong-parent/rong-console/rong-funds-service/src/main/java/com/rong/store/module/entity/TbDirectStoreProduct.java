package com.rong.store.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 直营店入驻产品
 * @author      : lether
 * @createDate  : 2020-02-11
 */
@Table("`tb_direct_store_product`")
@Data()
@Accessors(chain = true)
public class TbDirectStoreProduct extends BaseEntity<TbDirectStoreProduct> {
    /**
     * （0私募，1信托，2公募）
     */
    @Column("`type`")
    @ApiModelProperty("（0私募，1信托，2公募）")
    private Integer type;

    /**
     * 产品id
     */
    @Column("`security_id`")
    @ApiModelProperty("产品id")
    private Long securityId;

    /**
     * 是否可见
     */
    @Column("`visible`")
    @ApiModelProperty("是否可见")
    private Boolean visible;

    /**
     * 是否推荐
     */
    @Column("`recommend`")
    @ApiModelProperty("是否推荐")
    private Boolean recommend;

    /**
     * 排序，越小在越前面
     */
    @Column("`sort`")
    @ApiModelProperty("排序，越小在越前面")
    private BigDecimal sort;
}