package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemBase;
import com.rong.user.mapper.MessageHistoryMapper;
import com.rong.user.module.entity.TbMessageHistory;
import com.rong.user.module.request.TqMessageHistory;
import com.rong.user.module.view.TvMessageHistory;
import com.rong.user.service.MessageHistoryService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Service;

@Service
public class MessageHistoryServiceImpl extends BasicServiceImpl<TbMessageHistory, TqMessageHistory, TvMessageHistory, MessageHistoryMapper> implements MessageHistoryService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mb.userName,mb.realName")
                .select0(
                        SelectAlias.valueOf("(select count(id)from tb_message_history where target=e.target and create_date >=CURRENT_DATE()) dayCount",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),mb->mb
                    .eqc(CompareAlias.valueOf("mb.phone"),CompareAlias.valueOf("e.target"))
                )
                ;
    }

}