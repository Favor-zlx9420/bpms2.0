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
@Table("`tong-rong`.`pfund_rel`")
@Data()
@Accessors(chain = true)
public class TbPfundRel {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 证券代码
     */
    @Column("`SECURITY_ID`")
    private Long securityId;

    /**
     * 关联子代码
     */
    @Column("`REL_SECURITY_ID`")
    private Long relSecurityId;

    /**
     * 层级
     */
    @Column("`LEVEL`")
    private String level;

    /**
     * 类型
     */
    @Column("`TYPE`")
    private String type;

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