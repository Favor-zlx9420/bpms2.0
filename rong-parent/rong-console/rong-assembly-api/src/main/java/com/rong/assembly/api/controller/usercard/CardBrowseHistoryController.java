package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.rong.assembly.api.service.UserCardSwapService;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.query.TsUserCardInfo;
import com.rong.usercard.module.view.TvUserCardBrowseHistory;
import com.rong.usercard.service.UserCardBrowseHistoryService;
import com.rong.usercard.service.UserCardInfoService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageList;
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

/**
 * 名片浏览记录
 */
@Api(tags = "名片浏览记录")
@RestController
@RequestMapping("usercard/browseHistory")
public class CardBrowseHistoryController {
    @Autowired
    private UserCardBrowseHistoryService userCardBrowseHistoryService;
    @Autowired
    private UserCardInfoService userCardInfoService;
    @Autowired
    private UserCardSwapService userCardSwapService;

    /**
     * 我浏览的名片记录
     * @param req
     * @return
     */
    @ApiOperation(value = "我浏览的名片记录")
    @GetMapping("myBrowses")
    public Result<PageList<TvUserCardBrowseHistory>> myBrowses(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper()
                .select("e.createDate,e.updateDate,e.id,ci.id theCardInfoId,ci.userId theUserId,ci.position thePosition,ci.company theCompany,ci.headPortrait theHeadPortrait")
                .select0(
                        SelectAlias.valueOf("concat(ci.first_name,ci.last_name) theFullName",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbUserCardInfo.class,"ci"), ci->
                        ci.eqc(CompareAlias.valueOf("ci.id"),CompareAlias.valueOf("e.cardInfoId"))
                )
                .eq(CompareAlias.valueOf("e.vistorUserId"),req.getUserId())
                .page(req.getPageInfo())
                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.updateDate")))
                ;
        if(StringUtil.isNotEmpty(req.getKeyword())){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("ci.fullName"),req.getKeyword())
                    .like(CompareAlias.valueOf("ci.company"),req.getKeyword())
            );
        }
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        queryWrapper.select0(
                SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_card_like where card_info_id=ci.id and likor_user_id="+req.getLoginUserId()+" and deltag = false) liked",true)
        );
        PageList<TvUserCardBrowseHistory> pageList = userCardBrowseHistoryService.selectPageListV(queryWrapper);
        if(null !=req.getUserInfo() && CommonUtil.isNotEmpty(pageList.getList())){
            for(TvUserCardBrowseHistory history:pageList.getList()){
                history.setSwapState(userCardSwapService.getBothSidesSwapState(req.getLoginUserId(),history.getTheUserId()).getValue());
            }
        }
        return Result.success(pageList);
    }

    /**
     * 浏览我名片的用户
     * @param req
     * @return
     */
    @ApiOperation(value = "浏览我名片的用户")
    @GetMapping("browseMine")
    public Result<PageList<TvUserCardBrowseHistory>> browseMine(TqMySimpleList req){
        TbUserCardInfo cardInfo = userCardInfoService.selectOne(
                WrapperFactory.queryWrapper()
                        .select(TsUserCardInfo.Fields.id, TsUserCardInfo.Fields.userId)
                        .eq(TsUserCardInfo.Fields.userId,req.getUserId())
        );
        if(null == cardInfo){
            return Result.success();//用户没有创建名片，返回空
        }
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper()
                .select("e.createDate,e.updateDate,e.id,ci.id theCardInfoId,ci.userId theUserId,ci.position thePosition,ci.company theCompany,ci.headPortrait theHeadPortrait")
                .select0(
                        SelectAlias.valueOf("concat(ci.first_name,ci.last_name) theFullName",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbUserCardInfo.class,"ci"), ci->
                        ci.eqc(CompareAlias.valueOf("ci.userId"),CompareAlias.valueOf("e.vistorUserId"))
                )
                .eq(CompareAlias.valueOf("e.cardInfoId"),cardInfo.getId())
                .page(req.getPageInfo())
                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.updateDate")))
                ;
        if(StringUtil.isNotEmpty(req.getKeyword())){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("ci.fullName"),req.getKeyword())
                    .like(CompareAlias.valueOf("ci.company"),req.getKeyword())
            );
        }
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        queryWrapper.select0(
                SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_card_like where card_info_id=ci.id and likor_user_id="+req.getLoginUserId()+" and deltag = false) liked",true)
        );

        PageList<TvUserCardBrowseHistory> pageList = userCardBrowseHistoryService.selectPageListV(queryWrapper);
        if(null !=req.getUserInfo() && CommonUtil.isNotEmpty(pageList.getList())){
            for(TvUserCardBrowseHistory history:pageList.getList()){
                history.setSwapState(userCardSwapService.getBothSidesSwapState(req.getLoginUserId(),history.getVistorUserId()).getValue());
            }
        }
        return Result.success(pageList);
    }
}
