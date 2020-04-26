package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 名片分享标题
 * @author      : Administrator
 * @createDate  : 2020-03-10
 */
@Table("`tb_user_card_share_title`")
@Data()
@Accessors(chain = true)
public class TbUserCardShareTitle extends BaseEntity<TbUserCardShareTitle> {
    /**
     * 状态（0：未使用，1：使用中）
     */
    @Column("`state`")
    @ApiModelProperty("状态（0：未使用，1：使用中）")
    private Integer state;
    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 标题内容
     */
    @Column("`title`")
    @ApiModelProperty("标题内容")
    private String title;
}