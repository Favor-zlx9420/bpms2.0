package com.rong.assembly.api.service.impl;

import com.rong.assembly.api.mapper.GeneralMapper;
import com.rong.assembly.api.mapper.RespTrFundsMapper;
import com.rong.assembly.api.module.request.buz.TqFundsOfManager;
import com.rong.assembly.api.module.request.buz.TqFundsOfOrg;
import com.rong.assembly.api.module.request.uc.fav.TqFavProList;
import com.rong.assembly.api.module.response.product.TrFavFund;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.assembly.api.service.ProductService;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.assembly.api.util.SortUtil;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.MultiDbUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentPerf;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNav;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNavGr;
import com.rong.tong.fund.module.entity.TbFund;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.entity.TbFundManagerNew;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RespTrFundsMapper respTrFundsMapper;
    @Autowired
    private GeneralMapper generalMapper;
    /**
     * 获取公司旗下私募产品
     * @param req
     * @return
     */
    @Override
    public TvPageList<TrFunds> priFundsOfOrg(TqFundsOfOrg req) {
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbPfund.class)
                .selectAllFiels(false)
                .select("e.securityId,e.establishDate,ci.returnA,ci.returnAccum,cn.nav,cn.accumNav,cp.returnRateYtd returnOfThisYear,cp.returnRateYtd")
                .select0(
                        SelectAlias.valueOf("(select count(id) from `tong-rong`.pfund_inst_rep where SECURITY_ID=e.SECURITY_ID and PARTY_ID= e.INVEST_CONSULTANT) as isResp",true)
                        ,
                        SelectAlias.valueOf("(select SEC_SHORT_NAME from `tong-rong`.md_security where SECURITY_ID=e.SECURITY_ID) as secShortName",true)
                        ,
                        SelectAlias.valueOf("(select SEC_FULL_NAME from `tong-rong`.md_security where SECURITY_ID=e.SECURITY_ID) as secFullName",true)
                )
                .select0(SelectAliasUtil.getFavOfPro(req.getLoginUserId()))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentIncome.class,"ci"),
                        x->x.where(FieldField.fromCondition("ci.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentNav.class,"cn"),
                        x->x.where(FieldField.fromCondition("cn.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentGr.class,"cp"),
                        x->x
                                .where(FieldField.fromCondition("cp.securityId.eq","e.securityId"))
                )
                .where(FieldValue.fromCondition("e.investConsultant.eq",req.getPartyId()))
                .page(req.getPageInfo())
                ;
        SortUtil.setOrgSort(queryWrapper,pageInfo);
        if(StringUtil.isEmpty(pageInfo.getSortField())){
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("isResp",true)));
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("returnAccum",true)));
        }
        TvPageList<TrFunds> fundsPageList = new TvPageList<>();
        pageInfo.setRecordCount(respTrFundsMapper.selectMultiTableCount(queryWrapper));
        fundsPageList.setPageInfo(pageInfo);
        fundsPageList.setList(respTrFundsMapper.selectViewList(queryWrapper));
        return fundsPageList;
    }

    /**
     * 获取公司旗下公募产品列表
     * @param req
     * @return
     */
    @Override
    public TvPageList<TrFunds> raisedFundsOfOrg(TqFundsOfOrg req) {
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbFundClass.class)
                .selectAllFiels(false)
                .select("e.securityId,e.establishDate,cn.nav,cn.accumNav,e.className,f.secFullName,f.secShortName,cng.returnRate,cng.returnRate returnA,cng.returnRateEst returnAccum")
                .select0(SelectAlias.valueOf("cng.return_rate_ytd returnOfThisYear",true))
                .select0(SelectAliasUtil.getFavOfPro(req.getLoginUserId()))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNavGr.class,"cng"),
                        x->x.where(FieldField.fromCondition("cng.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNav.class,"cn"),
                        x->x.where(FieldField.fromCondition("cn.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class,"f"),
                        x->x.where(FieldField.fromCondition("f.fundId.eq","e.fundId"))
                )
                .where(FieldValue.fromCondition("f.managementCompany.eq",req.getPartyId()))
                .page(pageInfo)
                ;
        SortUtil.setOrgSort(queryWrapper,pageInfo);
        if(StringUtil.isEmpty(pageInfo.getSortField())){
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("returnAccum",true)));
        }
        TvPageList<TrFunds> fundsPageList = new TvPageList<>();
        pageInfo.setRecordCount(respTrFundsMapper.selectMultiTableCount(queryWrapper));
        fundsPageList.setPageInfo(pageInfo);
        fundsPageList.setList(respTrFundsMapper.selectViewList(queryWrapper));
        return fundsPageList;
    }

    /**
     * 获取基金经理旗下的私募基金
     * @param req
     * @return
     */
    @Override
    public TvPageList<TrFunds> priFundsOfManager(TqFundsOfManager req) {
        PageInfo pageInfo = req.getPageInfo();
        TvPageList<TrFunds> fundsPageList = new TvPageList<>();
        pageInfo.setRecordCount(generalMapper.count(WrapperFactory.multiQueryWrapper(TbPfundManager.class).eq(CompareAlias.valueOf("e.personId"), req.getPersonId())));
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbPfundManager.class)
                .selectAllFiels(false)
                .select("e.securityId,pf.establishDate,ci.returnA,ci.returnAccum,cn.nav,cn.accumNav,cp.returnOfThisYear")
                .select0(
                        SelectAlias.valueOf("(select count(id) from `tong-rong`.pfund_manager_rep where SECURITY_ID=e.SECURITY_ID and PERSON_ID= e.PERSON_ID) as isResp", true)
                        ,
                        SelectAlias.valueOf("(select SEC_SHORT_NAME from `tong-rong`.md_security where SECURITY_ID=e.SECURITY_ID) as secShortName", true)
                        ,
                        SelectAlias.valueOf("(select SEC_FULL_NAME from `tong-rong`.md_security where SECURITY_ID=e.SECURITY_ID) as secFullName", true)
                )
                .select0(SelectAliasUtil.getFavOfPro(req.getLoginUserId()))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentIncome.class, "ci"),
                        x -> x.where(FieldField.fromCondition("ci.securityId.eq", "e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentNav.class, "cn"),
                        x -> x.where(FieldField.fromCondition("cn.securityId.eq", "e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentPerf.class, "cp"),
                        x -> x.where(FieldField.fromCondition("cp.securityId.eq", "e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class, "pf"),
                        x -> x.where(FieldField.fromCondition("pf.securityId.eq", "e.securityId"))
                )
                .where(FieldValue.fromCondition("e.personId.eq", req.getPersonId()))
                .page(pageInfo)
                ;
        queryWrapper.orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("isResp", true)));
        fundsPageList.setPageInfo(pageInfo);
        fundsPageList.setList(respTrFundsMapper.selectViewList(queryWrapper));
        return fundsPageList;
    }

    /**
     * 获取基金经理旗下的公募基金
     * @param req
     * @return
     */
    @Override
    public TvPageList<TrFunds> raisedFundsOfManager(TqFundsOfManager req) {
        PageInfo pageInfo = req.getPageInfo();
        TvPageList<TrFunds> fundsPageList = new TvPageList<>();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbFundManagerNew.class)
                .selectAllFiels(false)
                .select("e.securityId,f.establishDate,cn.nav,cn.accumNav,fc.className,f.secShortName,f.secFullName,cp.returnRate,cp.returnRate returnA,cp.returnRateEst returnRateEst,cp.returnRateEst returnAccum")
                .select0(SelectAlias.valueOf("cp.return_rate_ytd returnOfThisYear", true))
                .select0(SelectAliasUtil.getFavOfPro(req.getLoginUserId()))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNav.class, "cn"),
                        x -> x.where(FieldField.fromCondition("cn.securityId.eq", "e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNavGr.class, "cp"),
                        x -> x.where(FieldField.fromCondition("cp.securityId.eq", "e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFundClass.class, "fc"),
                        x -> x.where(FieldField.fromCondition("fc.securityId.eq", "e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class, "f"),
                        x -> x.where(FieldField.fromCondition("f.fundId.eq", "e.fundId"))
                )
                .where(FieldValue.fromCondition("e.personId.eq", req.getPersonId()))
                .page(pageInfo)
                ;
        pageInfo.setRecordCount(respTrFundsMapper.selectMultiTableCount(queryWrapper));
        fundsPageList.setPageInfo(pageInfo);
        fundsPageList.setList(respTrFundsMapper.selectViewList(queryWrapper));
        return fundsPageList;
    }

    /**
     * 用户收藏的基金列表
     * @param req
     * @return
     */
    @Override
    public TvPageList<TrFavFund> selectFavFunds(TqFavProList req) {
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbUserProFav.class)
                .selectAllFiels(true)
                .select0(SelectAliasUtil.getFavOfPro(req.getUserId()))
                .select("e.securityId,fc.className")
                .select0(
                        //基金净值
                        SelectAlias.valueOf("case when e.type = 0 then pn.nav else rn.nav end nav",true)
                        //累计净值
                        ,SelectAlias.valueOf("case when e.type = 0 then pn.accum_nav else rn.accum_nav end accumNav",true)
                        //净值日期
                        ,SelectAlias.valueOf("case when e.type = 0 then pgr.end_date else rgr.end_date end navDate",true)
                        //累计净值(分红再投资)
                        ,SelectAlias.valueOf("case when e.type = 0 then pn.adj_nav else rn.adj_nav end adjNav",true)
                        //调整净值回报
                        ,SelectAlias.valueOf("case when e.type = 0 then pn.return_rate else rn.return_rate end returnRate",true)
                        //今年以来年化
                        ,SelectAlias.valueOf("case when e.type = 0 then pgr.RETURN_RATE_YTD else rgr.RETURN_RATE_YTD end returnOfThisYear",true)
                        //近一个月收益
                        ,SelectAlias.valueOf("case when e.type = 0 then pgr.RETURN_RATE_1M else rgr.RETURN_RATE_1M end returnRate1m",true)
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
                        //基金类型
                        ,SelectAlias.valueOf("case when e.type = 0 then '私募' else '公募' end produceType",true)
                        //基金状态(私募)
                        ,SelectAlias.valueOf("(select VALUE_NAME_CN from `tong-rong`.sys_code where CODE_TYPE_ID=40034 and VALUE_NUM_CD=ppf.STATUS limit 1) priStatus",true)
                        //投资策略（私募）
                        ,SelectAlias.valueOf("(select VALUE_NAME_CN from `tong-rong`.sys_code where CODE_TYPE_ID=40032 and VALUE_NUM_CD=ppf.INVEST_STRATEGY limit 1) priInvestStrategy",true)
                        //基金状态（公募）
                        ,SelectAlias.valueOf("IF(fc.OPERATION_MODE='C','开放','封闭') raiseStatus",true)
                        //投资策略（公募）
                        ,SelectAlias.valueOf("(select VALUE_NAME_CN from `tong-rong`.sys_code a,`tong-rong`.fund_type b where a.CODE_TYPE_ID=40123 and a.VALUE_NUM_CD = b.VALUE_NUM_CD and b.SECURITY_ID=e.security_id) raiseInvestStrategy",true)


                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentNav.class,"pn"),
                        x->x.where(FieldField.fromCondition("pn.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNav.class,"rn"),
                        x->x.where(FieldField.fromCondition("rn.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentIncome.class,"pi"),
                        x->x.where(FieldField.fromCondition("pi.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentGr.class,"pgr"),
                        x->x.where(FieldField.fromCondition("pgr.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNavGr.class,"rgr"),
                        x->x.where(FieldField.fromCondition("rgr.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFundClass.class,"fc"),
                        x->x.where(FieldField.fromCondition("fc.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class,"f"),
                        x->x.where(FieldField.fromCondition("f.fundId.eq","fc.fundId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class,"ppf"),
                        x->x.where(FieldField.fromCondition("ppf.securityId.eq","e.securityId"))
                )
                .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(pageInfo)
                ;
        if(null != req.getType()){
            queryWrapper.eq(CompareAlias.valueOf("e.type"),req.getType());
        }
        if(CommonUtil.isNormalSql(pageInfo.getSortField())){
            queryWrapper.orderBy(OrderBy.valueOf(Order.valueOf(pageInfo.getSortDistanct().toUpperCase()), SelectAlias.valueOf(pageInfo.getSortField())));
        }else{
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")));
        }
        pageInfo.setRecordCount(generalMapper.count(queryWrapper));
        TvPageList<TrFavFund> pageList = new TvPageList<>();
        pageList.setPageInfo(pageInfo);
        pageList.setList(respTrFundsMapper.selectFavFunds(queryWrapper));
        return pageList;
    }
}
