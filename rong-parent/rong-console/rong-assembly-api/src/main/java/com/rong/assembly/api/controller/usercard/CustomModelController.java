package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.rong.assembly.api.module.request.usercard.TqSimpleRemoveList;
import com.rong.assembly.api.module.request.usercard.TqUpdateCustomModel;
import com.rong.common.module.Result;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardCustomModel;
import com.rong.usercard.module.query.TsUserCardCustomModel;
import com.rong.usercard.module.query.TsUserCardEducationExperience;
import com.rong.usercard.module.request.TqUserCardCustomModel;
import com.rong.usercard.module.view.TvUserCardCustomModel;
import com.rong.usercard.service.UserCardCustomModelService;
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
 * 自定义模块
 */
@Api(tags = "自定义模块")
@RestController
@RequestMapping("usercard/customModel")
public class CustomModelController {
    @Autowired
    private UserCardCustomModelService userCardCustomModelService;

    /**
     * 自定义模块列表
     * @param req
     * @return
     */
    @ApiOperation(value = "自定义模块列表")
    @GetMapping("list")
    public Result<PageList<TvUserCardCustomModel>> list(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        return Result.success(userCardCustomModelService.selectPageListV(queryWrapper));
    }

    /**
     * 批量更新自定义模块
     * @param req
     * @return
     */
    @ApiOperation(value = "批量更新自定义模块")
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody TqUpdateCustomModel req){
        for(TqUpdateCustomModel.CustomModel customModel:req.getCustomModels()){
            if (customModel.getId() != null) {
                userCardCustomModelService.updateSelectItem(
                        WrapperFactory.updateWrapper()
                                .update(
                                        Elements.valueOf(TsUserCardCustomModel.Fields.modelTitle,customModel.getModelTitle())
                                        ,
                                        Elements.valueOf(TsUserCardCustomModel.Fields.modelContent,customModel.getModelContent())
                                        ,
                                        Elements.valueOf(TsUserCardCustomModel.Fields.remark,customModel.getRemark())
                                        ,
                                        Elements.valueOf(TsUserCardEducationExperience.Fields.updateDate,new Date())
                                )
                                .eq(TsUserCardCustomModel.Fields.id,customModel.getId())
                );
            } else {
                TbUserCardCustomModel tbUserCardCustomModel = new TbUserCardCustomModel()
                        .setUserId(req.getUserId())
                        .setModelTitle(customModel.getModelTitle())
                        .setModelContent(customModel.getModelContent())
                        .setRemark(customModel.getRemark());
                userCardCustomModelService.insert(new TqUserCardCustomModel().setEntity(tbUserCardCustomModel));
            }

        }
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除自定义模块
     * @param req
     * @return
     */
    @ApiOperation(value = "删除自定义模块")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody TqSimpleRemoveList req){
        userCardCustomModelService.updateSelectItem(
                WrapperFactory.updateWrapper()
                        .update(
                                Elements.valueOf(TsUserCardCustomModel.Fields.deltag,true)
                                ,
                                Elements.valueOf(TsUserCardCustomModel.Fields.updateDate,new Date())
                        )
                        .in(TsUserCardCustomModel.Fields.id,req.getIds())
        );
        return Result.success(Boolean.TRUE);
    }
}
