package com.rong.store.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 直营店关联标签
 * @author      : Administrator
 * @createDate  : 2020-02-27
 */
@Table("`tb_direct_store_org_label`")
@Data()
@Accessors(chain = true)
public class TbDirectStoreOrgLabel extends BaseEntity<TbDirectStoreOrgLabel> {
    /**
     * 产品id
     */
    @Column("`party_id`")
    @ApiModelProperty("产品id")
    private Long partyId;

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
}