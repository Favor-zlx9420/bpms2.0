package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户标签
 * @author      : Administrator
 * @createDate  : 2020-03-03
 */
@Table("`tb_user_label`")
@Data()
@Accessors(chain = true)
public class TbUserLabel extends BaseEntity<TbUserLabel> {
    /**
     * 用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("用户id")
    private Long userId;

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
     * 自定义标签0
     */
    @Column("`label_var0`")
    @ApiModelProperty("自定义标签0")
    private String labelVar0;

    /**
     * 自定义标签1
     */
    @Column("`label_var1`")
    @ApiModelProperty("自定义标签1")
    private String labelVar1;

    /**
     * 自定义标签2
     */
    @Column("`label_var2`")
    @ApiModelProperty("自定义标签2")
    private String labelVar2;

    /**
     * 自定义标签3
     */
    @Column("`label_var3`")
    @ApiModelProperty("自定义标签3")
    private String labelVar3;
}