package com.rong.store.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 直营店入驻机构
 * @author      : lether
 * @createDate  : 2020-02-11
 */
@Table("`tb_direct_store_org`")
@Data()
@Accessors(chain = true)
public class TbDirectStoreOrg extends BaseEntity<TbDirectStoreOrg> {
    /**
     * 机构id
     */
    @Column("`party_id`")
    @ApiModelProperty("机构id")
    private Long partyId;
    /**
     * 直营店用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("直营店用户id")
    private Long userId;

    /**
     * （0私募，1信托，2公募）
     */
    @Column("`type`")
    @ApiModelProperty("（0私募，1信托，2公募）")
    private Integer type;

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

    /**
     * 备注
     */
    @Column("`remark`")
    @ApiModelProperty("备注")
    private String remark;

}