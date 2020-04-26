package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 名片相册表
 * @author      : Administrator
 * @createDate  : 2020-03-09
 */
@Table("`tb_user_card_album`")
@Data()
@Accessors(chain = true)
public class TbUserCardAlbum extends BaseEntity<TbUserCardAlbum> {
    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 图片地址
     */
    @Column("`pic_url`")
    @ApiModelProperty("图片地址")
    private String picUrl;

    /**
     * 图片名称
     */
    @Column("`pic_name`")
    @ApiModelProperty("图片名称")
    private String picName;

    /**
     * 说明
     */
    @Column("`remark`")
    @ApiModelProperty("说明")
    private String remark;
}