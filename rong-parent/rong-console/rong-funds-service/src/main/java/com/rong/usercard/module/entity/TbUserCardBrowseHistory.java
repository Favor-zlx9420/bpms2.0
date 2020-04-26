package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 浏览名片记录表
 * @author      : Administrator
 * @createDate  : 2020-03-10
 */
@Table("`tb_user_card_browse_history`")
@Data()
@Accessors(chain = true)
public class TbUserCardBrowseHistory extends BaseEntity<TbUserCardBrowseHistory> {
    /**
     * 浏览用户ID
     */
    @Column("`vistor_user_id`")
    @ApiModelProperty("浏览用户ID")
    private Long vistorUserId;

    /**
     * 名片id
     */
    @Column("`card_info_id`")
    @ApiModelProperty("名片id")
    private Long cardInfoId;
}