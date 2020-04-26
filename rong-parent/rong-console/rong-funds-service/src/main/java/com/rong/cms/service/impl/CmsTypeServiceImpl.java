package com.rong.cms.service.impl;

import com.rong.cms.module.entity.TbCmsDynProper;
import com.rong.cms.module.entity.TbCmsType;
import com.rong.cms.module.query.TsCmsDynProper;
import com.rong.cms.module.request.TqCmsType;
import com.rong.cms.module.view.TvCmsType;
import com.rong.cms.mapper.CmsDynProperMapper;
import com.rong.cms.mapper.CmsTypeMapper;
import com.rong.cms.service.CmsTypeService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.util.CommonUtil;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.rong.common.service.impl.BasicServiceImpl;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CmsTypeServiceImpl extends BasicServiceImpl<TbCmsType, TqCmsType, TvCmsType, CmsTypeMapper> implements CmsTypeService {

    private final CmsDynProperMapper cmsDynProperMapper;

    @Autowired
    public CmsTypeServiceImpl(CmsDynProperMapper cmsDynProperMapper) {
        this.cmsDynProperMapper = cmsDynProperMapper;
    }

    @Override
    public TvCmsType selectViewByPrimaryKey(MultiTableIdWrapper wrapper) {
        TvCmsType view = super.selectViewByPrimaryKey(wrapper);
        if (CommonUtil.isNull(view)) {
            return null;
        }
        view.setDyns(cmsDynProperMapper.selectList(
                new QueryWrapper()
                        .eq(TsCmsDynProper.Fields.typeId, wrapper.getKeyValue())
                        .eq(TsCmsDynProper.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        ));
        return view;
    }
    @Override
    protected void beforeInsert(TqCmsType req) {
        super.beforeInsert(req);
        req.getEntity().setId(SnowflakeIdWorker.create().nextId());
    }

    @Override
    protected final void afterInsert(TqCmsType req){
        super.afterInsert(req);
        //添加参数
        if(CommonUtil.isNotNull(req.getDyns())){
            for(TbCmsDynProper item:req.getDyns()){
                item.setTypeId(req.getEntity().getId());
                item.setCreateDate(req.getEntity().getCreateDate());
                item.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
                cmsDynProperMapper.insertSelective(item);
            }
        }
    }
    @Override
    protected final void afterUpdate(TqCmsType req){
        super.afterUpdate(req);
        Date now = req.getEntity().getUpdateDate();
        //有则更新无则增加
        if(CommonUtil.isNotNull(req.getDyns())){
            for(TbCmsDynProper item:req.getDyns()){
                item.setTypeId(req.getEntity().getId());
                item.setUpdateDate(now);
                if(item.getId() > 0){
                    cmsDynProperMapper.updateSelectiveByPrimaryKey(item);
                }else{
                    item.setCreateDate(now);
                    item.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
                    cmsDynProperMapper.insertSelective(item);
                }
            }
        }
        //删除的 delDynIds
        if(CommonUtil.isNotEmpty(req.getDelDynIds())){
            cmsDynProperMapper.updateSelectItem(
                    new UpdateWrapper()
                            .update(
                                    Elements.valueOf(TsCmsDynProper.Fields.updateDate,now),
                                    Elements.valueOf(TsCmsDynProper.Fields.deltag,CommonEnumContainer.Deltag.DELETED.getValue())
                            )
                            .in(TsCmsDynProper.Fields.id,req.getDelDynIds())
            );
        }
    }
}