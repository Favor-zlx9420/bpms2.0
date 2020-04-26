package com.rong.assembly.api.controller.business;


import com.google.common.collect.ImmutableList;
import com.rong.assembly.api.mapper.GeneralMapper;
import com.rong.assembly.api.module.request.usercard.TqSwapMessageList;
import com.rong.assembly.api.module.request.usercard.TqUserCardSwapInfo;
import com.rong.assembly.api.module.response.usercard.TrSendSwapInfo;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.entity.TbUserCardSwapMessage;
import com.rong.usercard.module.view.TvUserCardSwapMessage;
import com.rong.usercard.service.UserCardSwapMessageService;
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
 * 名片交换请求
 */
@Api(tags = "名片交换请求")
@RestController
@RequestMapping("usercard/swap")
public class UserCardSwapMessageController {
    @Autowired
    private UserCardSwapMessageService userCardSwapMessageService;
    @Autowired
    private GeneralMapper generalMapper;
    /**
     * 交换名片记录
     * @param req
     * @return
     */
    @ApiOperation(value = "交换名片记录")
    @GetMapping("browseMine")
    public Result<PageList<TvUserCardSwapMessage>> browseMine(TqSwapMessageList req){
        if(req.getUserInfo() == null){
            PageList pageList = new PageList<TvUserCardSwapMessage>();
            pageList.setList(ImmutableList.of());
            return Result.success(pageList);
        }
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper()
                .select("e.createDate,e.updateDate,e.id,e.createDate,e.dealResult,e.applicantUserId,e.targetUserId")
                .select0(
                        SelectAlias.valueOf("case when e.applicant_user_id="+req.getUserId()+" then tg.id else app.id end theCardInfoId",true)
                        ,
                        SelectAlias.valueOf("case when e.applicant_user_id="+req.getUserId()+" then tg.user_id else app.user_id end theUserId",true)
                        ,
                        SelectAlias.valueOf("case when e.applicant_user_id="+req.getUserId()+" then tg.position else app.position end thePosition",true)
                        ,
                        SelectAlias.valueOf("case when e.applicant_user_id="+req.getUserId()+" then tg.company else app.company end theCompany",true)
                        ,
                        SelectAlias.valueOf("case when e.applicant_user_id="+req.getUserId()+" then tg.head_portrait else app.head_portrait end theHeadPortrait",true)
                        ,
                        SelectAlias.valueOf("case when e.applicant_user_id="+req.getUserId()+" then concat(tg.first_name,tg.last_name) else concat(app.first_name,app.last_name) end theFullName",true)

                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbUserCardInfo.class,"app"), app->
                        app.eqc(CompareAlias.valueOf("app.userId"),CompareAlias.valueOf("e.applicantUserId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbUserCardInfo.class,"tg"), tg->
                        tg.eqc(CompareAlias.valueOf("tg.userId"),CompareAlias.valueOf("e.targetUserId"))
                )
                .or(x->x
                        .eq(CompareAlias.valueOf("e.applicantUserId"),req.getUserId())
                        .eq(CompareAlias.valueOf("e.targetUserId"),req.getUserId())
                )
                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.createDate")))
                .page(req.getPageInfo())
                ;
                if(StringUtil.isNotEmpty(req.getKeyword())){
                    queryWrapper.or(or->or
                            .like(CompareAlias.valueOf("app.fullName"),req.getKeyword())
                            .like(CompareAlias.valueOf("app.company"),req.getKeyword())
                            .like(CompareAlias.valueOf("tg.fullName"),req.getKeyword())
                            .like(CompareAlias.valueOf("tg.company"),req.getKeyword())
                    );
                }
                if(null != req.getDealResult()){
                    queryWrapper.eq(CompareAlias.valueOf("e.dealResult"),req.getDealResult());
                }
                if(null != req.getDeltag()){
                    queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
                }
                if(CommonUtil.isEqual(req.getTarget(), TqSwapMessageList.SwapTarget.别人向我发起的请求.ordinal())){
                    queryWrapper.eq(CompareAlias.valueOf("e.applicantUserId"),req.getUserId());
                }else if(CommonUtil.isEqual(req.getTarget(), TqSwapMessageList.SwapTarget.我向别人发起的请求.ordinal())){
                    queryWrapper.eq(CompareAlias.valueOf("e.targetUserId"),req.getUserId());
                }
        return Result.success(userCardSwapMessageService.selectPageListV(queryWrapper));
    }

    /**
     * 发送和接收名片数量信息
     * @param req
     * @return
     */
    @ApiOperation(value = "发送和接收名片数量信息")
    @GetMapping("count")
    public Result<TrSendSwapInfo> sendSwapInfo(TqUserCardSwapInfo req){
        TrSendSwapInfo sendSwapInfo = new TrSendSwapInfo();
        if(req.getUserInfo() != null){
            //我发送的数量
            sendSwapInfo.setSendCount(
                    generalMapper.count(WrapperFactory.multiQueryWrapper(TbUserCardSwapMessage.class)
                            .eq(CompareAlias.valueOf("e.applicantUserId"),req.getLoginUserId())
                            .eq(CompareAlias.valueOf("e.dealResult"), req.getDealResult())
                    )
            );
            //我接收的数量
            sendSwapInfo.setReceiveCount(
                    generalMapper.count(WrapperFactory.multiQueryWrapper(TbUserCardSwapMessage.class)
                            .eq(CompareAlias.valueOf("e.targetUserId"),req.getLoginUserId())
                            .eq(CompareAlias.valueOf("e.dealResult"), req.getDealResult())
                    )
            );
        }
        return Result.success(sendSwapInfo);
    }

}
