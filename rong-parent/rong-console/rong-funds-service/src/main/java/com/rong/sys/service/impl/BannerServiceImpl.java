package com.rong.sys.service.impl;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.query.TsAdmUser;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.sys.mapper.BannerMapper;
import com.rong.sys.module.entity.TbBanner;
import com.rong.sys.module.query.TsBanner;
import com.rong.sys.module.request.TqBanner;
import com.rong.sys.module.view.TvBanner;
import com.rong.sys.service.BannerService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BannerServiceImpl extends BasicServiceImpl<TbBanner, TqBanner, TvBanner, BannerMapper> implements BannerService {

    @Override
    public int insert(TqBanner req) {
        //
        if(CollectionUtils.isEmpty(req.getPics())){
            throw new CustomerException("banner图片必须至少传一张", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
        }
        TbBanner entity = req.getEntity();
        Date now = new Date();
        for(TbBanner item:req.getPics()){
            item.setPageType(entity.getPageType());
            item.setCreateDate(now);
            item.setUpdateDate(now);
            item.setState(entity.getState());
            item.setTitle(entity.getTitle());
            item.setDescription(entity.getDescription());
            item.setDeltag(false);
            item.setCreateBy(entity.getCreateBy());
            mapper.insertSelective(item);
        }
        return 1;
    }

    @Override
    public int updateByPrimary(TqBanner req) {
        //删除的 delDynIds
        Date now = new Date();
        if(CommonUtil.isNotEmpty(req.getDelPicIds())){
            mapper.updateSelectItem(
                    new UpdateWrapper()
                            .update(
                                    Elements.valueOf(TsBanner.Fields.updateDate,now),
                                    Elements.valueOf(TsBanner.Fields.deltag,CommonEnumContainer.Deltag.DELETED.getValue())
                            )
                            .in(TsBanner.Fields.id,req.getDelPicIds())
            );
        }
        TbBanner entity = req.getEntity();
        //有则更新无则增加
        if(CommonUtil.isNotNull(req.getPics())){
            for(TbBanner item:req.getPics()){
                item.setUpdateDate(now);
                item.setState(entity.getState());
                item.setPageType(entity.getPageType());
                item.setTitle(entity.getTitle());
                item.setDescription(entity.getDescription());
                if(item.getId() > 0){
                    mapper.updateSelectiveByPrimaryKey(item);
                }else{
                    item.setCreateDate(now);
                    item.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
                    mapper.insertSelective(item);
                }
            }
        }
        return 1;
    }
}