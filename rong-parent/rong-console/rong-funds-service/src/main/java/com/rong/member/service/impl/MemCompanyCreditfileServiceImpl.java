package com.rong.member.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbMemCompanyCreditfile;
import com.rong.member.module.entity.TbMemCreditHistory;
import com.rong.member.module.entity.TbMemCreditfileCertifyLog;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.query.TsMemCompanyCreditfile;
import com.rong.member.module.request.TqMemCompanyCreditfile;
import com.rong.member.module.view.TvMemCompanyCreditfile;
import com.rong.member.mapper.MemBaseMapper;
import com.rong.member.mapper.MemCompanyCreditfileMapper;
import com.rong.member.mapper.MemCreditHistoryMapper;
import com.rong.member.mapper.MemCreditfileCertifyLogMapper;
import com.rong.member.service.MemCompanyCreditfileService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class MemCompanyCreditfileServiceImpl extends BasicServiceImpl<TbMemCompanyCreditfile, TqMemCompanyCreditfile, TvMemCompanyCreditfile, MemCompanyCreditfileMapper> implements MemCompanyCreditfileService {

    private final MemBaseMapper memBaseMapper;
    private final MemCreditHistoryMapper memCreditHistoryMapper;
    private final MemCreditfileCertifyLogMapper memCreditfileCertifyLogMapper;

    @Autowired()
    public MemCompanyCreditfileServiceImpl(MemBaseMapper memBaseMapper
            ,MemCreditHistoryMapper memCreditHistoryMapper
            ,MemCreditfileCertifyLogMapper memCreditfileCertifyLogMapper
    ) {
        this.memBaseMapper = memBaseMapper;
        this.memCreditHistoryMapper = memCreditHistoryMapper;
        this.memCreditfileCertifyLogMapper = memCreditfileCertifyLogMapper;
    }

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("a.user_Name userName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"a"),
                                CompareAlias.valueOf(TsMemCompanyCreditfile.Fields.id, ConstValue.MAIN_ALIAS)))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }


    @Override
    protected final void beforeInsert(TqMemCompanyCreditfile req){
        req.getEntity().setCreditPoints(0);
    }
    @Override
    protected final void afterInsert(TqMemCompanyCreditfile req){
        TbMemBase upMemBase = new TbMemBase();
        upMemBase.setOpenCreditfile(CommonEnumContainer.YesOrNo.RIGHT.getValue());
        upMemBase.setId(req.getEntity().getId());
        upMemBase.setUpdateDate(req.getEntity().getCreateDate());
        memBaseMapper.updateSelectiveByPrimaryKey(upMemBase);
    }
    /**
     * 审核 个人认证资料
     * @param req
     */
    @Override
    @Transactional
    public void updateForAuditCreditfile(TqMemCompanyCreditfile req){
        //先查出历史数据再更新
        TbMemCompanyCreditfile updateEntity = req.getEntity();
        TbMemCreditHistory history = req.getCreditHistory();
        TbMemCreditfileCertifyLog log = new TbMemCreditfileCertifyLog();
        TbMemCompanyCreditfile old = mapper.selectItemByPrimaryKey(IdWrapper.valueOf(updateEntity.getId()));
        boolean auditOk = CommonUtil.isEqual(MemEnumContainer.CreditfileCertifyState.验证通过.getValue(),req.getAuditResult());
        if(CommonUtil.isNotNull(history.getCreditValue()) && auditOk){
            updateEntity.setCreditPoints(old.getCreditPoints()+history.getCreditValue());
        }else{
            updateEntity.setCreditPoints(null);
        }
        Date now = new Date();
        updateEntity.setUpdateDate(now);
        mapper.updateSelectiveByPrimaryKey(updateEntity);
        if(auditOk) {
            //插入记录
            history.setBalance(updateEntity.getCreditPoints());
            history.setCreateDate(now);
            memCreditHistoryMapper.insertSelective(history);
        }

        //插入日志
        log.setCreateDate(now);
        log.setMemo("审核信用资料,备注："+history.getMemo());
        log.setAdmUserId(history.getAdmUserId());
        log.setAdmUserName(history.getAdmUserName());
        log.setRelationLinks(history.getRelationLinks());
        log.setCreditfileType(history.getRecordType());
        log.setCreditfileState(req.getAuditResult());
        log.setMemberId(history.getMemberId());
        log.setDeltag(history.getDeltag());
        log.setIp(req.getAuditIp());
        memCreditfileCertifyLogMapper.insertSelective(log);
    }


}