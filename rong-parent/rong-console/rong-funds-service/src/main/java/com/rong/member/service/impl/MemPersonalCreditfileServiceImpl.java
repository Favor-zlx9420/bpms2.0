package com.rong.member.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbMemCreditHistory;
import com.rong.member.module.entity.TbMemCreditfileCertifyLog;
import com.rong.member.module.entity.TbMemPersonalCreditfile;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.query.TsMemPersonalCreditfile;
import com.rong.member.module.request.TqMemPersonalCreditfile;
import com.rong.member.module.view.TvMemPersonalCreditfile;
import com.rong.member.mapper.MemBaseMapper;
import com.rong.member.mapper.MemCreditHistoryMapper;
import com.rong.member.mapper.MemCreditfileCertifyLogMapper;
import com.rong.member.mapper.MemPersonalCreditfileMapper;
import com.rong.member.service.MemPersonalCreditfileService;
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
public class MemPersonalCreditfileServiceImpl extends BasicServiceImpl<TbMemPersonalCreditfile, TqMemPersonalCreditfile, TvMemPersonalCreditfile, MemPersonalCreditfileMapper> implements MemPersonalCreditfileService {

    private final MemBaseMapper memBaseMapper;
    private final MemCreditHistoryMapper memCreditHistoryMapper;
    private final MemCreditfileCertifyLogMapper memCreditfileCertifyLogMapper;

    @Autowired()
    public MemPersonalCreditfileServiceImpl(MemBaseMapper memBaseMapper
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
                                CompareAlias.valueOf(TsMemPersonalCreditfile.Fields.id, ConstValue.MAIN_ALIAS)))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }


    @Override
    protected final void beforeInsert(TqMemPersonalCreditfile req){
        req.getEntity().setCreditPoints(0);
    }
    @Override
    protected final void afterInsert(TqMemPersonalCreditfile req){
        TbMemBase upMemBase = new TbMemBase();
        upMemBase.setOpenCreditfile(CommonEnumContainer.YesOrNo.RIGHT.getValue());
        upMemBase.setId(req.getEntity().getId());
        upMemBase.setUpdateDate(req.getEntity().getCreateDate());
        memBaseMapper.updateSelectiveByPrimaryKey(upMemBase);
    }
    /**
     * 审核 个人认证资料
     * 建议使用乐观锁，因为该处可控度高，并发度不高
     * @param req
     */
    @Override
    @Transactional
    public void updateForAuditCreditfile(TqMemPersonalCreditfile req){
        //先查出历史数据再更新
        TbMemPersonalCreditfile updateEntity = req.getEntity();
        TbMemCreditHistory history = req.getCreditHistory();
        TbMemCreditfileCertifyLog log = new TbMemCreditfileCertifyLog();
        TbMemPersonalCreditfile old = mapper.selectItemByPrimaryKey(IdWrapper.valueOf(updateEntity.getId()));
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