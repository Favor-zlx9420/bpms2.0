package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class TqCardTalkList extends TqPageListBase {
    @ApiParam("说说的用户id")
    @RequireValidator
    private Long talkUserId;
}
