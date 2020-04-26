package com.rong.sys.service.impl;

import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.util.StringUtil;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbDictionary;
import com.rong.sys.module.query.TsDictionary;
import com.rong.sys.module.request.TqDictionary;
import com.rong.sys.module.view.TvDictionary;
import com.rong.sys.mapper.DictionaryMapper;
import com.rong.sys.service.DictionaryService;
import com.vitily.mybatis.util.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class DictionaryServiceImpl extends BasicServiceImpl<TbDictionary, TqDictionary, TvDictionary, DictionaryMapper> implements DictionaryService {
    private final DictionaryCache dictionaryCache;

    @Autowired()
    public DictionaryServiceImpl(DictionaryCache dictionaryCache) {
        this.dictionaryCache = dictionaryCache;
    }
    @Override
    @Transactional
    public int updateSelectiveByPrimaryKey(TbDictionary entity){
        int c = mapper.updateSelectiveByPrimaryKey(entity);
        if(c > 0){
            com.rong.cache.foreign.TbDictionary dictionary = new com.rong.cache.foreign.TbDictionary();
            BeanUtils.copyProperties(entity,dictionary);
            dictionaryCache.setDictionary(dictionary);
        }
        return c;
    }
    @Override
    @Transactional
    public Integer updateByBeans(TbDictionary[] dictionaries){
        Integer total = 0;
        Date now = new Date();
        for(TbDictionary entity:dictionaries){
            if(StringUtil.isEmpty(entity.getKey())){
                continue;
            }
            entity.setUpdateDate(now);
            entity.setState(CommonEnumContainer.State.NORMAL.getValue());
            mapper.updateSelectItem(
                    new UpdateWrapper()
                    .update(
                            Elements.valueOf(TsDictionary.Fields.updateDate,now),
                            Elements.valueOf(TsDictionary.Fields.state,entity.getState()),
                            Elements.valueOf(TsDictionary.Fields.value,entity.getValue())
                    )
                    .eq(TsDictionary.Fields.key,entity.getKey())
            );
            com.rong.cache.foreign.TbDictionary dictionary = new com.rong.cache.foreign.TbDictionary();
            BeanUtils.copyProperties(entity,dictionary);
            dictionaryCache.setDictionary(dictionary);
            ++total;
        }
        return total;
    }
}