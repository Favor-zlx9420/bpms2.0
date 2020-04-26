package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员收藏基金记录
 * @author      : lether
 * @createDate  : 2020-02-07
 */
@Table("`tb_user_pro_fav`")
@Data()
@Accessors(chain = true)
public class TbUserProFav extends BaseEntity<TbUserProFav> {
    /**
     * 用户id
     */
    @Column("`user_id`")
    private Long userId;

    /**
     * 产品id
     */
    @Column("`security_id`")
    private Long securityId;

    /**
     * 0私募，1信托，2公募
     */
    @Column("`type`")
    private Integer type;
}