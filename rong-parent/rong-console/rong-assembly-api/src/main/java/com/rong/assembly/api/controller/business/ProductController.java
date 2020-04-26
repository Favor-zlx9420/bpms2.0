package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.mapper.RespTrFundsMapper;
import com.rong.assembly.api.mapper.RespUserFavMapper;
import com.rong.assembly.api.module.request.buz.TqFavUserListOfProduct;
import com.rong.assembly.api.module.request.buz.TqRecommendSecurity;
import com.rong.assembly.api.module.response.TrFavUser;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.module.TqBase;
import com.rong.common.module.TvPageList;
import com.rong.common.util.MultiDbUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNav;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNavGr;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.member.module.entity.TbMemBase;
import com.rong.tong.fund.module.entity.TbFund;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.user.module.entity.TbUserProFav;
import com.vitily.mybatis.core.entity.FieldField;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
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

import java.util.List;

@Api(tags = "产品相关")
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private RespTrFundsMapper respTrFundsMapper;
    @Autowired
    private RespUserFavMapper respUserFavMapper;
    /**
     * 收藏该产品的用户列表
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏产品的用户列表")
    @GetMapping("fav/list")
    public Result<TvPageList<TrFavUser>> favList(TqFavUserListOfProduct req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbUserProFav.class)
                .selectAllFiels(false)
                .select("m.userName,m.realName,m.headPortrait,e.userId")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"), x->
                        x.eqc(CompareAlias.valueOf("m.id"),CompareAlias.valueOf("e.userId"))
                )
                .where(FieldValue.fromCondition("e.securityId.eq",req.getSecurityId()))
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                .page(pageInfo)
                ;
        pageInfo.setRecordCount(respUserFavMapper.selectMultiTableCount(queryWrapper));
        TvPageList<TrFavUser> outList = new TvPageList<>();
        outList.setPageInfo(pageInfo);
        outList.setList(respUserFavMapper.selectFavUsers(queryWrapper));

        for(TrFavUser user:outList.getList()){
            user.setRealName(StringUtil.markLastName(user.getRealName()));
            user.setUserName(StringUtil.markCenterPhone(user.getUserName()));
        }
        return Result.success(outList);
    }

    private MultiTableQueryWrapper simpListQuery(TqBase req){
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbSecurityManage.class)
                .selectAllFiels(true)
                .select("e.securityId,fc.className")
                .select0(
                        //基金净值
                        SelectAlias.valueOf("case when e.type = 0 then pn.nav else rn.nav end nav",true)
                        //基金净值日期
                        ,SelectAlias.valueOf("case when e.type = 0 then pn.end_date else rn.end_date end navDate",true)
                        //累计净值
                        ,SelectAlias.valueOf("case when e.type = 0 then pn.accum_nav else rn.accum_nav end accumNav",true)
                        //年化收益
                        ,SelectAlias.valueOf("pi.return_a returnA",true)
                        //累计收益
                        ,SelectAlias.valueOf("pi.return_accum returnAccum",true)
                        //成立日期
                        ,SelectAlias.valueOf("case when e.type = 0 then (select ESTABLISH_DATE from `"+ MultiDbUtil.privateFundDb+"`.pfund where SECURITY_ID=e.SECURITY_ID) else (select ESTABLISH_DATE from `"+ MultiDbUtil.privateFundDb+"`.fund_class where SECURITY_ID=e.SECURITY_ID) end  as establishDate",true)
                        //基金简称
                        ,SelectAlias.valueOf("case when e.type = 0 then (select SEC_SHORT_NAME from `"+ MultiDbUtil.privateFundDb+"`.md_security where SECURITY_ID=e.SECURITY_ID) else f.SEC_SHORT_NAME end as secShortName",true)
                        //基金全称
                        ,SelectAlias.valueOf("case when e.type = 0 then (select SEC_FULL_NAME from `"+ MultiDbUtil.privateFundDb+"`.md_security where SECURITY_ID=e.SECURITY_ID) else f.SEC_FULL_NAME end as secFullName",true)
                        //近一年收益
                        ,SelectAlias.valueOf("case when e.type = 0 then pgr.return_rate_1y else rgr.return_rate_1y end returnRate1y",true)
                )
                .select0(SelectAliasUtil.getFavOfPro(req.getLoginUserId()))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentNav.class,"pn"),
                        x->x
                                .where(FieldField.fromCondition("pn.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNav.class,"rn"),
                        x->x
                                .where(FieldField.fromCondition("rn.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentIncome.class,"pi"),
                        x->x
                                .where(FieldField.fromCondition("pi.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFundClass.class,"fc"),
                        x->x
                                .where(FieldField.fromCondition("fc.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class,"f"),
                        x->x
                                .where(FieldField.fromCondition("f.fundId.eq","fc.fundId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentGr.class,"pgr"),
                        x->x
                                .where(FieldField.fromCondition("pgr.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNavGr.class,"rgr"),
                        x->x
                                .where(FieldField.fromCondition("rgr.securityId.eq","e.securityId"))
                )
                //.orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf("e.sort")))
                ;
        return queryWrapper;
    }
    /**
     * 推荐产品列表
     * @param req
     * @return
     */
    @ApiOperation(value = "推荐产品列表")
    @GetMapping("recommend/list")
    public Result<List<TrFunds>> recommendProList(TqRecommendSecurity req){
        List<TrFunds> funds = respTrFundsMapper.selectViewList(simpListQuery(req)
        .eq(CompareAlias.valueOf("e.recommend"),true))
        ;
        return Result.success(funds);
    }

    /**
     * 热搜产品列表
     * @param req
     * @return
     */
    @ApiOperation(value = "热搜产品列表")
    @GetMapping("hot/list")
    public Result<List<TrFunds>> hotProList(TqRecommendSecurity req){
        List<TrFunds> funds = respTrFundsMapper.selectViewList(simpListQuery(req)
                .eq(CompareAlias.valueOf("e.hotSearch"),true))
                ;
        return Result.success(funds);
    }

}
