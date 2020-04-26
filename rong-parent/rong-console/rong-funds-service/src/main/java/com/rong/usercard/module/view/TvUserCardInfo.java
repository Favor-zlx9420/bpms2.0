package com.rong.usercard.module.view;

import com.rong.usercard.module.entity.TbUserCardInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

import static com.rong.common.consts.CommonEnumContainer.UserCardSwapState.NO_EXCHANGE_REQUEST;

@Data
public class TvUserCardInfo extends TbUserCardInfo {
    @ApiModelProperty("浏览我名片的人数")
    private int browseUserCount;
    @ApiModelProperty("点赞我名片的人数")
    private int likeUserCount;
    @ApiModelProperty("用户名片交换状态(-1:未申请交换,0:已申请交换对方未处理,1：双方已同意，2：对方已拒绝交换要求，3：对方已申请您未处理，4：您主动拒绝交换要求)")
    private Integer swapState = NO_EXCHANGE_REQUEST.getValue();
    @ApiModelProperty("我的观点")
    private List<TvUserCardTalk> cardTalks;
    @ApiModelProperty("模块列表")
    private List<TvUserCardModule> cardModules;
    @ApiModelProperty("分享标题")
    private String currentShareTitle;

    @ApiModelProperty("是否已点赞")
    private boolean liked;
}