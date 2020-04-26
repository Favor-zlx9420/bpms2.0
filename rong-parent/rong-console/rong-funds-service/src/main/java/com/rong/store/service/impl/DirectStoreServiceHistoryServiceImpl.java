package com.rong.store.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.StringUtil;
import com.rong.member.module.entity.TbMemBase;
import com.rong.store.mapper.DirectStoreServiceHistoryMapper;
import com.rong.store.module.entity.TbDirectStoreServiceHistory;
import com.rong.store.module.request.TqDirectStoreServiceHistory;
import com.rong.store.module.view.TvDirectStoreServiceHistory;
import com.rong.store.service.DirectStoreServiceHistoryService;
import com.rong.sys.mapper.MessageMapper;
import com.rong.sys.module.entity.TbMessage;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DirectStoreServiceHistoryServiceImpl extends BasicServiceImpl<TbDirectStoreServiceHistory, TqDirectStoreServiceHistory, TvDirectStoreServiceHistory, DirectStoreServiceHistoryMapper> implements DirectStoreServiceHistoryService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select0(
                //合格投资者
                SelectAlias.valueOf("(CASE WHEN (select id from tb_investor_qualified where user_id=e.investor_user_id and state=1) IS NOT NULL THEN 1 ELSE 0 END) as userType",true)
                )
                .select("cm.userName customerUserName,cm.realName customerUserRealName,im.userName investorUserName,im.realName investorUserRealName,im.phone investorUserPhone")
                .select0(
                        SelectAlias.valueOf("(select content from tb_comm_message where relation_id=e.id and type="+ CommonEnumContainer.MessageType.CUSTOMER_SERVICE_RECORD.ordinal() +" order by id desc limit 1) replyMessage",true)

                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"cm"),cm->
                        cm.eqc(CompareAlias.valueOf("cm.id"),CompareAlias.valueOf("e.customerUserId"))
                        )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"im"),im->
                        im.eqc(CompareAlias.valueOf("im.id"),CompareAlias.valueOf("e.investorUserId"))
                )
                ;
    }


    @Override
    protected void afterUpdate(TqDirectStoreServiceHistory req) {
        super.afterUpdate(req);
        if(StringUtil.isEmpty(req.getReplyMessage())){
            req.setReplyMessage("无");
        }
        TvDirectStoreServiceHistory comment = mapper.selectOneView(getMultiCommonWrapper().eq(CompareAlias.valueOf("e.id"),req.getEntity().getId()));
        TbMessage message = new TbMessage();
        message.setTitle("客服服务记录审核结果通知");
        message.setContent(req.getReplyMessage());
        message.setType(CommonEnumContainer.MessageType.CUSTOMER_SERVICE_RECORD.ordinal());
        message.setViewState(CommonEnumContainer.MessageViewState.DID_NOT_SEE.ordinal());
        message.setContactor(comment.getInvestorUserRealName());
        message.setPhone(comment.getInvestorUserPhone());
        message.setState(CommonEnumContainer.State.NORMAL.getValue());
        message.setDeltag(false);
        message.setCreateDate(new Date());
        message.setMemberId(comment.getInvestorUserId());
        message.setRelationId(comment.getId());
        messageMapper.insert(message);
    }

}