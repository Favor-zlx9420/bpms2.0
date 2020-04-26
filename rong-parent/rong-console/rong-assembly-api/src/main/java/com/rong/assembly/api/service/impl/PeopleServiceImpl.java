package com.rong.assembly.api.service.impl;

import com.rong.assembly.api.mapper.RespPeopleInfoMapper;
import com.rong.assembly.api.module.request.TqPeopleInfo;
import com.rong.assembly.api.module.request.uc.fav.TqFavPeopleList;
import com.rong.assembly.api.module.response.people.TrFavManager;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.assembly.api.service.OrgService;
import com.rong.assembly.api.service.PeopleService;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentManagerRanking;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.view.TvPeopleManage;
import com.rong.fundmanage.service.PeopleManageService;
import com.rong.tong.pfunds.mapper.MdSecurityMapper;
import com.rong.tong.pfunds.mapper.PfundManagerRepMapper;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.query.TsMdSecurity;
import com.rong.user.module.entity.TbUserPeopleFav;
import com.vitily.mybatis.core.entity.FieldField;
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
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleManageService peopleManageService;
    @Autowired
    private RespPeopleInfoMapper respPeopleInfoMapper;
    @Autowired
    private PfundManagerRepMapper pfundManagerRepMapper;
    @Autowired
    private OrgService orgService;
    @Autowired
    private MdSecurityMapper mdSecurityMapper;
    @Override
    public MultiTableQueryWrapper getCommonOfManager(Class<?> tbclass,String peopleIdStr,Long userId){
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(tbclass)
                .select0(
                        SelectAlias.valueOf("(SELECT YEAR from `tong-rong`.pfund_mana_year where person_id=e.person_id limit 1) workYear",true)
                        ,//累计收益(只有私募)
                        SelectAlias.valueOf("(select avg(return_accum) from tb_private_funds_current_income where person_id=e.person_id) returnAccum",true)
                        ,//年化收益(只有私募)
                        SelectAlias.valueOf("(select avg(return_a) from tb_private_funds_current_income where person_id=e.person_id) returnA",true)
                        ,//平均私募净值
                        SelectAlias.valueOf("(select avg(nav) from tb_private_funds_current_nav where person_id=e.person_id) priNav",true)
                        ,//平均私募累计净值
                        SelectAlias.valueOf("(select avg(accum_nav) from tb_private_funds_current_nav where person_id=e.person_id) priAccumNav",true)
                        ,//私募今年以来收益
                        SelectAlias.valueOf("(select avg(return_rate_ytd) from tb_private_funds_current_gr where person_id=e.person_id) priReturnOfThisYear",true)
                        ,//平均公募净值
                        SelectAlias.valueOf("(select avg(nav) from tb_raised_fund_current_nav where person_id=e.person_id) raisedNav",true)
                        ,//平均公募累计净值
                        SelectAlias.valueOf("(select avg(accum_nav) from tb_raised_fund_current_nav where person_id=e.person_id) raisedAccumNav",true)
                        ,//公募今年以来收益
                        SelectAlias.valueOf("(select avg(return_rate_ytd) from tb_raised_fund_current_nav_gr where person_id=e.person_id) raisedReturnOfThisYear",true)
                        ,//代表作id(只有私募)
                        SelectAlias.valueOf("(select a.SECURITY_ID from `tong-rong`.pfund_manager_rep a where a.person_id=e.person_id order by a.id desc limit 1) as repSecurityId",true)
                        ,//代表作（只有私募）
                        SelectAlias.valueOf("(select b.SEC_FULL_NAME from `tong-rong`.pfund_manager_rep a left join `tong-rong`.md_security b on a.security_id=b.security_id where a.person_id=e.person_id order by a.id desc limit 1) as repSecurityFullName",true)
                        ,//代表作（只有私募）
                        SelectAlias.valueOf("(select b.SEC_SHORT_NAME from `tong-rong`.pfund_manager_rep a left join `tong-rong`.md_security b on a.security_id=b.security_id where a.person_id=e.person_id order by a.id desc limit 1) as repSecurityShortName",true)

                        ,SelectAlias.valueOf("(select count(DISTINCT security_id) from `tong-rong`.pfund_manager where person_id = e.person_id) priFundCounts",true)
                        ,SelectAlias.valueOf("(select count(DISTINCT security_id) from `tong-rong`.fund_manager_new where person_id = e.person_id) raisedFundCounts",true)
                        //SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =10024 and VALUE_NUM_CD=e.EDUCATION limit 1) workYear1",true),
                        //SelectAlias.valueOf("(select count(DISTINCT security_id) from `tong-rong`.pfund_manager where person_id = e.person_id) fundCounts",true)
                );
        if(null != userId){
            queryWrapper.select0(SelectAliasUtil.getFavOfPeople(peopleIdStr,userId));
        }
        return queryWrapper;
    }
    @Override
    public TrManager info(TqPeopleInfo req) {
        MultiTableQueryWrapper queryWrapper = getCommonOfManager(TbMdPeople.class,"e.person_id",req.getLoginUserId())
                .selectAllFiels(true)
                .select("mr.backgroundValue backgroundDesc,e.backgroundDesc profile")
                .select("mr.returnAccum repSecReturnAccum,mr.secShortName repSecurityShortName,mr.securityId repSecurityId")
                .select("pf.establishDate repEstablishDate")
                .select("cn.accumNav repSecAccumNav")
                .select0(
                        SelectAlias.valueOf("e.name realName",true)
                        ,
                        SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =10024 and VALUE_NUM_CD=e.EDUCATION limit 1) education",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentManagerRanking.class,"mr"), mr->
                        mr.eqc(CompareAlias.valueOf("mr.personId"),CompareAlias.valueOf("e.personId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class, "pf"),
                        x -> x.where(FieldField.fromCondition("pf.securityId.eq", "mr.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentNav.class,"cn"), cn->
                        cn.eqc(CompareAlias.valueOf("cn.securityId"),CompareAlias.valueOf("mr.securityId"))
                )
                .eq(CompareAlias.valueOf("e.personId"),req.getPersonId())
        ;
        TrManager manager = respPeopleInfoMapper.selectOneView(queryWrapper);

        if(null == manager){
            return null;
        }
        TvPeopleManage peopleManage = peopleManageService.selectOneView(
                peopleManageService.getMultiCommonWrapper().eq(CompareAlias.valueOf("e.personId"),req.getPersonId())
        );
        if(null != manager.getRepSecurityId()){
            TbMdSecurity repSecurity = mdSecurityMapper.selectOne(
                    WrapperFactory.queryWrapper()
                            .eq(TsMdSecurity.Fields.securityId,manager.getRepSecurityId())
            );
            if(null != repSecurity){
                manager.setRepSecurityShortName(repSecurity.getSecShortName());
                manager.setRepSecurityFullName(repSecurity.getSecFullName());
            }
        }
        if(null != peopleManage){
            manager.setUserId(peopleManage.getUserId());
            manager.setPosition(peopleManage.getPosition());
            manager.setHeadPortrait(peopleManage.getHeadPortrait());
            manager.setNickName(peopleManage.getNickName());

        }

        return manager;
    }

    @Override
    public TvPageList<TrFavManager> selectFavManager(TqFavPeopleList req) {
        PageInfo pageInfo = req.getPageInfo();
        TvPageList<TrFavManager> pageList = new TvPageList<>();

        MultiTableQueryWrapper queryWrapper = getCommonOfManager(TbUserPeopleFav.class,"e.person_id",req.getUserId())
                .selectAllFiels(false)
                .select("e.updateDate,mr.backgroundValue backgroundDesc,m.userId,e.personId")
                .select0(
                        SelectAlias.valueOf("(select GROUP_CONCAT(distinct PARTY_FULL_NAME separator '、') from `tong-rong`.pfund_resume where person_id=e.person_id and IS_INCUMBENT = '1') as companyNames",true)
                        ,
                        SelectAlias.valueOf("(select GROUP_CONCAT(distinct POSITION separator '、') from `tong-rong`.pfund_resume where person_id=e.person_id and IS_INCUMBENT = '1') as companyPosts",true)
                        ,
                        SelectAlias.valueOf("mp.name realName",true)
                        ,
                        SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =10024 and VALUE_NUM_CD=mp.EDUCATION limit 1) education",true)
                )

                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdPeople.class,"mp"), mi->mi.eqc(CompareAlias.valueOf("mp.personId"),CompareAlias.valueOf("e.personId")))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPeopleManage.class,"m"), mi->mi.eqc(CompareAlias.valueOf("m.personId"),CompareAlias.valueOf("mp.personId")))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentManagerRanking.class,"mr"), mr->
                        mr.eqc(CompareAlias.valueOf("mr.personId"),CompareAlias.valueOf("e.personId"))
                        )
                .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(pageInfo)
                ;
        pageInfo.setRecordCount(respPeopleInfoMapper.selectMultiTableCount(queryWrapper));
        if(CommonUtil.isNormalSql(pageInfo.getSortField())){
            queryWrapper.orderBy(OrderBy.valueOf(Order.valueOf(pageInfo.getSortDistanct().toUpperCase()), SelectAlias.valueOf(pageInfo.getSortField())));
        }else{
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")));
        }
        pageList.setPageInfo(pageInfo);
        pageList.setList(respPeopleInfoMapper.selectFavList(queryWrapper));
        TqPeopleInfo tqPeopleInfo = new TqPeopleInfo();
        for(TrFavManager favManager:pageList.getList()){
            if(StringUtil.isEmpty(favManager.getCompanyNames())) {
                tqPeopleInfo.setPersonId(favManager.getPersonId());
                favManager.setServiceOrgs(orgService.getServiceOrgOfManager(tqPeopleInfo));
                final StringBuilder companyNames = new StringBuilder();
                if (null != favManager.getServiceOrgs() && favManager.getServiceOrgs().size() > 0) {
                    favManager.getServiceOrgs().forEach(x->{
                        companyNames.append(x.getPartyShortName()).append("、");
                    });
                    companyNames.deleteCharAt(companyNames.length()-1);
                }
                favManager.setCompanyNames(companyNames.toString());
            }
        }

        return pageList;
    }
}
