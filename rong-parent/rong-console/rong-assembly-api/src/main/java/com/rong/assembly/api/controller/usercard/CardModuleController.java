package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.*;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.Assert;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardModule;
import com.rong.usercard.module.query.TsUserCardModule;
import com.rong.usercard.module.request.TqUserCardModule;
import com.rong.usercard.module.view.TvUserCardModule;
import com.rong.usercard.service.UserCardModuleService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageList;
import com.vitily.mybatis.core.wrapper.delete.DeleteWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 名片自定义模块
 */
@Api(tags = "名片自定义模块，排序、删除为主")
@RestController
@RequestMapping("usercard/module")
public class CardModuleController {
    @Autowired
    private UserCardModuleService userCardModuleService;

    /**
     * 我的模块列表
     * @param req
     * @return
     */
    @ApiOperation(value = "我的模块列表")
    @GetMapping("list")
    public Result<PageList<TvUserCardModule>> list(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort"))
        );
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        return Result.success(userCardModuleService.selectPageListV(queryWrapper));
    }

    /**
     * 模块详情
     * @param req
     * @return
     */
    @ApiOperation(value = "模块详情")
    @GetMapping("detail")
    public Result<TvUserCardModule> detail(TqCardModuleDetail req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.id"),req.getId())
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId()
                        );
        return Result.success(userCardModuleService.selectOneView(queryWrapper));
    }

    /**
     * 添加模块
     * @param req
     * @return
     */
    @ApiOperation(value = "添加模块")
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody TqAddCardModule req){
        TbUserCardModule userCardModule = new TbUserCardModule();
        userCardModule.setTitle(req.getTitle());
        userCardModule.setContent(req.getContent());
        userCardModule.setType(CommonEnumContainer.UserCardModuleType.CUSTOM_MODULE.getValue());
        TbUserCardModule maxSort = userCardModuleService.selectOne(WrapperFactory.queryWrapper()
            .eq(TsUserCardModule.Fields.userId,req.getUserId())
                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("sort")))
        );
        userCardModule.setSort(maxSort.getSort().add(BigDecimal.ONE));
        userCardModule.setUserId(req.getUserId());
        TqUserCardModule tqUserCardModule = new TqUserCardModule();
        tqUserCardModule.setEntity(userCardModule);
        userCardModuleService.insert(tqUserCardModule);
        return Result.success(Boolean.TRUE);
    }

    /**
     * 更新模块
     * @param req
     * @return
     */
    @ApiOperation(value = "更新模块")
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody TqUpdateCardModule req){
        TbUserCardModule has = userCardModuleService.selectOne(
          WrapperFactory.queryWrapper()
                  .eq(TsUserCardModule.Fields.id,req.getId())
                  .eq(TsUserCardModule.Fields.userId,req.getUserId())
        );
        Assert.notNull(has,"模块不存在");
        TbUserCardModule up = new TbUserCardModule();
        BeanUtils.copyProperties(req,up);
        userCardModuleService.updateByPrimary(new TqUserCardModule().setEntity(up));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除模块(假删除)
     * @param req
     * @return
     */
    @ApiOperation(value = "删除模块(假删除)")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody TqSimpleRemoveList req){
        removeOrRecycle(req,true);
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除模块(真删除)
     * @param req
     * @return
     */
    @ApiOperation(value = "删除模块(真删除)只允许删除自定义模块")
    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody TqSimpleRemoveList req){
        userCardModuleService.delete(
          new DeleteWrapper()
            .in(TsUserCardModule.Fields.id,req.getIds())
            .eq(TsUserCardModule.Fields.userId,req.getUserId())
            .eq(TsUserCardModule.Fields.type, CommonEnumContainer.UserCardModuleType.CUSTOM_MODULE)
        );
        removeOrRecycle(req,true);
        return Result.success(Boolean.TRUE);
    }

    /**
     * 恢复删除
     * @param req
     * @return
     */
    @ApiOperation(value = "恢复删除")
    @PostMapping("recycle")
    public Result<Boolean> recycle(@RequestBody TqSimpleRemoveList req){
        removeOrRecycle(req,false);
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除或者恢复
     * @param req
     */
    public void removeOrRecycle(TqSimpleRemoveList req,boolean result){
        userCardModuleService.updateSelectItem(
                WrapperFactory.updateWrapper()
                        .update(
                                Elements.valueOf(TsUserCardModule.Fields.deltag,result)
                                ,
                                Elements.valueOf(TsUserCardModule.Fields.updateDate,new Date())
                        )
                        .in(TsUserCardModule.Fields.id,req.getIds())
                        .eq(TsUserCardModule.Fields.userId,req.getUserId())
        );
    }

}
