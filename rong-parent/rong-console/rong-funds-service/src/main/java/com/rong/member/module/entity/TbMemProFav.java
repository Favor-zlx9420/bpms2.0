package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员产品收藏
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_pro_fav`")
@Data()
@Accessors(chain = true)
public class TbMemProFav extends BaseEntity<TbMemProFav> {
    /**
     * 会员id
     */
    @Column("`member_id`")
    private Long memberId;

    /**
     * 产品规格参数id
     */
    @Column("`spec_par_pro_id`")
    private Long specParProId;

    /**
     * 产品Id
     */
    @Column("`product_id`")
    private Long productId;
}