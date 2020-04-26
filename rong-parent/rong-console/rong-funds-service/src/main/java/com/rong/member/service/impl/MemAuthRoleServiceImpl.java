package com.rong.member.service.impl;

import com.rong.auth.module.entity.TbAuthRole;
import com.rong.auth.module.query.TsAuthRole;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemAuthRole;
import com.rong.member.module.query.TsMemAuthRole;
import com.rong.member.module.request.TqMemAuthRole;
import com.rong.member.module.view.TvMemAuthRole;
import com.rong.member.mapper.MemAuthRoleMapper;
import com.rong.member.service.MemAuthRoleService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Component;

@Component
public class MemAuthRoleServiceImpl extends BasicServiceImpl<TbMemAuthRole, TqMemAuthRole, TvMemAuthRole, MemAuthRoleMapper> implements MemAuthRoleService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .select0(
                        SelectAlias.valueOf(TsMemAuthRole.Fields.id, ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemAuthRole.Fields.userId, ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemAuthRole.Fields.roleId, ConstValue.MAIN_ALIAS)
                )
                .select("a.name roleName,a.symbol roleSymbol")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbAuthRole.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsAuthRole.Fields.id,"a"),
                                CompareAlias.valueOf(TsMemAuthRole.Fields.userId,"e")))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }
}