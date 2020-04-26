package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-02-03
 */
@Table("`tong-rong`.`pfund_mana_year`")
@Data()
@Accessors(chain = true)
public class TbPfundManaYear {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 人员ID
     */
    @Column("`PERSON_ID`")
    private Long personId;

    /**
     * 从业年限
     */
    @Column("`YEAR`")
    private BigDecimal year;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 时间戳
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;
}