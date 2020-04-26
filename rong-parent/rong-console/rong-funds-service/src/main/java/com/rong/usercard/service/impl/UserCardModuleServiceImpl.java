package com.rong.usercard.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.mapper.UserCardAlbumMapper;
import com.rong.usercard.mapper.UserCardEducationExperienceMapper;
import com.rong.usercard.mapper.UserCardModuleMapper;
import com.rong.usercard.mapper.UserCardWorkExperienceMapper;
import com.rong.usercard.module.entity.TbUserCardModule;
import com.rong.usercard.module.query.TsUserCardModule;
import com.rong.usercard.module.query.TsUserCardWorkExperience;
import com.rong.usercard.module.request.TqUserCardModule;
import com.rong.usercard.module.view.TvUserCardModule;
import com.rong.usercard.service.UserCardModuleService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserCardModuleServiceImpl extends BasicServiceImpl<TbUserCardModule, TqUserCardModule, TvUserCardModule, UserCardModuleMapper> implements UserCardModuleService {
    @Autowired
    private UserCardEducationExperienceMapper userCardEducationExperienceMapper;
    @Autowired
    private UserCardWorkExperienceMapper userCardWorkExperienceMapper;
    @Autowired
    private UserCardAlbumMapper userCardAlbumMapper;
    /**
     * 初始化用户模块：只有表里没有的时候才创建
     * @param userId
     */
    @Override
    public void initUserModule(Long userId) {
        if(mapper.selectOne(WrapperFactory.queryWrapper().eq(TsUserCardModule.Fields.userId,userId)) != null){
            return;
        }
        TbUserCardModule userCardModule = new TbUserCardModule()
                .setUserId(userId)
                .setCreateDate(new Date())
                .setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue())
                ;
        for(CommonEnumContainer.UserCardModuleType type: CommonEnumContainer.UserCardModuleType.values()){
            userCardModule
                    .setSort(BigDecimal.valueOf(type.getValue()))
                    .setType(type.getValue())
                    .setTitle(type.getDesc())
            ;
            if(type != CommonEnumContainer.UserCardModuleType.CUSTOM_MODULE) {
                mapper.insert(userCardModule);
            }
        }
    }

    @Override
    public List<TvUserCardModule> listUserCardModules(Long userId) {
        List<TvUserCardModule> cardModules = mapper.selectViewList(
                WrapperFactory.multiQueryWrapper().eq(CompareAlias.valueOf("e.userId"),userId).eq(CompareAlias.valueOf("e.deltag"),false)
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort")))
        );
        for(TvUserCardModule cardModule:cardModules){
            if(CommonUtil.isEqual(cardModule.getType(),CommonEnumContainer.UserCardModuleType.WORK_EXPERIENCE.getValue())){
                cardModule.setDetails(userCardWorkExperienceMapper
                        .selectList(WrapperFactory.queryWrapper()
                                .eq(TsUserCardWorkExperience.Fields.userId,userId)
                                .eq(TsUserCardWorkExperience.Fields.deltag,false)
                        )
                );
            }else if(CommonUtil.isEqual(cardModule.getType(),CommonEnumContainer.UserCardModuleType.EDUCATION_EXPERIENCE.getValue())){
                cardModule.setDetails(userCardEducationExperienceMapper
                        .selectList(WrapperFactory.queryWrapper()
                                .eq(TsUserCardWorkExperience.Fields.userId,userId)
                                .eq(TsUserCardWorkExperience.Fields.deltag,false)
                        )
                );
            }else if(CommonUtil.isEqual(cardModule.getType(),CommonEnumContainer.UserCardModuleType.MY_PHOTO_ALBUMS.getValue())){
                cardModule.setDetails(userCardAlbumMapper
                        .selectList(WrapperFactory.queryWrapper()
                                .eq(TsUserCardWorkExperience.Fields.userId,userId)
                                .eq(TsUserCardWorkExperience.Fields.deltag,false)
                        )
                );
            }
        }
        return cardModules;
    }
}