package com.rong.roadshow.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 路演热门企业
 * @author      : lether
 * @createDate  : 2020-02-17
 */
@Table("`tb_roadshow_hot_org`")
@Data()
@Accessors(chain = true)
public class TbRoadShowHotOrg extends BaseEntity<TbRoadShowHotOrg> {
    /**
     * 排序
     */
    @Column("`sort`")
    @ApiModelProperty("排序")
    private BigDecimal sort;

    /**
     * （是否可见：0不可见，1可见）
     */
    @Column("`visible`")
    @ApiModelProperty("（是否可见：0不可见，1可见）")
    private Boolean visible;

    /**
     * 企业id
     */
    @Column("`party_id`")
    @ApiModelProperty("企业id")
    private Long partyId;
}