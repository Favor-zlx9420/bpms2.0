package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户账户
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_user_account`")
@Data()
@Accessors(chain = true)
public class TbUserAccount extends BaseEntity<TbUserAccount> {
    /**
     * 用户ID
     */
    @Column("`user_id`")
    private Long userId;

    /**
     * 冻结金额
     */
    @Column("`freeze_amount`")
    private BigDecimal freezeAmount;

    /**
     * 可用金额
     */
    @Column("`available_amount`")
    private BigDecimal availableAmount;

    /**
     * 版本号
     */
    @Column("`version`")
    private Integer version;

    /**
     * 账号类型
     */
    @Column("`type`")
    private Integer type;
}