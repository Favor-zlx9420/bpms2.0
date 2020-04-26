package com.rong.admin.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 管理员消息
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_adm_msg`")
@Data()
@Accessors(chain = true)
public class TbAdmMsg extends BaseEntity<TbAdmMsg> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 类型
     */
    @Column("`type`")
    private Integer type;

    /**
     * 所属用户id
     */
    @Column("`to_adm_user_id`")
    private Long toAdmUserId;

    /**
     * 发送的用户id
     */
    @Column("`from_adm_user_id`")
    private Long fromAdmUserId;

    /**
     * 发送的会员id
     */
    @Column("`from_member_id`")
    private Long fromMemberId;

    /**
     * 标题
     */
    @Column("`title`")
    private String title;

    /**
     * 内容
     */
    @Column("`content`")
    private String content;

    /**
     * 选择的标签
     */
    @Column("`label_ids`")
    private String labelIds;
}