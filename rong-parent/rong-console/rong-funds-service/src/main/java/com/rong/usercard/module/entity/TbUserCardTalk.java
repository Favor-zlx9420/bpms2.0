package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 名片朋友圈说说
 * @author      : Administrator
 * @createDate  : 2020-03-10
 */
@Table("`tb_user_card_talk`")
@Data()
@Accessors(chain = true)
public class TbUserCardTalk extends BaseEntity<TbUserCardTalk> {
    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 内容
     */
    @Column("`content`")
    @ApiModelProperty("内容")
    private String content;
}