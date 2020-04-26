package com.rong.assembly.api.controller.usercard;

import com.rong.common.module.Result;
import com.rong.common.module.TqBase;
import com.rong.usercard.module.query.TsUserCardWorkExperience;
import com.rong.usercard.service.UserCardWorkExperienceService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "产品业务")
@RestController
@RequestMapping("/workExperience")
public class UserCardWorkExperienceController {

    @Autowired
    private UserCardWorkExperienceService userCardWorkExperienceService;

    /**
     * 列表
     * @param req
     * @return
     */
    @ApiOperation(value = "列表")
    @GetMapping("list")
    public Result search(TqBase req){
        return Result.success(userCardWorkExperienceService.selectList(IdWrapper.valueOf(req.getLoginUserId(), TsUserCardWorkExperience.Fields.userId)));
    }


}
