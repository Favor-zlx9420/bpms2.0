package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqAlterCardInfo;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.util.Assert;
import com.rong.common.util.StringUtil;
import com.rong.common.util.Validator;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.entity.TbUserCardShareTitle;
import com.rong.usercard.module.query.TsUserCardInfo;
import com.rong.usercard.module.request.TqUserCardInfo;
import com.rong.usercard.module.request.TqUserCardShareTitle;
import com.rong.usercard.service.UserCardInfoService;
import com.rong.usercard.service.UserCardShareTitleService;
import com.rong.usercard.service.UserCardSwapMessageService;
import com.rong.usercard.service.UserCardTalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 名片中心
 */
@Api(tags = "名片中心")
@RestController
@RequestMapping("usercard/info")
public class CardInfoController {
    @Autowired
    UserCardInfoService userCardInfoService;
    @Autowired
    UserCardTalkService userCardTalkService;
    @Autowired
    UserCardSwapMessageService userCardSwapMessageService;
    @Autowired
    private UserCardShareTitleService userCardShareTitleService;
    /**
     * 创建名片
     * @param req
     * @return
     */
    @ApiOperation(value = "创建名片")
    @PostMapping("create")
    public Result<Boolean> createCard(@RequestBody TqAlterCardInfo req){
        //验证必填项
        Assert.notEmpty(req.getFirstName(),"姓必填");
        Assert.notEmpty(req.getFirstName(),"名字必填");
        Assert.match(req.getPhone(), Validator.PHONE_NUMBER,"手机号格式不正确");
        Assert.match(req.getEmail(), Validator.EMAIL,"邮箱格式不正确");

        if(userCardInfoService.selectCount(WrapperFactory.queryWrapper().eq(TsUserCardInfo.Fields.userId,req.getUserId())) > 0){
            throw new DuplicateDataException("已经有名片无法再次创建");
        }
        TbUserCardInfo cardInfo = new TbUserCardInfo();
        BeanUtils.copyProperties(req,cardInfo);
        cardInfo.setId(null);
        if(null == cardInfo.getContactPublicVisible()){
            cardInfo.setContactPublicVisible(false);
        }
        if(null == cardInfo.getSwapVerify()){
            cardInfo.setSwapVerify(true);
        }
        cardInfo
                .setVisible(true)
                .setHotSearch(false)
                .setRecommend(false)
                .setSort(BigDecimal.ZERO)
                .setFullName(cardInfo.getFirstName() + cardInfo.getLastName())

        ;
        userCardInfoService.insert(new TqUserCardInfo().setEntity(cardInfo));
        //初始化分享标题
        TbUserCardShareTitle title = new TbUserCardShareTitle()
                .setTitle("您好，我是"+cardInfo.getFirstName()+cardInfo.getLastName()+"，欢迎进入我的名片，可直接与我聊一聊！")
                .setState(CommonEnumContainer.State.NORMAL.getValue())
                .setUserId(req.getUserId());
        userCardShareTitleService.insert(new TqUserCardShareTitle().setEntity(title));
            title.setTitle("您好，这是我的融总管名片，望惠存！")
                .setState(CommonEnumContainer.State.INVALID.getValue());
        userCardShareTitleService.insert(new TqUserCardShareTitle().setEntity(title));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 修改名片
     * @param req
     * @return
     */
    @ApiOperation(value = "修改名片")
    @PostMapping("update")
    public Result<Boolean> updateCardInfo(@RequestBody TqAlterCardInfo req){

        //验证必填项
        Assert.notEmptyThenMatch(req.getPhone(), Validator.PHONE_NUMBER,"手机号格式不正确");
        Assert.notEmptyThenMatch(req.getEmail(), Validator.EMAIL,"邮箱格式不正确");
        TbUserCardInfo cardInfo = userCardInfoService.selectOne(
                WrapperFactory.queryWrapper()
                        .select(TsUserCardInfo.Fields.id, TsUserCardInfo.Fields.firstName, TsUserCardInfo.Fields.lastName)
                        .eq(TsUserCardInfo.Fields.id,req.getId())
                        .eq(TsUserCardInfo.Fields.userId,req.getUserId())
        )
        ;
        if(null == cardInfo){
            throw new NoExistsException("名片不存在");
        }
        String fn = cardInfo.getFirstName(),ln=cardInfo.getLastName();
        if(StringUtil.isNotEmpty(req.getFirstName())){
            fn = req.getFirstName();
        }
        if(StringUtil.isNotEmpty(req.getLastName())){
            ln = req.getLastName();
        }
        BeanUtils.copyProperties(req,cardInfo);
        cardInfo
                .setFullName(fn + ln);
        userCardInfoService.updateByPrimary(new TqUserCardInfo().setEntity(cardInfo));
        return Result.success(Boolean.TRUE);
    }

}
