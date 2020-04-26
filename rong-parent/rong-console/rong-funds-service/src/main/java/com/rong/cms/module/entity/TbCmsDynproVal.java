package com.rong.cms.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 信息动态属性表
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_cms_dynpro_val`")
@Data()
@Accessors(chain = true)
public class TbCmsDynproVal extends BaseEntity<TbCmsDynproVal> {

    /**
     * 对应新闻id
     */
    @Column("`news_id`")
    private Long newsId;

    /**
     * 对应动态属性id
     */
    @Column("`proper_id`")
    private Long properId;

    /**
     * 属性名称
     */
    @Column("`proper_name`")
    private String properName;

    /**
     * 属性值
     */
    @Column("`value`")
    private String value;
}