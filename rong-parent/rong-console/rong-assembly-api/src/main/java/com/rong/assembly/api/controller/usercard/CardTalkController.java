package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqAddUserCardTalk;
import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.rong.assembly.api.module.request.usercard.TqSimpleRemoveList;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.module.Result;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardTalk;
import com.rong.usercard.module.query.TsUserCardTalk;
import com.rong.usercard.module.request.TqUserCardTalk;
import com.rong.usercard.module.view.TvUserCardTalk;
import com.rong.usercard.service.UserCardTalkService;
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

import java.util.Date;

/**
 * 名片说说（我的观点）
 */
@Api(tags = "名片说说（我的观点）")
@RestController
@RequestMapping("usercard/talk")
public class CardTalkController {
    @Autowired
    private UserCardTalkService userCardTalkService;

    /**
     * 我的说说记录
     * @param req
     * @return
     */
    @ApiOperation(value = "我的说说记录")
    @GetMapping("list")
    public Result<PageList<TvUserCardTalk>> list(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .eq(CompareAlias.valueOf("e.deltag"),req.getDeltag())
                        .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                        .page(req.getPageInfo())
                ;
        if(StringUtil.isNotEmpty(req.getKeyword())){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("e.content"),req.getKeyword())
            );
        }
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        return Result.success(userCardTalkService.selectPageListV(queryWrapper));
    }
    /**
     * 添加说说
     * @param req
     * @return
     */
    @ApiOperation(value = "添加说说")
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody TqAddUserCardTalk req){
        if(userCardTalkService.selectCount(
                WrapperFactory.queryWrapper()
                    .eq(TsUserCardTalk.Fields.userId,req.getUserId())
                    .ge(TsUserCardTalk.Fields.createDate, DateUtil.getCurDateTime(DateUtil.yyyy_MM_dd_EN))
        ) > 10){
            throw new DuplicateDataException("每天只能发10条说说");
        }
        TbUserCardTalk cardTalk = new TbUserCardTalk()
                .setUserId(req.getUserId())
                .setContent(req.getContent())
                ;
        userCardTalkService.insert(new TqUserCardTalk().setEntity(cardTalk));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除说说
     * @param req
     * @return
     */
    @ApiOperation(value = "删除说说")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody TqSimpleRemoveList req){

        userCardTalkService.updateSelectItem(
                WrapperFactory.updateWrapper()
                .update(
                        Elements.valueOf(TsUserCardTalk.Fields.deltag,true)
                        ,
                        Elements.valueOf(TsUserCardTalk.Fields.updateDate,new Date())
                )
                .in(TsUserCardTalk.Fields.id,req.getIds())
                .eq(TsUserCardTalk.Fields.userId,req.getUserId())
        );
        return Result.success(Boolean.TRUE);
    }
}
