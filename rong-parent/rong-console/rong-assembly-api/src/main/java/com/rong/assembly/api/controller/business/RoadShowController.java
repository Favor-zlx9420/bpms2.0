package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.module.request.buz.*;
import com.rong.assembly.api.module.response.TrRoadShowHome;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.Assert;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.roadshow.module.entity.TbRoadShowCategory;
import com.rong.roadshow.module.entity.TbUserRoadShowView;
import com.rong.roadshow.module.query.TsRoadShowInfo;
import com.rong.roadshow.module.query.TsUserRoadShowReservation;
import com.rong.roadshow.module.request.TqUserRoadShowView;
import com.rong.roadshow.module.view.TvRoadShowCategory;
import com.rong.roadshow.module.view.TvRoadShowHotOrg;
import com.rong.roadshow.module.view.TvRoadShowHotUser;
import com.rong.roadshow.module.view.TvRoadShowInfo;
import com.rong.roadshow.service.RoadShowCategoryService;
import com.rong.roadshow.service.RoadShowHotOrgService;
import com.rong.roadshow.service.RoadShowHotUserService;
import com.rong.roadshow.service.RoadShowInfoService;
import com.rong.roadshow.service.UserRoadShowViewService;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.view.TvLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Api(tags = "路演信息")
@RestController
@RequestMapping("roadShow/")
public class RoadShowController {
    @Autowired
    private RoadShowCategoryService roadShowCategoryService;
    @Autowired
    private RoadShowInfoService roadShowInfoService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private RoadShowHotUserService roadShowHotUserService;
    @Autowired
    private RoadShowHotOrgService roadShowHotOrgService;
    @Autowired
    private UserRoadShowViewService userRoadShowViewService;
    @Autowired
    private DictionaryCache dictionaryCache;

    /**
     * 路演分类列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "路演分类列表")
    @GetMapping("cates")
    public Result<List<TvRoadShowCategory>> cates(TqRoadShowCates req) {
        MultiTableQueryWrapper queryWrapper = roadShowCategoryService.getMultiCommonWrapper()
                .select("e.id,e.name")
                .eq(CompareAlias.valueOf("e.state"), CommonEnumContainer.State.NORMAL.getValue())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getDesc())
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort")))
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")));
        return Result.success(roadShowCategoryService.selectViewList(queryWrapper));
    }

    /**
     * 路演分类列表(排除30天路演分类)
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "路演分类列表(排除30天路演分类)")
    @GetMapping("catesExceptThirtyDayHot")
    public Result<List<TvRoadShowCategory>> catesExceptThirtyDayHot(TqRoadShowCates req) {
        Long thirtyDayHotCateId = Long.valueOf(dictionaryCache.getValue(DictionaryKey.Keys._30_DAYS_HOT_ROAD_SHOW_CATEGORY_ID.getValue()));
        MultiTableQueryWrapper queryWrapper = roadShowCategoryService.getMultiCommonWrapper()
                .select("e.id,e.name")
                .eq(CompareAlias.valueOf("e.state"), CommonEnumContainer.State.NORMAL.getValue())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getDesc())
                .neq(CompareAlias.valueOf("e.id"), thirtyDayHotCateId)
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort")))
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")));
        return Result.success(roadShowCategoryService.selectViewList(queryWrapper));
    }

    /**
     * 路演标签列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "路演标签列表")
    @GetMapping("labels")
    public Result<List<TvLabel>> labels(TqRoadShowLabels req) {
        MultiTableQueryWrapper queryWrapper = labelService.getMultiCommonWrapper()
                .select("e.id,e.name")
                .eq(CompareAlias.valueOf("e.type"), SysEnumContainer.LabelType.ROADSHOW_LABEL.getValue())
                .eq(CompareAlias.valueOf("e.state"), CommonEnumContainer.State.NORMAL.getValue())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getDesc())
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort")))
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")));
        if (req.getCateId() != null) {
            TbRoadShowCategory category = roadShowCategoryService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getCateId()));
            List<Long> labelIds = new ArrayList<>();
            if (category == null || StringUtil.isEmpty(category.getLabelIds())) {
                labelIds.add(0L);
            } else {
                labelIds = StringUtil.StringsToLongList(category.getLabelIds().split(","));
            }
            queryWrapper.in(CompareAlias.valueOf("e.id"), labelIds);
        }
        return Result.success(labelService.selectViewList(queryWrapper));
    }

    /**
     * 热门机构
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "热门机构")
    @GetMapping("hotOrgs")
    public Result<List<TvRoadShowHotOrg>> hotOrgs(TqRoadShowHotOrg req) {
        if (null == req.getPageInfo()) {
            req.setPageInfo(new PageInfo());
        }

        MultiTableQueryWrapper queryWrapper = roadShowHotOrgService.getMultiCommonWrapper()
                .eq(CompareAlias.valueOf("e.visible"), CommonEnumContainer.YesOrNo.RIGHT.getValue())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getDesc())
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort")))
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                .page(req.getPageInfo());
        return Result.success(roadShowHotOrgService.selectViewList(queryWrapper));
    }

    /**
     * 热门嘉宾
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "热门嘉宾")
    @GetMapping("hotUsers")
    public Result<List<TvRoadShowHotUser>> hotUsers(TqRoadShowHotUser req) {
        if (null == req.getPageInfo()) {
            req.setPageInfo(new PageInfo());
        }

        MultiTableQueryWrapper queryWrapper = roadShowHotUserService.getMultiCommonWrapper()
                .eq(CompareAlias.valueOf("e.visible"), CommonEnumContainer.YesOrNo.RIGHT.getValue())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getDesc())
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort")))
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                .page(req.getPageInfo());
        return Result.success(roadShowHotUserService.selectViewList(queryWrapper));
    }

    /**
     * 路演首页顶部信息（最新、预约、统计等）
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "路演首页顶部信息（最新、预约、统计等）")
    @GetMapping("summary")
    public Result<TrRoadShowHome> summary(TqRoadShowHome req) {
        TrRoadShowHome home = new TrRoadShowHome();
        //统计私募路演数量
        home.setOnlineCount(roadShowInfoService.selectCount(new QueryWrapper()
                .where(FieldValue.fromCondition("state.eq", CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue()))
                .where(FieldValue.fromCondition("deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
        ));
        //统计路演机构
        home.setOrgCount(roadShowInfoService.countOrgs());
        //热门
        home.setReservation(
                roadShowInfoService.selectViewList(
                        roadShowInfoService.getMultiCommonWrapper().selectAllFiels(false)
                                .select("e.id,e.coverImageUrl,e.title,e.presenter,e.viewUsers,e.showDate,e.endDate,e.showDuration")
                                .select0(
                                        SelectAlias.valueOf("case when e.show_date > NOW() then 0 else 1 end expired", true)
                                )
                                .select0(SelectAliasUtil.getFavOfRoadShow(req.getLoginUserId()))
                                .where(FieldValue.fromCondition("e.state.eq", CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue()))
                                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                                //.orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf("expired",true)))
                                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.hot")))
                                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                                .page(PageInfo.valueOf(1, 2))

                )
        );
        //top
        PageInfo topPage = new PageInfo();
        topPage.setPageSize(5);
        home.setTops(
                roadShowInfoService.selectViewList(
                        roadShowInfoService.getMultiCommonWrapper().selectAllFiels(false)
                                .select("e.id,e.coverImageUrl,e.title,e.presenter,e.viewUsers,e.showDate,e.endDate,e.showDuration")
                                .select0(
                                        SelectAlias.valueOf("case when e.show_date > NOW() then 0 else 1 end expired", true)
                                )
                                .select0(SelectAliasUtil.getFavOfRoadShow(req.getLoginUserId()))
                                .where(FieldValue.fromCondition("e.state.eq", CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue()))
                                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                                //.orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf("expired",true)))
                                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.top")))
                                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                                .page(topPage)

                )
        );
        return Result.success(home);
    }

    /**
     * 路演列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "路演列表")
    @GetMapping("list")
    public Result<TvPageList<TvRoadShowInfo>> list(TqRoadShowList req) {
        Long thirtyDayHotCateId = Long.valueOf(dictionaryCache.getValue(DictionaryKey.Keys._30_DAYS_HOT_ROAD_SHOW_CATEGORY_ID.getValue()));
        if (req.getCateId() != null && thirtyDayHotCateId == req.getCateId()) {
            req.setCateId(null);
            req.setBeginSearchDate(DateUtil.dateToDateString(DateUtil.addDate(new Date(), -30), DateUtil.yyyy_MM_dd_HH_mm_ss_EN));
            req.getPageInfo().setSortField(TsRoadShowInfo.Fields.viewUsers.name());
            req.getPageInfo().setSortDistanct("DESC");
        }
        MultiTableQueryWrapper queryWrapper = roadShowInfoService.getMultiCommonWrapper();
        queryWrapper
                .select("e.id,e.coverImageUrl,e.title,e.presenter,e.viewUsers,e.showDate,e.endDate,e.showDuration")
                .select0(
                        SelectAlias.valueOf("case when e.show_date > NOW() then 0 else 1 end expired", true)
                )
                .select0(SelectAliasUtil.getFavOfRoadShow(req.getLoginUserId()))
        ;
        if (null != req.getCateId()) {
            queryWrapper.eq(CompareAlias.valueOf("e.cateId"), req.getCateId());
        }
        if (null != req.getLabelId()) {
            queryWrapper.or(or -> or
                    .eq(CompareAlias.valueOf("e.labelId"), req.getLabelId())
                    .eq(CompareAlias.valueOf("e.labelId0"), req.getLabelId())
            );
        }
        if (null != req.getBeginSearchDate()) {
            queryWrapper.ge(CompareAlias.valueOf("e.updateDate"), req.getBeginSearchDate());
        }
        if (null != req.getEndSearchDate()) {
            queryWrapper.le(CompareAlias.valueOf("e.updateDate"), req.getEndSearchDate());
        }
        if (null != req.getPartyId()) {
            queryWrapper.eq(CompareAlias.valueOf("e.orgId"), req.getPartyId());
        }
        if (null != req.getPresenter()) {
            queryWrapper.eq(CompareAlias.valueOf("e.presenter"), req.getPresenter());
        }
        if (StringUtil.isNotEmpty(req.getKeyword())) {
            queryWrapper.or(x -> x
                    .like(CompareAlias.valueOf("e.title"), req.getKeyword())
                    .like(CompareAlias.valueOf("e.presenter"), req.getKeyword())
                    .like(CompareAlias.valueOf("i.partyShortName"), req.getKeyword())
                    .like(CompareAlias.valueOf("i.partyFullName"), req.getKeyword())
            );
        }
        queryWrapper
                .where(FieldValue.fromCondition("e.state.eq", CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue()))
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .page(req.getPageInfo())
        ;
        if (StringUtil.isNotEmpty(req.getPageInfo().getSortField()) && StringUtil.isNotEmpty(req.getPageInfo().getSortDistanct())) {
            queryWrapper
                    .orderBy(OrderBy.valueOf(Order.valueOf(req.getPageInfo().getSortDistanct()), SelectAlias.valueOf(req.getPageInfo().getSortField())))
            ;
        } else {
            queryWrapper
                    //.orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf("expired",true)))
                    .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.showDate")))
            ;
        }

        return Result.success(roadShowInfoService.selectPageList(queryWrapper));
    }

    /**
     * 路演详情
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "路演详情")
    @GetMapping("detail")
    public Result<TvRoadShowInfo> detail(TqRoadShowDetail req) {

        TvRoadShowInfo roadShowInfo = roadShowInfoService.selectOneView(
                roadShowInfoService.getMultiCommonWrapper()
                        .select0(SelectAliasUtil.getFavOfRoadShow(req.getLoginUserId()))
                        .eq(CompareAlias.valueOf("e.id"), req.getId())
        );
        Assert.notNull(roadShowInfo, "路演不存在！");
        if (req.getUserInfo() != null) {
            TbUserRoadShowView has = userRoadShowViewService.selectOne(
                    new QueryWrapper()
                            .select(TsUserRoadShowReservation.Fields.id, TsUserRoadShowReservation.Fields.deltag)
                            .eq(TsUserRoadShowReservation.Fields.userId, req.getLoginUserId())
                            .eq(TsUserRoadShowReservation.Fields.roadShowId, req.getId())
            );
            if (has == null) {
                TbUserRoadShowView res = new TbUserRoadShowView()
                        .setRoadShowId(req.getId())
                        .setUpdateDate(new Date())
                        .setUserId(req.getLoginUserId());
                userRoadShowViewService.insert(new TqUserRoadShowView().setEntity(res));
                //增加查看按人数
                roadShowInfoService.updateSelectItem(
                        new UpdateWrapper()
                                .update(
                                        Elements.valueOf(TsRoadShowInfo.Fields.updateDate, new Date()),
                                        Elements.valueOf(TsRoadShowInfo.Fields.viewUsers, roadShowInfo.getViewUsers() + 1)
                                )
                                .eq(TsRoadShowInfo.Fields.id, req.getId())
                );
            } else {
                TbUserRoadShowView up = new TbUserRoadShowView();
                up.setId(has.getId());
                userRoadShowViewService.updateByPrimary(new TqUserRoadShowView().setEntity(up));
            }
        }
        return Result.success(roadShowInfo);
    }

    /**
     * 相关路演
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "相关路演")
    @GetMapping("relations")
    public Result<List<TvRoadShowInfo>> relations(TqRoadShowRelation req) {
        TvRoadShowInfo view = roadShowInfoService.selectViewByPrimaryKey(roadShowInfoService.getMultiCommonIdWrapper(req.getId()));
        if (null == view) {
            return Result.success(Collections.emptyList());
        }
        PageInfo page = new PageInfo();
        page.setPageSize(5);
        MultiTableQueryWrapper queryWrapper = roadShowInfoService.getMultiCommonWrapper()
                .selectAllFiels(false)
                .select("e.id,e.coverImageUrl,e.title,e.presenter,e.viewUsers,e.showDate,e.endDate,e.showDuration")
                .select0(
                        SelectAlias.valueOf("case when e.show_date > NOW() then 0 else 1 end expired", true)
                )
                .select0(SelectAliasUtil.getFavOfRoadShow(req.getLoginUserId()));
        queryWrapper.or(x ->
                {
                    if (null != view.getLabelId()) {
                        x.eq(CompareAlias.valueOf("e.labelId"), view.getLabelId());
                    }
                    if (null != view.getCateId()) {
                        x.eq(CompareAlias.valueOf("e.cateId"), view.getCateId());
                    }
                }
        );
        queryWrapper
                .where(FieldValue.fromCondition("e.state.eq", CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue()))
                .where(FieldValue.fromCondition("e.id.neq", req.getId()))
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("expired", true)))
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.showDate")))
                .page(page);
        return Result.success(roadShowInfoService.selectViewList(queryWrapper));
    }

}
