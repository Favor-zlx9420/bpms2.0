package com.rong.store.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.store.mapper.DirectStoreUserMapper;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.query.TsDirectStoreUser;
import com.rong.store.module.request.TqDirectStoreUser;
import com.rong.store.module.view.TvDirectStoreUser;
import com.rong.store.service.DirectStoreUserService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Service;

@Service
public class DirectStoreUserServiceImpl extends BasicServiceImpl<TbDirectStoreUser, TqDirectStoreUser, TvDirectStoreUser, DirectStoreUserMapper> implements DirectStoreUserService {


    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return getMultiCommonWrapper(TbDirectStoreUser.class);
    }
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper(Class<?> tbClass) {
        return super.getMultiCommonWrapper(tbClass)
                .selectAllFiels(true)
                .select("m.realName realName,m.lastLoginDate,m.type userType")
                .select0(
                        SelectAlias.valueOf("(select count(1)from tb_direct_store_service_history where customer_user_id=e.user_id and audit_result in(0,1)) serviceNumCount",true)
                        ,
                        SelectAlias.valueOf("(select count(DISTINCT investor_user_id) from tb_direct_store_service_history where customer_user_id=e.user_id and audit_result in(0,1)) serviceUserCount",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"m"),
                                CompareAlias.valueOf(TsDirectStoreUser.Fields.userId, ConstValue.MAIN_ALIAS)))
                ;
    }
}