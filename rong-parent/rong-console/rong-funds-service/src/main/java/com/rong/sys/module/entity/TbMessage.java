package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 消息主表
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_message`")
@Data()
@Accessors(chain = true)
public class TbMessage extends BaseEntity<TbMessage> {
    /**
     * 
     */
    @Column("`relation_id`")
    private Long relationId;
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
     * 会员id
     */
    @Column("`member_id`")
    private Long memberId;

    /**
     * 管理员id
     */
    @Column("`admin_user_id`")
    private Long adminUserId;

    /**
     * 消息标题
     */
    @Column("`title`")
    private String title;

    /**
     * 消息内容
     */
    @Column("`content`")
    private String content;

    /**
     * 查看状态
     */
    @Column("`view_state`")
    private Integer viewState;

    /**
     * 类型
     */
    @Column("`type`")
    private Integer type;

    /**
     * 上一级id：如果不为空表示是回复的
     */
    @Column("`parent_id`")
    private Integer parentId;

    /**
     * 联系人
     */
    @Column("`contactor`")
    private String contactor;

    /**
     * 联系人手机
     */
    @Column("`phone`")
    private String phone;

    /**
     * email
     */
    @Column("`email`")
    private String email;

    /**
     * 职务
     */
    @Column("`post`")
    private String post;

    /**
     * 所在公司
     */
    @Column("`company`")
    private String company;

    /**
     * 所在省份
     */
    @Column("`province`")
    private String province;

    /**
     * 所在城市
     */
    @Column("`city`")
    private String city;

    /**
     * 详细地址
     */
    @Column("`address`")
    private String address;

    /**
     * ip
     */
    @Column("`ip`")
    private String ip;

    /**
     * 回复内容
     */
    @Column("`reply`")
    private String reply;
}