package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqUserCardlike;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardLike;
import com.rong.usercard.module.query.TsUserCardInfo;
import com.rong.usercard.module.query.TsUserCardLike;
import com.rong.usercard.module.request.TqUserCardLike;
import com.rong.usercard.service.UserCardInfoService;
import com.rong.usercard.service.UserCardLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名片点赞
 */
@Api(tags = "名片点赞")
@RestController
@RequestMapping("usercard/like")
public class CardLikeController {
    @Autowired
    private UserCardLikeService userCardLikeService;
    @Autowired
    private UserCardInfoService userCardInfoService;
    /**
     * 点赞/取消点赞名片
     * @param req
     * @return
     */
    @ApiOperation(value = "点赞/取消点赞名片")
    @PostMapping("set")
    public Result<Boolean> likeOrNotLike(@RequestBody TqUserCardlike req){
        if(userCardInfoService.selectCount(WrapperFactory.queryWrapper().eq(TsUserCardInfo.Fields.id,req.getCardInfoId())) == 0){
            throw new NoExistsException("卡片不存在！");
        }
        TbUserCardLike userCardLike = userCardLikeService.selectOne(
          WrapperFactory.queryWrapper()
                  .eq(TsUserCardLike.Fields.cardInfoId,req.getCardInfoId())
                  .eq(TsUserCardLike.Fields.likorUserId,req.getUserId())
        );
        if(null == userCardLike){
            //
            userCardLike = new TbUserCardLike()
                    .setLikorUserId(req.getUserId())
                    .setCardInfoId(req.getCardInfoId())
                    ;
            userCardLikeService.insert(new TqUserCardLike().setEntity(userCardLike));
        }else{
            userCardLike.setDeltag(!userCardLike.getDeltag());
            userCardLikeService.updateByPrimary(new TqUserCardLike().setEntity(userCardLike));
        }
        return Result.success(Boolean.TRUE);
    }
}
