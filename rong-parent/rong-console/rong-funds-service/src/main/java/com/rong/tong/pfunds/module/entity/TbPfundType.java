package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-02-03
 */
@Table("`tong-rong`.`pfund_type`")
@Data()
@Accessors(chain = true)
public class TbPfundType {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 证券内部ID
     */
    @Column("`SECURITY_ID`")
    private Integer securityId;

    /**
     * 分类代码
     */
    @Column("`CODE_TYPE_ID`")
    private Long codeTypeId;

    /**
     * 参数值
     */
    @Column("`VALUE_NUM_CD`")
    private Long valueNumCd;

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