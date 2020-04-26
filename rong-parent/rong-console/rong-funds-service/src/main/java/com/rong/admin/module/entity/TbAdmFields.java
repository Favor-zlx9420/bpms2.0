package com.rong.admin.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description : 每张表 后台管理列表内容显示的列
 * @author      : Administrator
 * @createDate  : 2020-01-04
 */
@Table("`tb_adm_fields`")
@Data()
@Accessors(chain = true)
public class TbAdmFields extends BaseEntity<TbAdmFields> {
    /**
     * 显示状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 管理员id
     */
    @Column("`adm_user_id`")
    private Long admUserId;

    /**
     * 栏目id（标识）
     */
    @Column("`column_id`")
    private Long columnId;

    /**
     * 表名
     */
    @Column("`table_name`")
    private String tableName;

    /**
     * 属性表别名
     */
    @Column("`table_alias`")
    private String tableAlias;

    /**
     * 表头
     */
    @Column("`table_header`")
    private String tableHeader;

    /**
     * 实体属性
     */
    @Column("`field_name`")
    private String fieldName;

    /**
     * 列值模板或者返回值方法
     */
    @Column("`visible_template`")
    private String visibleTemplate;

    /**
     * 显示宽度
     */
    @Column("`row_width`")
    private Integer rowWidth;

    /**
     * 是否可排序
     */
    @Column("`sortable`")
    private Boolean sortable;

    /**
     * 是否行分组
     */
    @Column("`row_group`")
    private Integer rowGroup;

    /**
     * 列分组
     */
    @Column("`col_group`")
    private Integer colGroup;

    /**
     * 定位方式
     */
    @Column("`fixed`")
    private String fixed;
}