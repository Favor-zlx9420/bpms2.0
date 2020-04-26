package com.rong.assembly.api.service.impl;

import com.rong.assembly.api.mapper.GeneralMapper;
import com.rong.assembly.api.mapper.RespOrgInfoMapper;
import com.rong.assembly.api.mapper.RespPeopleInfoMapper;
import com.rong.assembly.api.mapper.RespTrFundsMapper;
import com.rong.assembly.api.module.request.TqOrgInfo;
import com.rong.assembly.api.module.request.TqPeopleInfo;
import com.rong.assembly.api.module.response.org.TrRespOrg;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.assembly.api.module.response.product.TrRepSecurity;
import com.rong.assembly.api.service.OrgService;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.NoExistsException;
import com.rong.common.util.MultiDbUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentCompanyRanking;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentManagerRanking;
import com.rong.member.module.entity.TbMemBase;
import com.rong.tong.fund.module.entity.TbFundInstInfo;
import com.rong.tong.fund.module.entity.TbFundManagerInfo;
import com.rong.tong.fund.module.entity.TbFundManagerNew;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import com.rong.tong.pfunds.module.query.TsMdInstitution;
import com.rong.tong.pfunds.module.query.TsMdSecurity;
import com.rong.tong.pfunds.service.MdSecurityService;
import com.vitily.mybatis.core.entity.FieldField;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private MdSecurityService mdSecurityService;
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private RespPeopleInfoMapper respPeopleInfoMapper;
    @Autowired
    private RespOrgInfoMapper respOrgInfoMapper;
    @Autowired
    private RespTrFundsMapper respTrFundsMapper;
    @Override
    public TrRespOrg selectPriOrgInfo(TqOrgInfo req) {
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbPfundInstInfo.class)
                .select("ccr.scale,ccr.mainFundType,e.ideaStrategy,mi.regDate,e.regCd,e.profile,e.legalRep keyPerson,e.partyId")
                .select0(
                        SelectAlias.valueOf("(select count(DISTINCT 1) from `"+ MultiDbUtil.manageFundsDb+"`.tb_direct_store_org where party_id=e.party_id) storeUser",true),
                        SelectAlias.valueOf("(select SECURITY_ID from `tong-rong`.pfund_inst_rep where party_id=e.party_id limit 1) repSecurityId",true),
                        SelectAlias.valueOf("(select ifnull(NUM_ALL,0) from `tong-rong`.pfund_inst_rep where party_id=e.party_id limit 1) numAll",true),
                        SelectAlias.valueOf("(select avg(return_of_latest_year) from `"+ MultiDbUtil.manageFundsDb+"`.tb_private_funds_current_perf where party_id=e.party_id and return_of_latest_year is not null) returnOfLatestYear",true),
                        SelectAlias.valueOf("(select avg(return_a) from `"+ MultiDbUtil.manageFundsDb+"`.tb_private_funds_current_income where party_id=e.party_id and return_a is not null) returnA",true),
                        SelectAlias.valueOf("(select avg(return_accum) from `"+ MultiDbUtil.manageFundsDb+"`.tb_private_funds_current_income where party_id=e.party_id and return_accum is not null) returnAccum",true)
                        ,SelectAlias.valueOf("(select INST_ID from `tong-rong`.pfund_inst_amac where party_id=e.party_id and RECORD_STATUS=1 limit 1) instId",true)
                        ,SelectAlias.valueOf("(select ACT_CAP from `tong-rong`.pfund_inst_amac where party_id=e.party_id and RECORD_STATUS=1 limit 1) actCap",true)

                )
                .select0(SelectAliasUtil.getFavOfOrg(req.getLoginUserId()))
                //
                .select("mi.partyShortName,mi.partyFullName,mi.regCap,mi.officeAddr")
                .select0(SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =10003 and VALUE_NUM_CD=mi.REG_CITY) regCity",true))

                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentCompanyRanking.class,"ccr"),
                        x->x.where(FieldField.fromCondition("ccr.partyId.eq","e.partyId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->
                        mi.eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("e.partyId"))
                )
                .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
                ;
        TrRespOrg priOrg = respOrgInfoMapper.selectOneView(queryWrapper);
        if(null == priOrg){
            throw new NoExistsException("机构不存在");
        }
        if(priOrg.getRepSecurityId() != null){
            List<TrRepSecurity> repSecurities = respTrFundsMapper.selectRepFunds(
                    WrapperFactory.multiQueryWrapper(TbMdSecurity.class)
                            .select("e.securityId,e.secShortName secShortName,e.secFullName secFullName")
                            .select0(
                                    SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =40032 and VALUE_NUM_CD=p.INVEST_STRATEGY) investStrategy",true)
                            )
                            .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class,"p"), p->
                                    p.eqc(CompareAlias.valueOf("p.securityId"),CompareAlias.valueOf("e.securityId"))
                                    )
                        .eq(CompareAlias.valueOf("e.securityId"),priOrg.getRepSecurityId())
            );
            if(repSecurities.size() > 0){
                priOrg.setRepSecurityShortName(repSecurities.get(0).getSecShortName());
                priOrg.setRepSecurityFullName(repSecurities.get(0).getSecFullName());
                priOrg.setInvestStrategy(repSecurities.get(0).getInvestStrategy());
            }
        }


        return priOrg;
    }

    @Override
    public TrRespOrg selectRaisedOrgInfo(TqOrgInfo req) {
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbFundInstInfo.class)
                .select("e.profile,e.partyId,e.regDate")
                .select0(
                        SelectAlias.valueOf("(select count(DISTINCT 1) from `"+ MultiDbUtil.manageFundsDb+"`.tb_direct_store_org where party_id=e.party_id) storeUser",true)
                        ,SelectAlias.valueOf("(select avg(return_rate_1y) from `"+ MultiDbUtil.manageFundsDb+"`.tb_raised_fund_current_nav_gr where party_id=e.party_id and return_rate_1y is not null) returnOfLatestYear",true)
                        ,SelectAlias.valueOf("(select avg(return_rate) from `"+ MultiDbUtil.manageFundsDb+"`.tb_raised_fund_current_nav_gr where party_id=e.party_id and return_rate is not null) returnA",true)
                        ,SelectAlias.valueOf("(select avg(return_rate_est) from `"+ MultiDbUtil.manageFundsDb+"`.tb_raised_fund_current_nav_gr where party_id=e.party_id and return_rate_est is not null) returnAccum",true)
                        ,SelectAlias.valueOf("e.REG_DATE recordDate",true)
                        ,SelectAlias.valueOf("e.LEGAL_REP keyPerson",true)
                        ,SelectAlias.valueOf("(select count(e.id) from `tong-rong`.fund_class e left join `tong-rong`.fund f on e.FUND_ID=f.FUND_ID where f.MANAGEMENT_COMPANY=e.party_id) numAll",true)
                        ,SelectAlias.valueOf("(select TOTAL_ASSET from `tong-rong`.fund_aum where PARTY_ID=e.party_id order by END_DATE desc limit 1) scaleD",true)
                )
                //
                .select("e.partyShortName,e.partyFullName,e.regCap,e.officeAddr")
                .select0(SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =10003 and VALUE_NUM_CD=e.REG_CITY) regCity",true))

                .select0(SelectAliasUtil.getFavOfOrg(req.getLoginUserId()))
                .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
                ;
        TrRespOrg raised = respOrgInfoMapper.selectOneView(queryWrapper);
        if(null == raised){
            throw new NoExistsException("机构不存在");
        }
        if(null != raised.getRepSecurityId()) {
            TbMdSecurity repSecurity = mdSecurityService.selectOne(
                    new QueryWrapper().eq(TsMdSecurity.Fields.securityId,raised.getRepSecurityId())
                            .select(TsMdSecurity.Fields.secFullName)
            );
            if(null != repSecurity){
                raised.setRepSecurityFullName(repSecurity.getSecFullName());
            }
        }
        return raised;
    }

    /**
     * 获取私募机构下的基金经理列表
     * @param req
     * @return
     */
    @Override
    public List<TrManager> selectManagerOfPriOrg(TqOrgInfo req) {
        List<Long> personIds = generalMapper.selectLongList(WrapperFactory.multiQueryWrapper(TbPfundManager.class)
                .select0(SelectAlias.valueOf(" DISTINCT PERSON_ID personId",true))
                .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
        );
        if(personIds.size() == 0){
            return Collections.emptyList();
        }
        List<TrManager> peoples = respPeopleInfoMapper.selectViewList(
                WrapperFactory.multiQueryWrapper(TbMdPeople.class)
                        .selectAllFiels(false)
                        .select0(
                                SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =10024 and VALUE_NUM_CD=e.EDUCATION limit 1) education",true),
                                SelectAlias.valueOf("(SELECT YEAR from `tong-rong`.pfund_mana_year where person_id=e.person_id limit 1) workYear",true),
                                SelectAlias.valueOf("(select count(DISTINCT security_id) from `tong-rong`.pfund_manager where person_id = e.person_id) priFundCounts",true)
                        )
                        .select("mr.returnAccum repSecReturnAccum,mr.secShortName repSecurityShortName,mr.securityId repSecurityId")
                        .select("e.personId,e.name realName,e.backgroundDesc,pm.userId,mb.position,mb.nickName,mb.headPortrait,mb.type")
                        .select("pf.establishDate repEstablishDate")
                        .select0(SelectAliasUtil.getFavOfPeople(req.getLoginUserId()))
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbPeopleManage.class,"pm"), pm->
                                pm.eqc(CompareAlias.valueOf("pm.personId"),CompareAlias.valueOf("e.personId"))
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), mb->
                                mb.eqc(CompareAlias.valueOf("mb.id"),CompareAlias.valueOf("pm.userId"))
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentManagerRanking.class,"mr"), mr->
                                mr.eqc(CompareAlias.valueOf("mr.personId"),CompareAlias.valueOf("e.personId"))
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class, "pf"),
                                x -> x.where(FieldField.fromCondition("pf.securityId.eq", "mr.securityId"))
                        )
                        .in(CompareAlias.valueOf("e.personId"),personIds)

        );
        return peoples;
    }

    /**
     * 获取公募机构下的基金经理列表
     * @param req
     * @return
     */
    @Override
    public List<TrManager> selectManagerOfRaisedOrg(TqOrgInfo req) {
        List<Long> personIds = generalMapper.selectLongList(WrapperFactory.multiQueryWrapper(TbFundManagerNew.class)
                .select0(SelectAlias.valueOf(" DISTINCT PERSON_ID personId",true))
                .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
                .page(new PageInfo())//只显示前十个
        );
        if(personIds.size() == 0){
            return Collections.emptyList();
        }
        List<TrManager> peoples = respPeopleInfoMapper.selectViewList(
                WrapperFactory.multiQueryWrapper(TbFundManagerInfo.class)
                        .selectAllFiels(false)
                        .select0(
                                SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =10024 and VALUE_NUM_CD=e.EDUCATION limit 1) education",true),
                                SelectAlias.valueOf("(SELECT YEAR from `tong-rong`.pfund_mana_year where person_id=e.person_id limit 1) workYear",true),
                                SelectAlias.valueOf("(select count(DISTINCT security_id) from `tong-rong`.fund_manager_new where person_id = e.person_id) raisedFundCounts",true)
                        )
                        .select("e.personId,e.name realName,e.backgroundDesc,pm.userId,mb.position,mb.nickName,mb.headPortrait,mb.type")
                        .select0(SelectAliasUtil.getFavOfPeople(req.getLoginUserId()))
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbPeopleManage.class,"pm"), pm->
                                pm.eqc(CompareAlias.valueOf("pm.personId"),CompareAlias.valueOf("e.personId"))
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), mb->
                                mb.eqc(CompareAlias.valueOf("mb.id"),CompareAlias.valueOf("pm.userId"))
                        )
                        .in(CompareAlias.valueOf("e.personId"),personIds)

        );
        return peoples;
    }

    /**
     * 获取基金经理所服务的基金机构
     * @param req
     * @return
     */
    @Override
    public List<TrRespOrg> getServiceOrgOfManager(TqPeopleInfo req) {
        //所在公司
        List<Long> partyIds = generalMapper.selectLongList(
                WrapperFactory.multiQueryWrapper(TbPfundManager.class)
                        .select0(SelectAlias.valueOf("DISTINCT PARTY_ID partyId",true))
                        .eq(CompareAlias.valueOf("e.personId"),req.getPersonId())
        );
        partyIds.addAll(
                generalMapper.selectLongList(
                        WrapperFactory.multiQueryWrapper(TbFundManagerNew.class)
                                .select0(SelectAlias.valueOf("DISTINCT PARTY_ID partyId",true))
                                .eq(CompareAlias.valueOf("e.personId"),req.getPersonId())
                )
        );
        if(!partyIds.isEmpty()){
            return respOrgInfoMapper.selectViewList(
                    WrapperFactory.multiQueryWrapper(TbMdInstitution.class)
                            .select("e.partyId,e.partyShortName,e.partyFullName,e.profile")
                            .select0(SelectAliasUtil.getFavOfOrg(req.getLoginUserId()))
                            .in(CompareAlias.valueOf(TsMdInstitution.Fields.partyId,"e"), partyIds)
            );
        }
        return Collections.emptyList();
    }

    @Override
    public CommonEnumContainer.OrgType getOrgType(Long partyId) {
        return generalMapper.count(WrapperFactory.multiQueryWrapper(TbPfundInstInfo.class)
                .eq(CompareAlias.valueOf("e.partyId"),partyId)
        ) > 0 ? CommonEnumContainer.OrgType.PRIVATE_PLACEMENT: CommonEnumContainer.OrgType.PUBLIC_PLACEMENT;
    }

}
