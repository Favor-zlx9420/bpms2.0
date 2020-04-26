package com.rong.cms.service.impl;

import com.rong.cms.module.entity.TbCmsCategory;
import com.rong.cms.module.entity.TbCmsType;
import com.rong.cms.module.query.TsCmsCategory;
import com.rong.cms.module.query.TsCmsType;
import com.rong.cms.module.request.TqCmsCategory;
import com.rong.cms.module.view.TvCmsCategory;
import com.rong.cms.mapper.CmsCategoryMapper;
import com.rong.cms.service.CmsCategoryService;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.stereotype.Component;

@Component
public class CmsCategoryServiceImpl extends BasicServiceImpl<TbCmsCategory, TqCmsCategory, TvCmsCategory, CmsCategoryMapper> implements CmsCategoryService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                .select("a.name typeName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbCmsType.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsCmsType.Fields.id,"a"),
                                CompareAlias.valueOf(TsCmsCategory.Fields.typeId,"e")))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }

    @Override
    protected final void beforeInsert(TqCmsCategory req){
        if(CommonUtil.isNull(req.getEntity().getParentId())){
            req.getEntity().setParentId(0L);
        }
    }
    @Override
    protected final void beforeUpdate(TqCmsCategory req){
        if(CommonUtil.isNull(req.getEntity().getParentId())){
            req.getEntity().setParentId(0L);
        }
    }
}