package com.rong.common.util;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;

public abstract class MissResultFactory {
    public static Result noExistsResult(String message){
        return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,message);
    }
}
