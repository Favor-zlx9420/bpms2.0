package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.rong.assembly.api.module.request.usercard.TqSimpleRemoveList;
import com.rong.assembly.api.module.request.usercard.TqUpdateWorkExperience;
import com.rong.common.module.Result;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardWorkExperience;
import com.rong.usercard.module.query.TsUserCardWorkExperience;
import com.rong.usercard.module.request.TqUserCardWorkExperience;
import com.rong.usercard.module.view.TvUserCardWorkExperience;
import com.rong.usercard.service.UserCardWorkExperienceService;
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
 * 工作经历
 */
@Api(tags = "工作经历")
@RestController
@RequestMapping("usercard/workExperience")
public class WorkExperienceController {
    @Autowired
    private UserCardWorkExperienceService userCardWorkExperienceService;

    /**
     * 工作经历列表
     * @param req
     * @return
     */
    @ApiOperation(value = "工作经历列表")
    @GetMapping("list")
    public Result<PageList<TvUserCardWorkExperience>> list(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        return Result.success(userCardWorkExperienceService.selectPageListV(queryWrapper));
    }
//    /**
//     * 添加工作经历
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "添加工作经历")
//    @PostMapping("add")
//    public Result<Boolean> add(@RequestBody TqUpdateWorkExperience req){
//        for (TqUpdateWorkExperience.WorkExperience workExperience : req.getWorkExperience()) {
//            TbUserCardWorkExperience tbUserCardWorkExperience = new TbUserCardWorkExperience()
//                    .setUserId(req.getUserId())
//                    .setCompany(workExperience.getCompany())
//                    .setPost(workExperience.getPost())
//                    .setAcceDate(workExperience.getAcceDate())
//                    .setDimiDate(workExperience.getDimiDate())
//                    .setRemark(workExperience.getRemark());
//            userCardWorkExperienceService.insert(new TqUserCardWorkExperience().setEntity(tbUserCardWorkExperience));
//        }
//        return Result.success(Boolean.TRUE);
//    }

    /**
     * 批量更新工作经历
     * @param req
     * @return
     */
    @ApiOperation(value = "批量更新工作经历")
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody TqUpdateWorkExperience req){
        for(TqUpdateWorkExperience.WorkExperience workExperience:req.getWorkExperience()){
            if (workExperience.getId() != null) {
                userCardWorkExperienceService.updateSelectItem(
                        WrapperFactory.updateWrapper()
                                .update(
                                        Elements.valueOf(TsUserCardWorkExperience.Fields.company,workExperience.getCompany())
                                        ,
                                        Elements.valueOf(TsUserCardWorkExperience.Fields.post,workExperience.getPost())
                                        ,
                                        Elements.valueOf(TsUserCardWorkExperience.Fields.acceDate,workExperience.getAcceDate())
                                        ,
                                        Elements.valueOf(TsUserCardWorkExperience.Fields.dimiDate,workExperience.getDimiDate())
                                        ,
                                        Elements.valueOf(TsUserCardWorkExperience.Fields.remark,workExperience.getRemark())
                                        ,
                                        Elements.valueOf(TsUserCardWorkExperience.Fields.updateDate,new Date())
                                )
                                .eq(TsUserCardWorkExperience.Fields.id,workExperience.getId())
                );
            } else {
                TbUserCardWorkExperience tbUserCardWorkExperience = new TbUserCardWorkExperience()
                        .setUserId(req.getUserId())
                        .setCompany(workExperience.getCompany())
                        .setPost(workExperience.getPost())
                        .setAcceDate(workExperience.getAcceDate())
                        .setDimiDate(workExperience.getDimiDate())
                        .setRemark(workExperience.getRemark());
                userCardWorkExperienceService.insert(new TqUserCardWorkExperience().setEntity(tbUserCardWorkExperience));
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
        userCardWorkExperienceService.updateSelectItem(
                WrapperFactory.updateWrapper()
                        .update(
                                Elements.valueOf(TsUserCardWorkExperience.Fields.deltag,true)
                                ,
                                Elements.valueOf(TsUserCardWorkExperience.Fields.updateDate,new Date())
                        )
                        .in(TsUserCardWorkExperience.Fields.id,req.getIds())
        );
        return Result.success(Boolean.TRUE);
    }
}
