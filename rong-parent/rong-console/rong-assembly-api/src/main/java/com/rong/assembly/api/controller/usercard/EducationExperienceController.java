package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.rong.assembly.api.module.request.usercard.TqSimpleRemoveList;
import com.rong.assembly.api.module.request.usercard.TqUpdateEducationExperience;
import com.rong.common.module.Result;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardEducationExperience;
import com.rong.usercard.module.query.TsUserCardEducationExperience;
import com.rong.usercard.module.request.TqUserCardEducationExperience;
import com.rong.usercard.module.view.TvUserCardEducationExperience;
import com.rong.usercard.service.UserCardEducationExperienceService;
import com.vitily.mybatis.core.wrapper.PageList;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 教育经历
 */
@Api(tags = "教育经历")
@RestController
@RequestMapping("usercard/educationExperience")
public class EducationExperienceController {
    @Autowired
    private UserCardEducationExperienceService userCardEducationExperienceService;

    /**
     * 教育经历列表
     * @param req
     * @return
     */
    @ApiOperation(value = "教育经历列表")
    @GetMapping("list")
    public Result<PageList<TvUserCardEducationExperience>> list(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        return Result.success(userCardEducationExperienceService.selectPageListV(queryWrapper));
    }

    /**
     * 批量更新教育经历
     * @param req
     * @return
     */
    @ApiOperation(value = "批量更新教育经历")
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody TqUpdateEducationExperience req){
        for(TqUpdateEducationExperience.EducationExperience educationExperience:req.getEducationExperience()){
            if (educationExperience.getId() != null) {
                userCardEducationExperienceService.updateSelectItem(
                        WrapperFactory.updateWrapper()
                                .update(
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.school,educationExperience.getSchool())
                                        ,
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.education,educationExperience.getEducation())
                                        ,
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.acceDate,educationExperience.getAcceDate())
                                        ,
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.dimiDate,educationExperience.getDimiDate())
                                        ,
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.remark,educationExperience.getRemark())
                                        ,
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.updateDate,new Date())
                                        ,
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.major,educationExperience.getMajor())
                                )
                                .eq(TsUserCardEducationExperience.Fields.id,educationExperience.getId())
                );
            } else {
                TbUserCardEducationExperience tbUserCardEducationExperience = new TbUserCardEducationExperience()
                        .setUserId(req.getUserId())
                        .setSchool(educationExperience.getSchool())
                        .setEducation(educationExperience.getEducation())
                        .setAcceDate(educationExperience.getAcceDate())
                        .setDimiDate(educationExperience.getDimiDate())
                        .setRemark(educationExperience.getRemark())
                        .setMajor(educationExperience.getMajor());
                userCardEducationExperienceService.insert(new TqUserCardEducationExperience().setEntity(tbUserCardEducationExperience));
            }

        }
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除工作经历
     * @param req
     * @return
     */
    @ApiOperation(value = "删除工作经历")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody TqSimpleRemoveList req){
        userCardEducationExperienceService.updateSelectItem(
                WrapperFactory.updateWrapper()
                        .update(
                                Elements.valueOf(TsUserCardEducationExperience.Fields.deltag,true)
                                ,
                                Elements.valueOf(TsUserCardEducationExperience.Fields.updateDate,new Date())
                        )
                        .in(TsUserCardEducationExperience.Fields.id,req.getIds())
        );
        return Result.success(Boolean.TRUE);
    }
}
