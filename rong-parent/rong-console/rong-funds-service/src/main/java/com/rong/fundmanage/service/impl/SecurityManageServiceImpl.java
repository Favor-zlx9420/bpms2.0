package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.mapper.ProductLabelMapper;
import com.rong.fundmanage.mapper.SecurityManageMapper;
import com.rong.fundmanage.module.entity.TbProductLabel;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.fundmanage.module.query.TsProductLabel;
import com.rong.fundmanage.module.request.TqSecurityManage;
import com.rong.fundmanage.module.view.TvSecurityManage;
import com.rong.fundmanage.service.SecurityManageService;
import com.vitily.mybatis.util.CollectionUtils;
import com.vitily.mybatis.util.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecurityManageServiceImpl extends BasicServiceImpl<TbSecurityManage, TqSecurityManage, TvSecurityManage, SecurityManageMapper> implements SecurityManageService {
    @Autowired
    private ProductLabelMapper productLabelMapper;


    @Override
    protected void afterInsert(TqSecurityManage req) {
        super.afterInsert(req);
        if(!CollectionUtils.isEmpty(req.getLabelIds())){
            TbProductLabel label = new TbProductLabel();
            label.setLabelReason(req.getLabelReason());
            label.setLabelVar0(req.getLabelVar0());
            label.setLabelVar1(req.getLabelVar1());
            label.setSecurityId(req.getEntity().getSecurityId());
            label.setVisible(true);
            label.setCreateDate(new Date());
            label.setDeltag(false);
            for(Long labelId : req.getLabelIds()){
                label.setLabelId(labelId);
                productLabelMapper.insert(label);
            }
        }
    }

    @Override
    protected void afterUpdate(TqSecurityManage req) {
        super.afterUpdate(req);
        if(null == req.getLabelIds()){
            return;
        }
        productLabelMapper.updateSelectItem(
                WrapperFactory.updateWrapper().update(
                        Elements.valueOf(TsProductLabel.Fields.deltag,true)
                        ,
                        Elements.valueOf(TsProductLabel.Fields.updateDate,new Date())
                )
                        .eq(TsProductLabel.Fields.securityId,req.getEntity().getSecurityId())
        );
        if(!CollectionUtils.isEmpty(req.getLabelIds())){
            TbProductLabel label = new TbProductLabel();
            label.setLabelReason(req.getLabelReason());
            label.setLabelVar0(req.getLabelVar0());
            label.setLabelVar1(req.getLabelVar1());
            label.setSecurityId(req.getEntity().getSecurityId());
            label.setVisible(true);
            label.setCreateDate(new Date());
            label.setDeltag(false);
            for(Long labelId : req.getLabelIds()){
                if(productLabelMapper.updateSelectItem(
                        WrapperFactory.updateWrapper().update(
                                Elements.valueOf(TsProductLabel.Fields.deltag,false)
                                ,
                                Elements.valueOf(TsProductLabel.Fields.labelVar0,label.getLabelVar0())
                                ,
                                Elements.valueOf(TsProductLabel.Fields.labelVar1,label.getLabelVar1())
                                ,
                                Elements.valueOf(TsProductLabel.Fields.labelReason,label.getLabelReason())
                                ,
                                Elements.valueOf(TsProductLabel.Fields.updateDate,new Date())
                        )
                                .eq(TsProductLabel.Fields.securityId,req.getEntity().getSecurityId())
                                .eq(TsProductLabel.Fields.labelId,labelId)
                ) == 0){
                    label.setLabelId(labelId);
                    productLabelMapper.insert(label);
                }
            }
        }
    }

}