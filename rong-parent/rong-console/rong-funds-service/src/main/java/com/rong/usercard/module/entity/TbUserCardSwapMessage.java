package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 名片交换消息
 * @author      : Administrator
 * @createDate  : 2020-03-10
 */
@Table("`tb_user_card_swap_message`")
@Data()
@Accessors(chain = true)
public class TbUserCardSwapMessage extends BaseEntity<TbUserCardSwapMessage> {
    /**
     * 主动申请交换用户id
     */
    @Column("`applicant_user_id`")
    @ApiModelProperty("主动申请交换用户id")
    private Long applicantUserId;

    /**
     * 目标用户（被申请人用户）id
     */
    @Column("`target_user_id`")
    @ApiModelProperty("目标用户（被申请人用户）id")
    private Long targetUserId;

    /**
     * 申请人名片id
     */
    @Column("`applicant_card_info_id`")
    @ApiModelProperty("申请人名片id")
    private Long applicantCardInfoId;

    /**
     * 目标名片（被申请人名片）名片id
     */
    @Column("`target_card_info_id`")
    @ApiModelProperty("目标名片（被申请人名片）名片id")
    private Long targetCardInfoId;

    /**
     * 处理结果（0：未处理，1：已同意，2：目标拒绝）
     */
    @Column("`deal_result`")
    @ApiModelProperty("处理结果（0：未处理，1：已同意，2：目标拒绝）")
    private Integer dealResult;
}