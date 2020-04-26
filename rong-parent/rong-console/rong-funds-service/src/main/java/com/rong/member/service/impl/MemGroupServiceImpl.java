package com.rong.member.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemGroup;
import com.rong.member.module.query.TsMemGroup;
import com.rong.member.module.request.TqMemGroup;
import com.rong.member.module.view.TvMemGroup;
import com.rong.member.mapper.MemGroupMapper;
import com.rong.member.service.MemGroupService;
import com.vitily.mybatis.core.entity.Element;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.Elements;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MemGroupServiceImpl extends BasicServiceImpl<TbMemGroup, TqMemGroup, TvMemGroup, MemGroupMapper> implements MemGroupService {

    @Override
    protected final void beforeInsert(TqMemGroup req){
        updateOtherByCheck(req.getEntity());
    }
    @Override
    protected final void beforeUpdate(TqMemGroup req){
        updateOtherByCheck(req.getEntity());
    }

    private void updateOtherByCheck(TbMemGroup entity){
        //如果是默认的则更新其他默认组为非默认组
        if(entity.getDefaulted() != null && entity.getDefaulted()){
            mapper.updateSelectItem(
                    new UpdateWrapper()
                    .update(
                            Elements.valueOf(TsMemGroup.Fields.updateDate,new Date()),
                            Elements.valueOf(TsMemGroup.Fields.defaulted,CommonEnumContainer.Default.DENY.getValue())
                    )
                    .eq(TsMemGroup.Fields.defaulted,CommonEnumContainer.Default.RIGHT.getValue())
                    .neq(TsMemGroup.Fields.id,entity.getId())
            );
        }
    }
}