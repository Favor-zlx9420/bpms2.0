package com.rong.admin.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description : 后台栏目表，也是保存权限的表
 * @author      : Administrator
 * @createDate  : 2020-01-04
 */
@Table("`tb_adm_column`")
@Data()
@Accessors(chain = true)
public class TbAdmColumn extends BaseEntity<TbAdmColumn> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 父级id
     */
    @Column("`parent_id`")
    private Long parentId;

    /**
     * 栏目名称
     */
    @Column("`name`")
    private String name;

    /**
     * 链接路径
     */
    @Column("`url_link`")
    private String urlLink;

    /**
     * 是否按钮
     */
    @Column("`is_btn`")
    private Boolean isBtn;

    /**
     * 拥有的权限地址
     */
    @Column("`right_urls`")
    private String rightUrls;

    /**
     * 导航图标
     */
    @Column("`icon`")
    private String icon;

    /**
     * 可见度
     */
    @Column("`visible`")
    private Boolean visible;
}