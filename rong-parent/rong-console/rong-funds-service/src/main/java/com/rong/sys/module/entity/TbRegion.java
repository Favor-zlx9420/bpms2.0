package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.PrimaryKey;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 地区管理
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_region`")
@Data()
@Accessors(chain = true)
public class TbRegion extends BaseEntity<TbRegion> {

    /**
     * 
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 编码
     */
    @Column("`code`")
    private String code;

    /**
     * 地址名
     */
    @Column("`name`")
    private String name;

    /**
     * 上一级ID
     */
    @Column("`parent_id`")
    private Long parentId;

    /**
     * 级别
     */
    @Column("`level`")
    private Integer level;

    /**
     * 拼音
     */
    @Column("`pinyin`")
    private String pinyin;

    /**
     * 拼音简写
     */
    @Column("`short_pinyin`")
    private String shortPinyin;
}