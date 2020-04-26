package com.rong.assembly.api.service;

import com.rong.common.consts.CommonEnumContainer;

public interface UserCardSwapService {
    CommonEnumContainer.UserCardSwapState getBothSidesSwapState(Long myUserId, Long theUserId);
}
