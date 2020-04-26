package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户名片点赞信息（与浏览分开）
 * @author      : Administrator
 * @createDate  : 2020-03-10
 */
@Table("`tb_user_card_like`")
@Data()
@Accessors(chain = true)
public class TbUserCardLike extends BaseEntity<TbUserCardLike> {
    /**
     * 点赞用户ID
     */
    @Column("`likor_user_id`")
    @ApiModelProperty("点赞用户ID")
    private Long likorUserId;

    /**
     * 名片id
     */
    @Column("`card_info_id`")
    @ApiModelProperty("名片id")
    private Long cardInfoId;
}