package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员收藏机构记录
 * @author      : lether
 * @createDate  : 2020-02-07
 */
@Table("`tb_user_org_fav`")
@Data()
@Accessors(chain = true)
public class TbUserOrgFav extends BaseEntity<TbUserOrgFav> {
    /**
     * 用户id
     */
    @Column("`user_id`")
    private Long userId;

    /**
     * 机构id
     */
    @Column("`party_id`")
    private Long partyId;
}