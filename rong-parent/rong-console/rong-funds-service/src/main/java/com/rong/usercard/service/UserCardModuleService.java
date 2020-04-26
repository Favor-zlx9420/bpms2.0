package com.rong.usercard.service;

import com.rong.common.service.BasicService;
import com.rong.usercard.module.entity.TbUserCardModule;
import com.rong.usercard.module.request.TqUserCardModule;
import com.rong.usercard.module.view.TvUserCardModule;

import java.util.List;

public interface UserCardModuleService extends BasicService<TbUserCardModule, TqUserCardModule, TvUserCardModule> {
    void initUserModule(Long userId);
    List<TvUserCardModule> listUserCardModules(Long userId);
}