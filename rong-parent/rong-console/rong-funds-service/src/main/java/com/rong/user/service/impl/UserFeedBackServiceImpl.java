package com.rong.user.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.WrapperFactory;
import com.rong.member.module.entity.TbMemBase;
import com.rong.user.mapper.UserFeedBackMapper;
import com.rong.user.module.entity.TbUserFeedBack;
import com.rong.user.module.query.TsUserFeedBack;
import com.rong.user.module.request.TqUserFeedBack;
import com.rong.user.module.view.TvUserFeedBack;
import com.rong.user.service.UserFeedBackService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Service;

@Service
public class UserFeedBackServiceImpl extends BasicServiceImpl<TbUserFeedBack, TqUserFeedBack, TvUserFeedBack, UserFeedBackMapper> implements UserFeedBackService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mb.userName submitUserName,mb.realName submitUserRealName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),mb->
                        mb.eqc(CompareAlias.valueOf("mb.id"),CompareAlias.valueOf(TsUserFeedBack.Fields.submitUserId,"e"))
                )
                ;
    }

    @Override
    protected void beforeInsert(TqUserFeedBack req) {
        TbUserFeedBack entity = req.getEntity();
        if(mapper.selectCount(WrapperFactory.queryWrapper()
                .eq(TsUserFeedBack.Fields.submitUserId,entity.getSubmitUserId())
                .eq(TsUserFeedBack.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                .eq(TsUserFeedBack.Fields.result, CommonEnumContainer.ReservationDealStatus.UNTREATED.getValue())
        ) > 4){
            throw new DuplicateDataException("已有5条未处理反馈，感谢您的反馈");
        }
        super.beforeInsert(req);
    }

}