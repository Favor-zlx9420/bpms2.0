package com.rong.admin.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description : 后台日志
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_adm_log`")
@Data()
@Accessors(chain = true)
public class TbAdmLog extends BaseEntity<TbAdmLog> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 栏目Id
     */
    @Column("`column_id`")
    private Long columnId;

    /**
     * 调用时间
     */
    @Column("`time`")
    private Date time;

    /**
     * 操作人员
     */
    @Column("`adm_user_name`")
    private String admUserName;

    /**
     * 操作人员Id
     */
    @Column("`adm_user_id`")
    private Long admUserId;

    /**
     * 操作描述
     */
    @Column("`opera_desc`")
    private String operaDesc;

    /**
     * 操作uri
     */
    @Column("`uri`")
    private String uri;

    /**
     * 出参
     */
    @Column("`return_value`")
    private String returnValue;

    /**
     * 其他参数
     */
    @Column("`other_args`")
    private String otherArgs;

    /**
     * ip
     */
    @Column("`ip`")
    private String ip;

    /**
     * 入参
     */
    @Column("`arguments`")
    private String arguments;
}