package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户预约表
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_user_reservation`")
@Data()
@Accessors(chain = true)
public class TbUserReservation extends BaseEntity<TbUserReservation> {
    /**
     * 预约用户姓名
     */
    @ApiModelProperty("预约用户姓名")
    @Column("`name`")
    private String name;

    /**
     * 用户手机
     */
    @ApiModelProperty("预约用户手机")
    @Column("`phone`")
    private String phone;

    /**
     * 处理状态（0：未处理，1：已处理）
     */
    @ApiModelProperty("处理状态（0：未处理，1：已处理）")
    @Column("`deal_status`")
    private Integer dealStatus;

    /**
     * 目标id
     */
    @ApiModelProperty("目标id")
    @Column("`target_id`")
    private Long targetId;

    /**
     * 预约类型（0：机构；1：基金经理；2：产品）
     */
    @ApiModelProperty("预约类型（0：机构；1：基金经理；2：产品）")
    @Column("`type`")
    private Integer type;

    /**
     * 预约用户id（如果有的话），关联tb_mem_base表主键
     */
    @ApiModelProperty("预约用户id（如果有的话）")
    @Column("`reservation_user_id`")
    private Long reservationUserId;

    /**
     * 处理用户id，一般为客服id，关联tb_mem_base表主键
     */
    @ApiModelProperty("处理用户id,一般为客服id")
    @Column("`dual_user_id`")
    private Long dualUserId;

    /**
     * 回复信息
     */
    @ApiModelProperty("回复信息")
    @Column("`reply_content`")
    private String replyContent;

}