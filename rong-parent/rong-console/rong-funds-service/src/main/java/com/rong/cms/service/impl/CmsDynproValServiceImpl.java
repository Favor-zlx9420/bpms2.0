package com.rong.cms.service.impl;

import com.rong.cms.module.entity.TbCmsDynProper;
import com.rong.cms.module.entity.TbCmsDynproVal;
import com.rong.cms.module.query.TsCmsDynProper;
import com.rong.cms.module.query.TsCmsDynproVal;
import com.rong.cms.module.request.TqCmsDynproVal;
import com.rong.cms.module.view.TvCmsDynproVal;
import com.rong.cms.mapper.CmsDynproValMapper;
import com.rong.cms.service.CmsDynproValService;
import com.rong.common.service.impl.BasicServiceImpl;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Component;

@Component
public class CmsDynproValServiceImpl extends BasicServiceImpl<TbCmsDynproVal, TqCmsDynproVal, TvCmsDynproVal, CmsDynproValMapper> implements CmsDynproValService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper(){

        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select0(
                        SelectAlias.valueOf("a.name proName"),
                        SelectAlias.valueOf("a.value proValue"),
                        SelectAlias.valueOf("a.htmlType htmlType")
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbCmsDynProper.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsCmsDynProper.Fields.id,"a"),
                                CompareAlias.valueOf(TsCmsDynproVal.Fields.properId,"e")))
                ;
    }
}