package com.rong.fundmanage.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 标签产品
 * @author      : lether
 * @createDate  : 2020-02-20
 */
@Table("`tb_product_label`")
@Data()
@Accessors(chain = true)
public class TbProductLabel extends BaseEntity<TbProductLabel> {
    /**
     * 产品id
     */
    @Column("`security_id`")
    @ApiModelProperty("产品id")
    private Long securityId;

    /**
     * 标签id
     */
    @Column("`label_id`")
    @ApiModelProperty("标签id")
    private Long labelId;

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
    /**
     * 推荐理由
     */
    @Column("`label_reason`")
    @ApiModelProperty("推荐理由")
    private String labelReason;
    /**
     * 标签0
     */
    @Column("`label_var0`")
    @ApiModelProperty("标签0")
    private String labelVar0;
    /**
     * 标签1
     */
    @Column("`label_var1`")
    @ApiModelProperty("标签1")
    private String labelVar1;
}