package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.mapper.PeopleManageMapper;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import com.rong.fundmanage.module.query.TsPeopleManage;
import com.rong.fundmanage.module.request.TqPeopleManage;
import com.rong.fundmanage.module.view.TvPeopleManage;
import com.rong.fundmanage.service.PeopleManageService;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.query.TsMdPeople;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class PeopleManageServiceImpl extends BasicServiceImpl<TbPeopleManage, TqPeopleManage, TvPeopleManage, PeopleManageMapper> implements PeopleManageService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return getMultiCommonWrapper(TbPeopleManage.class);
    }
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper(Class<?> tbClass) {
        return WrapperFactory.multiQueryWrapper(tbClass)
                .selectAllFiels(true)
                .select("p.name realName,m.position position,m.nickName nickName,m.headPortrait headPortrait")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"m"),
                                CompareAlias.valueOf(TsPeopleManage.Fields.userId, ConstValue.MAIN_ALIAS)))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdPeople.class,"p"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMdPeople.Fields.personId,"p"),
                                CompareAlias.valueOf(TsPeopleManage.Fields.personId, ConstValue.MAIN_ALIAS)))
                ;
    }
}