package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqAddUserCardShareTitle;
import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.rong.assembly.api.module.request.usercard.TqSimpleRemoveList;
import com.rong.assembly.api.module.request.usercard.TqUpdateUserCardShareTitle;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardShareTitle;
import com.rong.usercard.module.query.TsUserCardShareTitle;
import com.rong.usercard.module.query.TsUserCardTalk;
import com.rong.usercard.module.request.TqUserCardShareTitle;
import com.rong.usercard.module.view.TvUserCardShareTitle;
import com.rong.usercard.service.UserCardShareTitleService;
import com.vitily.mybatis.core.entity.Element;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageList;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * 分享标题
 */
@Api(tags = "分享标题")
@RestController
@RequestMapping("usercard/shareTitle")
public class CardShareTitleController {
    @Autowired
    private UserCardShareTitleService userCardShareTitleService;

    /**
     * 我的分享标题
     * @param req
     * @return
     */
    @ApiOperation(value = "我的分享标题记录")
    @GetMapping("list")
    public Result<PageList<TvUserCardShareTitle>> list(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .eq(CompareAlias.valueOf("e.deltag"),req.getDeltag())
                        .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                        .page(req.getPageInfo()
        );
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        return Result.success(userCardShareTitleService.selectPageListV(queryWrapper));
    }

    /**
     * 添加分享标题
     * @param req
     * @return
     */
    @ApiOperation(value = "添加分享标题")
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody TqAddUserCardShareTitle req){
        if(userCardShareTitleService.selectCount(
                WrapperFactory.queryWrapper()
                    .eq(TsUserCardTalk.Fields.userId,req.getUserId())
        ) > 10){
            throw new DuplicateDataException("最多只允许有10条分享标题");
        }
        //修改其他标题为false
        userCardShareTitleService.updateSelectItem(
          WrapperFactory.updateWrapper()
                  .update(
                          Elements.valueOf(TsUserCardShareTitle.Fields.state, CommonEnumContainer.State.INVALID.getValue())
                          ,
                          Elements.valueOf(TsUserCardShareTitle.Fields.updateDate, new Date())
                  )
                .eq(TsUserCardShareTitle.Fields.userId,req.getUserId())

        );
        TbUserCardShareTitle shareTitle = new TbUserCardShareTitle()
                .setUserId(req.getUserId())
                .setTitle(req.getTitle())
                .setState(CommonEnumContainer.State.NORMAL.getValue())
                ;
        userCardShareTitleService.insert(new TqUserCardShareTitle().setEntity(shareTitle));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 修改分享标题
     * @param req
     * @return
     */
    @ApiOperation(value = "修改分享标题")
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody TqUpdateUserCardShareTitle req){
        //修改自己为true
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(Elements.valueOf(TsUserCardShareTitle.Fields.title, req.getTitle()));
        elements.add(Elements.valueOf(TsUserCardShareTitle.Fields.updateDate, new Date()));
        if(null != req.getState()){
            //假如设置为0，则把其他改成0
            //假如设置为0，判断其他是否有1
            if(CommonUtil.isEqual(req.getState(), CommonEnumContainer.State.NORMAL.getValue())){
                userCardShareTitleService.updateSelectItem(
                        WrapperFactory.updateWrapper()
                                .update(
                                        Elements.valueOf(TsUserCardShareTitle.Fields.updateDate, new Date()),
                                        Elements.valueOf(TsUserCardShareTitle.Fields.state, CommonEnumContainer.State.INVALID.getValue())
                                )
                                .eq(TsUserCardShareTitle.Fields.userId,req.getUserId())
                                .neq(TsUserCardShareTitle.Fields.id,req.getId())

                );
            }else{
                if(userCardShareTitleService.selectCount(WrapperFactory.queryWrapper()
                        .eq(TsUserCardShareTitle.Fields.userId,req.getUserId())
                        .eq(TsUserCardShareTitle.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                )== 0){
                    throw new NoExistsException("必须至少有一条分享标题！");
                }
            }
            elements.add(Elements.valueOf(TsUserCardShareTitle.Fields.state, req.getState()));
        }
        userCardShareTitleService.updateSelectItem(
                WrapperFactory.updateWrapper()
                        .update(elements)
                        .eq(TsUserCardShareTitle.Fields.id,req.getId())
                        .eq(TsUserCardShareTitle.Fields.userId,req.getUserId())

        );
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除分享标题
     * @param req
     * @return
     */
    @ApiOperation(value = "删除分享标题")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody TqSimpleRemoveList req){

        userCardShareTitleService.updateSelectItem(
                WrapperFactory.updateWrapper()
                .update(
                        Elements.valueOf(TsUserCardShareTitle.Fields.deltag,true)
                        ,
                        Elements.valueOf(TsUserCardShareTitle.Fields.updateDate,new Date())
                )
                .in(TsUserCardShareTitle.Fields.id,req.getIds())
                .eq(TsUserCardShareTitle.Fields.userId,req.getUserId())
        );
        return Result.success(Boolean.TRUE);
    }

}
