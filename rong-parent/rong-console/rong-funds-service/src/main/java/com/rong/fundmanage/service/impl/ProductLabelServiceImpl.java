package com.rong.fundmanage.service.impl;

import com.rong.common.exception.DuplicateDataException;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.mapper.ProductLabelMapper;
import com.rong.fundmanage.module.entity.TbProductLabel;
import com.rong.fundmanage.module.query.TsProductLabel;
import com.rong.fundmanage.module.request.TqProductLabel;
import com.rong.fundmanage.module.view.TvProductLabel;
import com.rong.fundmanage.service.ProductLabelService;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.query.TsMdSecurity;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductLabelServiceImpl extends BasicServiceImpl<TbProductLabel, TqProductLabel, TvProductLabel, ProductLabelMapper> implements ProductLabelService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .select("ms.secShortName,ms.secFullName")
                .selectAllFiels(true)
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"ms"),ms->
                        ms.eqc(CompareAlias.valueOf(TsMdSecurity.Fields.securityId,"ms"),
                                CompareAlias.valueOf(TsProductLabel.Fields.securityId,"e")
                        )
                        )
                ;
    }

    @Override
    protected void beforeInsert(TqProductLabel req) {
        TbProductLabel entity = req.getEntity();
        if(mapper.selectCount(
                WrapperFactory.queryWrapper()
                .eq(TsProductLabel.Fields.securityId,entity.getSecurityId())
                .eq(TsProductLabel.Fields.labelId,entity.getLabelId())
        ) > 0){
            throw new DuplicateDataException("已有该产品对应标签，请检查！");
        }
        super.beforeInsert(req);
    }
}