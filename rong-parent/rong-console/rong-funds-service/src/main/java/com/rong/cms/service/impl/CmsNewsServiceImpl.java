package com.rong.cms.service.impl;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.query.TsAdmUser;
import com.rong.cms.mapper.CmsDynProperMapper;
import com.rong.cms.mapper.CmsDynproValMapper;
import com.rong.cms.mapper.CmsNewsMapper;
import com.rong.cms.module.entity.*;
import com.rong.cms.module.query.*;
import com.rong.cms.module.request.TqCmsNews;
import com.rong.cms.module.view.TvCmsNews;
import com.rong.cms.service.CmsNewsService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.EnumFieldColUtil;
import com.rong.common.util.GUIDGenerator;
import com.vitily.mybatis.core.wrapper.delete.DeleteWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class CmsNewsServiceImpl extends BasicServiceImpl<TbCmsNews, TqCmsNews, TvCmsNews, CmsNewsMapper> implements CmsNewsService {

    private final CmsDynproValMapper cmsDynproValMapper;
    private final CmsDynProperMapper cmsDynProperMapper;

    @Resource
    private CmsNewsMapper cmsNewsMapper;

    @Autowired
    public CmsNewsServiceImpl(CmsDynproValMapper cmsDynproValMapper, CmsDynProperMapper cmsDynProperMapper) {
        this.cmsDynproValMapper = cmsDynproValMapper;
        this.cmsDynProperMapper = cmsDynProperMapper;
    }

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(false)
                .select0(EnumFieldColUtil.getByEnums(TsCmsNews.Fields.values(),"e", TsCmsNews.Fields.content))
                .select("t.name typeName,c.name cateName,a.userName createName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbCmsType.class,"t"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsCmsType.Fields.id,"t"),
                                CompareAlias.valueOf(TsCmsNews.Fields.typeId)))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbCmsCategory.class,"c"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsCmsCategory.Fields.id,"c"),
                                CompareAlias.valueOf(TsCmsNews.Fields.cateId)))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbAdmUser.class,"a"),
                        x->x.eqc(
                                CompareAlias.valueOf(TsAdmUser.Fields.id,"a"),
                                CompareAlias.valueOf(TsCmsNews.Fields.createBy,"e")))
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper().select(
               "e.content content"
        ));
    }


    @Override
    protected void beforeInsert(TqCmsNews req) {
        super.beforeInsert(req);
        req.getEntity().setId(SnowflakeIdWorker.create().nextId());
    }

    @Override
    protected void afterInsert(TqCmsNews req){
        super.afterInsert(req);
        String guid= GUIDGenerator.getGUID();
        bitInsertCmsDynproVals(req.getEntity().getTypeId(),req.getEntity().getId(),guid,req.getDynList(),req.getEntity().getCreateDate());
    }
    @Override
    protected void afterUpdate(TqCmsNews req){
        if(req.isChangeDyn()){//先删除后新增
            cmsDynproValMapper.delete(
                    new DeleteWrapper()
                            .eq(TsCmsDynproVal.Fields.newsId,req.getEntity().getId())
            );
            if(CommonUtil.isNotNull(req.getDynList())) {
                String guid = GUIDGenerator.getGUID();
                bitInsertCmsDynproVals(req.getEntity().getTypeId(),req.getEntity().getId(), guid, req.getDynList(),req.getEntity().getUpdateDate());
            }
        }else{//直接更新
            if(CommonUtil.isNotNull(req.getDynList())) {
                for (TbCmsDynproVal item : req.getDynList()) {
                    item.setUpdateDate(new Date());
                    cmsDynproValMapper.updateSelectiveByPrimaryKey(item);
                }
            }
        }
    }

    public void bitInsertCmsDynproVals(Long typeId, Long newsId, String guid, List<TbCmsDynproVal> gens, Date dealDate){
        List<TbCmsDynProper> list = cmsDynProperMapper.selectList(
                new QueryWrapper()
                        .eq(TsCmsDynProper.Fields.typeId,typeId)
        );
        if(list.size() != gens.size()){
            return;
        }

        for(int i = 0;i<gens.size();++i){
            TbCmsDynproVal item = gens.get(i);
            TbCmsDynProper e = list.get(i);
            item.setId(null);
            item.setCreateDate(dealDate);
            item.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
            item.setProperId(e.getId());
            item.setProperName(e.getName());
            item.setNewsId(newsId);
            cmsDynproValMapper.insertSelective(item);
        }
    }

    @Override
    public List<TbCmsNews> selectCmsNewsInfoList() {
        return cmsNewsMapper.selectCmsNewsInfoList();
    }
}