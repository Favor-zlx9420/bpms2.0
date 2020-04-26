package com.rong.assembly.api.controller.business;


import com.rong.assembly.api.module.request.usercard.*;
import com.rong.assembly.api.service.UserCardSwapService;
import com.rong.assembly.api.wrapper.UserCardWrapper;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.Assert;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.service.MemBaseService;
import com.rong.usercard.module.entity.TbUserCardBrowseHistory;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.entity.TbUserCardShareTitle;
import com.rong.usercard.module.query.TsUserCardBrowseHistory;
import com.rong.usercard.module.query.TsUserCardInfo;
import com.rong.usercard.module.query.TsUserCardShareTitle;
import com.rong.usercard.module.request.TqUserCardBrowseHistory;
import com.rong.usercard.module.view.TvUserCardBrowseHistory;
import com.rong.usercard.module.view.TvUserCardInfo;
import com.rong.usercard.module.view.TvUserCardTalk;
import com.rong.usercard.service.UserCardBrowseHistoryService;
import com.rong.usercard.service.UserCardInfoService;
import com.rong.usercard.service.UserCardModuleService;
import com.rong.usercard.service.UserCardShareTitleService;
import com.rong.usercard.service.UserCardSwapMessageService;
import com.rong.usercard.service.UserCardTalkService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.PageList;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;

/**
 * 名片中心
 */
@Api(tags = "名片中心")
@RestController
@RequestMapping("usercard/info")
public class UserCardInfoController {
    @Autowired
    UserCardInfoService userCardInfoService;
    @Autowired
    UserCardTalkService userCardTalkService;
    @Autowired
    UserCardSwapMessageService userCardSwapMessageService;
    @Autowired
    private MemBaseService memBaseService;
    @Autowired
    private UserCardBrowseHistoryService userCardBrowseHistoryService;
    @Autowired
    private UserCardModuleService userCardModuleService;
    @Autowired
    private UserCardSwapService userCardSwapService;
    @Autowired
    private UserCardShareTitleService userCardShareTitleService;
    /**
     * 我的名片首页
     * @param req
     * @return
     */
    @ApiOperation(value = "我的名片首页")
    @GetMapping("index")
    public Result<TvUserCardInfo> index(TqMyCardIndex req){
        TvUserCardInfo cardInfo = userCardInfoService.selectOneView(
                UserCardWrapper.selectCardInfoIndex()
                .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
        );
        if(null == cardInfo){
            cardInfo = new TvUserCardInfo();
        }
        //初始化模块
        userCardModuleService.initUserModule(req.getUserId());

        //加载观点
        cardInfo.setCardTalks(
                userCardTalkService.selectViewList(
                        WrapperFactory.multiQueryWrapper()
                                .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                                .eq(CompareAlias.valueOf("e.deltag"),false)
                                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                                .page(new PageInfo())
                )
        );
        //当前分享标题
        TbUserCardShareTitle shareTitle = userCardShareTitleService.selectOne(
          WrapperFactory.queryWrapper().eq(TsUserCardShareTitle.Fields.userId,cardInfo.getUserId())
          .eq(TsUserCardShareTitle.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
          .eq(TsUserCardShareTitle.Fields.deltag,false)
                  .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("updateDate")))
        );
        if(null != shareTitle){
            cardInfo.setCurrentShareTitle(shareTitle.getTitle());
        }
        //加载自定义模块
        cardInfo.setCardModules(userCardModuleService.listUserCardModules(req.getUserId()));
        return Result.success(cardInfo);
    }

    /**
     * 名片详情
     * @param req
     * @return
     */
    @ApiOperation(value = "名片详情")
    @GetMapping("detail")
    public Result<TvUserCardInfo> detail(TqUserCardDetail req){
        MultiTableQueryWrapper queryWrapper = UserCardWrapper.selectCardInfoIndex()
                .eq(CompareAlias.valueOf("e.id"),req.getId())
                .eq(CompareAlias.valueOf("e.visible"),true);
        if(req.getUserInfo() != null){
            queryWrapper.select0(
                    SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_card_like where card_info_id=e.id and likor_user_id="+req.getLoginUserId()+" and deltag = false) liked",true)
            );
        }
        TvUserCardInfo cardInfo = userCardInfoService.selectOneView(queryWrapper);
        Assert.notNull(cardInfo,"名片不存在");
        //加载观点
        cardInfo.setCardTalks(
                userCardTalkService.selectViewList(
                        WrapperFactory.multiQueryWrapper()
                                .eq(CompareAlias.valueOf("e.userId"),cardInfo.getUserId())
                                .eq(CompareAlias.valueOf("e.deltag"),false)
                                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                                .page(new PageInfo())
                )
        );
        //当前分享标题
        TbUserCardShareTitle shareTitle = userCardShareTitleService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserCardShareTitle.Fields.userId,cardInfo.getUserId())
                        .eq(TsUserCardShareTitle.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                        .eq(TsUserCardShareTitle.Fields.deltag,false)
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("updateDate")))
        );
        if(null != shareTitle){
            cardInfo.setCurrentShareTitle(shareTitle.getTitle());
        }
        //加载自定义模块
        cardInfo.setCardModules(userCardModuleService.listUserCardModules(cardInfo.getUserId()));
        CommonEnumContainer.UserCardSwapState swapState = userCardSwapService.getBothSidesSwapState(req.getUserId(),cardInfo.getUserId());
        cardInfo.setSwapState(swapState.getValue());
        //如果未交换 且 又不公开
        if(swapState != CommonEnumContainer.UserCardSwapState.BOTH_PARTIES_HAVE_AGREED && !cardInfo.getContactPublicVisible()){
            cardInfo.setPhone(null);
            cardInfo.setEmail(null);
            cardInfo.setCall(null);
            cardInfo.setWxNo(null);
            cardInfo.setFax(null);
            cardInfo.setAddress(null);
            cardInfo.setHometown(null);
        }
        if(!CommonUtil.isEqual(cardInfo.getUserId(),req.getUserId()) && req.getUserInfo() != null) {
            //判断并插入浏览记录
            if (userCardBrowseHistoryService.selectCount(WrapperFactory.queryWrapper()
                    .eq(TsUserCardBrowseHistory.Fields.cardInfoId, req.getId())
                    .eq(TsUserCardBrowseHistory.Fields.vistorUserId, req.getUserId())
            ) == 0) {
                TbUserCardBrowseHistory browseHistory = new TbUserCardBrowseHistory()
                        .setVistorUserId(req.getUserId())
                        .setUpdateDate(new Date())
                        .setCardInfoId(req.getId());
                userCardBrowseHistoryService.insert(new TqUserCardBrowseHistory().setEntity(browseHistory));
            }
        }


        return Result.success(cardInfo);
    }

    /**
     * 浏览某名片的用户
     * @param req
     * @return
     */
    @ApiOperation(value = "浏览某名片的用户")
    @GetMapping("browsers")
    public Result<PageList<TvUserCardBrowseHistory>> browseMine(TqUserCardBrowHistory req){
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper()
                .select("e.createDate,e.updateDate,e.id,ci.id theCardInfoId,ci.userId theUserId,ci.position thePosition,ci.company theCompany,ci.headPortrait theHeadPortrait")
                .select0(
                        SelectAlias.valueOf("concat(ci.first_name,ci.last_name) theFullName",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbUserCardInfo.class,"ci"), ci->
                        ci.eqc(CompareAlias.valueOf("ci.userId"),CompareAlias.valueOf("e.vistorUserId"))
                )
                .eq(CompareAlias.valueOf("e.cardInfoId"),req.getId())
                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.updateDate")))
                .page(req.getPageInfo())
                ;
        if(StringUtil.isNotEmpty(req.getKeyword())){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("ci.fullName"),req.getKeyword())
                    .like(CompareAlias.valueOf("ci.company"),req.getKeyword())
            );
        }
        if(req.getUserInfo() != null){
            queryWrapper.select0(
                    SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_card_like where card_info_id=e.card_info_id and likor_user_id="+req.getLoginUserId()+" and deltag = false) liked",true)
            );
        }
        return Result.success(userCardBrowseHistoryService.selectPageListV(queryWrapper));
    }
    /**
     * 热门名片
     * @param req
     * @return
     */
    @ApiOperation(value = "热门名片")
    @GetMapping("hot/list")
    public Result<PageList<TvUserCardInfo>> hotCards(TqUserCardList req){
        MultiTableQueryWrapper queryWrapper = UserCardWrapper.selectCardInfoList();
        queryWrapper
                .eq(CompareAlias.valueOf("e.hotSearch"),true)
                .eq(CompareAlias.valueOf("e.visible"),true)
        ;
        if(req.getUserInfo() != null){
            queryWrapper.select0(
                    SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_card_like where card_info_id=e.id and likor_user_id="+req.getLoginUserId()+" and deltag = false) liked",true)
            );
        }
        if(StringUtil.isNotEmpty(req.getKeyword())){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("e.fullName"),req.getKeyword())
                    .like(CompareAlias.valueOf("e.company"),req.getKeyword())
            );
        }
        PageList<TvUserCardInfo> pageList = userCardInfoService.selectPageListV(queryWrapper.page(req.getPageInfo()));
        if(null !=req.getUserInfo() && CommonUtil.isNotEmpty(pageList.getList())){
            for(TvUserCardInfo cardInfo:pageList.getList()){
                cardInfo.setSwapState(userCardSwapService.getBothSidesSwapState(req.getLoginUserId(),cardInfo.getUserId()).getValue());
            }
        }
        return Result.success(pageList);
    }

    /**
     * 可能认识的名片(未登录无法识别用户，所以肯定返回)
     * @param req
     * @return
     */
    @ApiOperation(value = "可能认识的名片")
    @GetMapping("mayKnow/list")
    public Result<PageList<TvUserCardInfo>> mayKnowList(TqUserCardList req){
        if(req.getLoginUserId() < 1L){
            PageList pageList = new PageList<>();
            pageList.setList(Collections.emptyList());
            return Result.success();//可以返回是否登录的异常
        }
        TbMemBase userInfo = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getLoginUserId()));
        TbUserCardInfo myCard = userCardInfoService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserCardInfo.Fields.userId,req.getLoginUserId())
        );
        MultiTableQueryWrapper queryWrapper = UserCardWrapper.selectCardInfoList();
        queryWrapper
                .or(x->{
                    x.eq(CompareAlias.valueOf("e.recommend"),true);
                    if(StringUtil.isNotEmpty(userInfo.getPosition())){
                        x.like(CompareAlias.valueOf("e.position"),userInfo.getPosition());
                    }
                    if(null != myCard){
                        if(StringUtil.isNotEmpty(myCard.getCompany())){
                            x.like(CompareAlias.valueOf("e.company"),myCard.getCompany());
                        }
                        if(StringUtil.isNotEmpty(myCard.getPosition())){
                            x.like(CompareAlias.valueOf("e.position"),myCard.getPosition());
                        }
                    }else{
                        x.eq(CompareAlias.valueOf("e.id"),0L);
                    }
                })
                .eq(CompareAlias.valueOf("e.visible"),true)
        ;
        if(req.getUserInfo() != null){
            queryWrapper.select0(
                    SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_card_like where card_info_id=e.id and likor_user_id="+req.getLoginUserId()+" and deltag = false) liked",true)
            );
        }
        if(StringUtil.isNotEmpty(req.getKeyword())){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("e.fullName"),req.getKeyword())
                    .like(CompareAlias.valueOf("e.company"),req.getKeyword())
            );
        }
        PageList<TvUserCardInfo> pageList = userCardInfoService.selectPageListV(queryWrapper.page(req.getPageInfo()));
        if(null !=req.getUserInfo() && CommonUtil.isNotEmpty(pageList.getList())){
            for(TvUserCardInfo cardInfo:pageList.getList()){
                cardInfo.setSwapState(userCardSwapService.getBothSidesSwapState(req.getLoginUserId(),cardInfo.getUserId()).getValue());
            }
        }
        return Result.success(pageList);
    }

    /**
     * 名片列表搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "名片列表搜索")
    @GetMapping("query")
    public Result<PageList<TvUserCardInfo>> mayKnowList(TqCardInfoList req){
        TbMemBase userInfo = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getLoginUserId()));
        TbUserCardInfo myCard = userCardInfoService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserCardInfo.Fields.userId,req.getLoginUserId())
        );
        MultiTableQueryWrapper queryWrapper = UserCardWrapper.selectCardInfoList()
                .eq(CompareAlias.valueOf("e.visible"),true);
        if(req.getUserInfo() != null){
            queryWrapper.select0(
                    SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_card_like where card_info_id=e.id and likor_user_id="+req.getLoginUserId()+" and deltag = false) liked",true)
            );
        }
        if(StringUtil.isNotEmpty(req.getKeyword())){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("e.fullName"),req.getKeyword())
                    .like(CompareAlias.valueOf("e.company"),req.getKeyword())
            );
        }
        if (StringUtil.isNotEmpty(req.getCompany())) {
            queryWrapper.like(CompareAlias.valueOf("e.company"),req.getCompany());
        }
        if (StringUtil.isNotEmpty(req.getFirstName())) {
            queryWrapper.like(CompareAlias.valueOf("e.fullName"),req.getFirstName());
        }
        if (StringUtil.isNotEmpty(req.getLastName())) {
            queryWrapper.like(CompareAlias.valueOf("e.fullName"),req.getLastName());
        }
        PageList<TvUserCardInfo> pageList = userCardInfoService.selectPageListV(queryWrapper.page(req.getPageInfo()));
        if(null !=req.getUserInfo() && CommonUtil.isNotEmpty(pageList.getList())){
            for(TvUserCardInfo cardInfo:pageList.getList()){
                cardInfo.setSwapState(userCardSwapService.getBothSidesSwapState(req.getLoginUserId(),cardInfo.getUserId()).getValue());
            }
        }
        return Result.success(pageList);
    }

    /**
     * 某人的说说记录
     * @param req
     * @return
     */
    @ApiOperation(value = "某人的说说记录")
    @GetMapping("talkList")
    public Result<PageList<TvUserCardTalk>> list(TqCardTalkList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getTalkUserId())
                        .eq(CompareAlias.valueOf("e.deltag"),false)
                        .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                        .page(req.getPageInfo())
                ;
        return Result.success(userCardTalkService.selectPageListV(queryWrapper));
    }

}
