package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemBase;
import com.rong.user.mapper.UserVip1ApplyMapper;
import com.rong.user.module.entity.TbUserVip1Apply;
import com.rong.user.module.request.TqUserVip1Apply;
import com.rong.user.module.view.TvUserVip1Apply;
import com.rong.user.service.UserVip1ApplyService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Service;

@Service
public class UserVip1ApplyServiceImpl extends BasicServiceImpl<TbUserVip1Apply, TqUserVip1Apply, TvUserVip1Apply, UserVip1ApplyMapper> implements UserVip1ApplyService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mb.userName appUserName,mb.realName appRealName")
                .select0(
                        SelectAlias.valueOf("(select end_date from tb_user_vip_end where user_id=e.app_user_id and deltag = false limit 1) endDate",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),mb->
                        mb.eqc(CompareAlias.valueOf("mb.id"),CompareAlias.valueOf("e.appUserId"))
                        )
                ;
    }
}