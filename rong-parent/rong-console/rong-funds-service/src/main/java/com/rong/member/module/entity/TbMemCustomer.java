package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 预约客户表
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_customer`")
@Data()
@Accessors(chain = true)
public class TbMemCustomer extends BaseEntity<TbMemCustomer> {
    /**
     * 处理状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 姓名
     */
    @Column("`name`")
    private String name;

    /**
     * 手机
     */
    @Column("`phone`")
    private String phone;
}