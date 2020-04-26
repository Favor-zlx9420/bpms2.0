package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 信用信息验证日志
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_creditfile_certify_log`")
@Data()
@Accessors(chain = true)
public class TbMemCreditfileCertifyLog extends BaseEntity<TbMemCreditfileCertifyLog> {
    /**
     * 会员Id
     */
    @Column("`member_id`")
    private Long memberId;

    /**
     * 验证状态
     */
    @Column("`creditfile_state`")
    private Integer creditfileState;

    /**
     * 验证类型
     */
    @Column("`creditfile_type`")
    private Integer creditfileType;

    /**
     * 相关凭证链接(一般是图片/视频/录音等多媒体)
     */
    @Column("`relation_links`")
    private String relationLinks;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;

    /**
     * 后台操作用户
     */
    @Column("`adm_user_id`")
    private Long admUserId;

    /**
     * 后台操作用户名称
     */
    @Column("`adm_user_name`")
    private String admUserName;

    /**
     * 备注
     */
    @Column("`memo`")
    private String memo;

    /**
     * ip
     */
    @Column("`ip`")
    private String ip;
}