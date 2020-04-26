package com.rong.assembly.api.controller.usercenter;

import com.rong.assembly.api.module.request.uc.account.*;
import com.rong.assembly.api.module.response.user.TrUserFundAccountIndex;
import com.rong.assembly.api.service.UserManagerService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.module.KvPair;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.DateUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.tong.fund.mapper.FundNavMapper;
import com.rong.tong.fund.module.entity.TbFundNav;
import com.rong.tong.fund.module.query.TsFundNav;
import com.rong.tong.pfunds.mapper.PfundNavMapper;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.entity.TbPfundNav;
import com.rong.tong.pfunds.module.query.TsPfundNav;
import com.rong.user.module.entity.TbUserFundAccount;
import com.rong.user.module.query.TsUserFundAccount;
import com.rong.user.module.request.TqUserFundAccount;
import com.rong.user.module.view.TvUserFundAccount;
import com.rong.user.service.UserFundAccountService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CollectionUtils;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 基金记账中心
 */
@Api(tags = "基金记账中心")
@RestController
@RequestMapping("user/fundAccount")
public class UserFundAccountController {

    @Value("${maxFundAccountOfDay:10}")
    private int maxFundAccountOfDay;
    @Autowired
    private UserFundAccountService userFundAccountService;
    @Autowired
    private PfundNavMapper pfundNavMapper;
    @Autowired
    private FundNavMapper fundNavMapper;
    @Autowired
    private UserManagerService userManagerService;

    /**
     * 账户记录首页信息
     * @param req
     * @return
     */
    @ApiOperation(value = "账户记录首页信息")
    @GetMapping(value="index")
    public Result<TrUserFundAccountIndex> index(TqFundAccountIndex req){
        TqFundAccountList reqList = new TqFundAccountList();
        BeanUtils.copyProperties(req,reqList);
        reqList.setPageInfo(new PageInfo());
        Result<TvPageList<TvUserFundAccount>> result = list(reqList);
        TrUserFundAccountIndex fundAccountIndex = userManagerService.selectSumFundIndex(req.getUserId());
        fundAccountIndex.setPageList(result.getContent());
        return Result.success(fundAccountIndex);
    }
    /**
     * 账户记录列表
     * @param req
     * @return
     */
    @ApiOperation(value = "账户记录列表")
    @GetMapping(value="list")
    public Result<TvPageList<TvUserFundAccount>> list(TqFundAccountList req){
        MultiTableQueryWrapper queryWrapper =
                userFundAccountService.getMultiCommonWrapper()
                        .selectAllFiels(true)
                        .select("md.secShortName,md.secFullName")
                        .select0(
                                //私募净值
                                SelectAlias.valueOf("(select nav from `tong-rong`.pfund_nav where security_id=e.security_id order by end_date desc limit 1) priNav",true)
                                //私募净值日期
                                ,SelectAlias.valueOf("(select end_date from `tong-rong`.pfund_nav where security_id=e.security_id order by end_date desc limit 1) navDate",true)
                                //公募净值
                                ,SelectAlias.valueOf("(select nav from `tong-rong`.fund_nav where security_id=e.security_id order by end_date desc limit 1) raisedNav",true)
                                //公募净值日期
                                ,SelectAlias.valueOf("(select end_date from `tong-rong`.fund_nav where security_id=e.security_id order by end_date desc limit 1) navDate",true)
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"md"), md->
                                md.eqc(CompareAlias.valueOf("md.securityId"),CompareAlias.valueOf("e.securityId")))
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.updateDate")))
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.createDate")))
                        .page(req.getPageInfo())
                ;
        if(null == req.getDeltag()){
            req.setDeltag(false);
        }
        queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        if(null != req.getSecFullName()){
            queryWrapper.or(x->x
                    .like(CompareAlias.valueOf("md.secShortName"),"%"+req.getSecFullName()+"%")
                    .like(CompareAlias.valueOf("md.secFullName"),"%"+req.getSecFullName()+"%")
            );
        }
        if(null != req.getBeginBuyDate()){
            queryWrapper.ge(CompareAlias.valueOf("e.buyDate"),req.getBeginBuyDate());
        }
        if(null != req.getEndBuyDate()){
            queryWrapper.le(CompareAlias.valueOf("e.buyDate"),req.getEndBuyDate());
        }
        if(null != req.getBeginShare()){
            queryWrapper.ge(CompareAlias.valueOf("e.share"),req.getBeginShare());
        }
        if(null != req.getEndBuyShare()){
            queryWrapper.le(CompareAlias.valueOf("e.share"),req.getEndBuyShare());
        }
        if(null != req.getBeginPrincipal()){
            queryWrapper.ge(CompareAlias.valueOf("e.principal"),req.getBeginPrincipal());
        }
        if(null != req.getEndBuyShare()){
            queryWrapper.le(CompareAlias.valueOf("e.principal"),req.getEndBuyShare());
        }
        if(null != req.getId()){
            queryWrapper.eq(CompareAlias.valueOf("e.id"),req.getId());
        }
        return Result.success(userFundAccountService.selectPageList(queryWrapper));
    }
    /**
     * 添加一条记录
     * @param req
     * @return
     */
    @ApiOperation(value = "添加一条记录")
    @PostMapping(value="add")
    public Result<Boolean> add(@RequestBody TqAddFundAccount req){
        //

        if(userFundAccountService.selectCount(WrapperFactory.queryWrapper()
                .eq(TsUserFundAccount.Fields.userId,req.getUserId())
                .ge(TsUserFundAccount.Fields.createDate, DateUtil.getCurDateTime(DateUtil.yyyy_MM_dd_EN))
        ) > maxFundAccountOfDay){
            throw new DuplicateDataException("一天内已经添加多条记录，请明天此时再添加吧");
        }
        TbUserFundAccount fundAccount = new TbUserFundAccount();
        BeanUtils.copyProperties(req,fundAccount);
        //先查公募，再查私募
        if(null == req.getBuyDateStr()){
            fundAccount.setBuyDate(new Date());
        }else{
            fundAccount.setBuyDate(DateUtil.getDate(req.getBuyDateStr(), DateUtil.yyyy_MM_dd_EN));
        }
        //如果修改了净值日期
        KvPair<Date,BigDecimal> theNavInfo = findNavBySecurityIdAndEndDate(fundAccount.getSecurityId(), fundAccount.getBuyDate());
        if(null == theNavInfo || null == theNavInfo.getValue()){
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"基金在所填日期不存在净值");
        }
        fundAccount.setThenNav(theNavInfo.getValue());
        //份数 = 本金/基金净值
        fundAccount.setShare(fundAccount.getPrincipal().divide(fundAccount.getThenNav(),8, RoundingMode.HALF_UP));
        fundAccount.setUpdateDate(new Date());
        userFundAccountService.insert(new TqUserFundAccount().setEntity(fundAccount));
        return Result.success(true);
    }

    /**
     * 编辑一条记录
     * @param req
     * @return
     */
    @ApiOperation(value = "编辑一条记录")
    @PostMapping(value="edit")
    public Result<Boolean> edit(@RequestBody TqEditFundAccount req){
        //查出记录
        TbUserFundAccount fundAccount = userFundAccountService.selectOne(
                WrapperFactory.queryWrapper()
                        .eq(TsUserFundAccount.Fields.id,req.getId())
                        .eq(TsUserFundAccount.Fields.userId,req.getUserId())
        );
        if(null == fundAccount){
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"账户记录不存在");
        }
        fundAccount.setPrincipal(req.getPrincipal());
        //如果修改了净值日期
        if(null != req.getBuyDateStr()){
            fundAccount.setBuyDate(DateUtil.getDate(req.getBuyDateStr(), DateUtil.yyyy_MM_dd_EN));
            KvPair<Date,BigDecimal> theNavInfo = findNavBySecurityIdAndEndDate(fundAccount.getSecurityId(), fundAccount.getBuyDate());
            if(null == theNavInfo || null == theNavInfo.getValue()){
                return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"基金在所填日期不存在净值");
            }
            fundAccount.setThenNav(theNavInfo.getValue());
        }
        //重新计算分数
        //份数 = 本金/基金净值
        fundAccount.setShare(fundAccount.getPrincipal().divide(fundAccount.getThenNav(), 8, RoundingMode.HALF_UP));
        userFundAccountService.updateByPrimary(new TqUserFundAccount().setEntity(fundAccount));
        return Result.success(true);
    }
    private KvPair<Date,BigDecimal> findNavBySecurityIdAndEndDate(Long securityId, Date endDate){
        KvPair<Date,BigDecimal> navInfo = new KvPair<>();
        if(null == endDate){
            return null;
        }
        TbPfundNav pfundNav = pfundNavMapper.selectOne(
                WrapperFactory.queryWrapper().select(TsPfundNav.Fields.nav)
                        .eq(TsPfundNav.Fields.securityId,securityId)
                        .le(TsPfundNav.Fields.endDate,endDate)
                        .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("endDate")))
        );
        if(null != pfundNav){
            navInfo.setKey(pfundNav.getEndDate());
            navInfo.setValue(pfundNav.getNav());
            return navInfo;
        }
        TbFundNav fundNav = fundNavMapper.selectOne(
                WrapperFactory.queryWrapper().select(TsFundNav.Fields.nav)
                        .eq(TsFundNav.Fields.securityId,securityId)
                        .le(TsFundNav.Fields.endDate,endDate)
                        .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("endDate")))
        );
        if(null != fundNav){
            navInfo.setKey(fundNav.getEndDate());
            navInfo.setValue(fundNav.getNav());
            return navInfo;
        }
        return null;
    }

    /**
     * 删除记录
     * @param req
     * @return
     */
    @ApiOperation(value = "删除记录")
    @PostMapping(value="remove")
    public Result<Boolean> remove(@RequestBody TqDelOrRecFundAccounts req){
        if(CollectionUtils.isEmpty(req.getIds())){
            return Result.miss(CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE,"ids必填");
        }
        userFundAccountService.updateSelectItem(new UpdateWrapper()
                .update(
                        Elements.valueOf(TsUserFundAccount.Fields.deltag,true)
                        ,
                        Elements.valueOf(TsUserFundAccount.Fields.updateDate,new Date())
                )
                .in(TsUserFundAccount.Fields.id,req.getIds())
                .eq(TsUserFundAccount.Fields.userId,req.getUserId())
        );
        return Result.success(true);
    }

    /**
     * 恢复已删除的记录
     * @param req
     * @return
     */
    @ApiOperation(value = "恢复已删除的记录")
    @PostMapping(value="restore")
    public Result<Boolean> restore(@RequestBody TqDelOrRecFundAccounts req){
        if(CollectionUtils.isEmpty(req.getIds())){
            return Result.miss(CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE,"ids必填");
        }
        userFundAccountService.updateSelectItem(new UpdateWrapper()
                .update(
                        Elements.valueOf(TsUserFundAccount.Fields.deltag,false)
                        ,
                        Elements.valueOf(TsUserFundAccount.Fields.updateDate,new Date())
                )
                .in(TsUserFundAccount.Fields.id,req.getIds())
                .eq(TsUserFundAccount.Fields.userId,req.getUserId())
        );
        return Result.success(true);
    }

}
