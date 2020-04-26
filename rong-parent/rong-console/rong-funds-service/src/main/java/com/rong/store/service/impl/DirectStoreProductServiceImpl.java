package com.rong.store.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.mapper.ProductLabelMapper;
import com.rong.fundmanage.module.entity.TbProductLabel;
import com.rong.fundmanage.module.query.TsProductLabel;
import com.rong.store.mapper.DirectStoreProductMapper;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import com.rong.store.module.entity.TbDirectStoreProduct;
import com.rong.store.module.query.TsDirectStoreOrgLabel;
import com.rong.store.module.query.TsDirectStoreProduct;
import com.rong.store.module.request.TqDirectStoreOrg;
import com.rong.store.module.request.TqDirectStoreProduct;
import com.rong.store.module.view.TvDirectStoreProduct;
import com.rong.store.service.DirectStoreProductService;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.query.TsMdSecurity;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CollectionUtils;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DirectStoreProductServiceImpl extends BasicServiceImpl<TbDirectStoreProduct, TqDirectStoreProduct, TvDirectStoreProduct, DirectStoreProductMapper> implements DirectStoreProductService {
    @Autowired
    private ProductLabelMapper productLabelMapper;
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("ms.secFullName,ms.secShortName")
                .leftJoin(
                        ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"ms"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsMdSecurity.Fields.securityId,"ms"),
                                CompareAlias.valueOf(TsDirectStoreProduct.Fields.securityId,"e")
                        )
                )
                ;
    }


    @Override
    protected void afterInsert(TqDirectStoreProduct req) {
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
    protected void afterUpdate(TqDirectStoreProduct req) {
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