package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员收藏基金经理记录
 * @author      : lether
 * @createDate  : 2020-02-07
 */
@Table("`tb_user_people_fav`")
@Data()
@Accessors(chain = true)
public class TbUserPeopleFav extends BaseEntity<TbUserPeopleFav> {
    /**
     * 用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 基金经理用户id
     */
    @Column("`fav_user_id`")
    @ApiModelProperty("基金经理用户id")
    private Long favUserId;
    /**
     * 对应人物id
     */
    @Column("`person_id`")
    @ApiModelProperty("对应人物id")
    private Long personId;
}