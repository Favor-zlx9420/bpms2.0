package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员预约基金经理
 * @author      : lether
 * @createDate  : 2020-02-07
 */
@Table("`tb_user_people_reservation`")
@Data()
@Accessors(chain = true)
public class TbUserPeopleReservation extends BaseEntity<TbUserPeopleReservation> {
    /**
     * 0：已预约未处理，1：已处理
     */
    @Column("`state`")
    private Integer state;

    /**
     * 用户id
     */
    @Column("`user_id`")
    private Long userId;

    /**
     * 机构id
     */
    @Column("`reservation_user_id`")
    private Long reservationUserId;
}