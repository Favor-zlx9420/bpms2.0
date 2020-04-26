package com.rong.member.module.foreign;

import com.rong.common.module.BaseEntity;

import java.math.BigDecimal;

public class TbAccountFlow extends BaseEntity {
    /**
     * 入账会员ID
     */
    private Integer memberId;

    /**
     * 操作金额
     */
    
    private BigDecimal amount;

    /**
     * 可用结余
     */
    
    private BigDecimal availableBalance;

    /**
     * 冻结结余
     */
    
    private BigDecimal freezeBalance;

    /**
     * 资金备注
     */
    private String memo;

    /**
     * 资金类型
     */
    private Integer fundsType;

    /**
     * 账户平台
     */
    private Integer platform;

    /**
     * 关联表ID
     */
    private Integer relationId;

    /**
     * 操作描述
     */
    private String remark;

    /**
     * 0:收入，1:支出,2预授权收入3预授权支出
     */
    private Integer direction;

    /**
     * 入账会员ID
     * [whh]@return member_id 入账会员ID
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 入账会员ID
     * @param memberId 入账会员ID
     */
    public TbAccountFlow setMemberId(Integer memberId) {
        this.memberId = memberId;
        return this;
    }

    /**
     * 操作金额
     * [whh]@return amount 操作金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 操作金额
     * @param amount 操作金额
     */
    public TbAccountFlow setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * 可用结余
     * [whh]@return available_balance 可用结余
     */
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    /**
     * 可用结余
     * @param availableBalance 可用结余
     */
    public TbAccountFlow setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
        return this;
    }

    /**
     * 冻结结余
     * [whh]@return freeze_balance 冻结结余
     */
    public BigDecimal getFreezeBalance() {
        return freezeBalance;
    }

    /**
     * 冻结结余
     * @param freezeBalance 冻结结余
     */
    public TbAccountFlow setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
        return this;
    }

    /**
     * 资金备注
     * [whh]@return memo 资金备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 资金备注
     * @param memo 资金备注
     */
    public TbAccountFlow setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    /**
     * 资金类型
     * [whh]@return funds_type 资金类型
     */
    public Integer getFundsType() {
        return fundsType;
    }

    /**
     * 资金类型
     * @param fundsType 资金类型
     */
    public TbAccountFlow setFundsType(Integer fundsType) {
        this.fundsType = fundsType;
        return this;
    }

    /**
     * 账户平台
     * [whh]@return platform 账户平台
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * 账户平台
     * @param platform 账户平台
     */
    public TbAccountFlow setPlatform(Integer platform) {
        this.platform = platform;
        return this;
    }

    /**
     * 关联表ID
     * [whh]@return relation_id 关联表ID
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * 关联表ID
     * @param relationId 关联表ID
     */
    public TbAccountFlow setRelationId(Integer relationId) {
        this.relationId = relationId;
        return this;
    }

    /**
     * 操作描述
     * [whh]@return remark 操作描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 操作描述
     * @param remark 操作描述
     */
    public TbAccountFlow setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 0:收入，1:支出,2预授权收入3预授权支出
     * [whh]@return direction 0:收入，1:支出,2预授权收入3预授权支出
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * 0:收入，1:支出,2预授权收入3预授权支出
     * @param direction 0:收入，1:支出,2预授权收入3预授权支出
     */
    public TbAccountFlow setDirection(Integer direction) {
        this.direction = direction;
        return this;
    }
}