package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.mapper.CmsMapper;
import com.rong.cms.module.entity.TbCmsCategory;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.module.TqBase;
import com.rong.common.module.TvPageList;
import com.rong.common.util.WrapperFactory;
import com.rong.tong.pfunds.module.request.TqNewsDetail;
import com.rong.tong.pfunds.module.request.TqNewsList;
import com.rong.tong.pfunds.module.view.TvNewsCate;
import com.rong.tong.pfunds.module.view.TvNewsDetail;
import com.rong.tong.pfunds.module.view.TvNewsList;
import com.rong.tong.pfunds.service.VnewsContentV1Service;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "新闻")
@RestController
@RequestMapping("/news")
public class VnewsController {

    @Autowired
    private VnewsContentV1Service vnewsContentV1Service;
    @Autowired
    private CmsMapper cmsMapper;

    /**
     * 新闻列表
     * @param req
     * @return
     */
    @ApiOperation(value = "新闻列表")
    @GetMapping("pageList")
    public Result<TvPageList<TvNewsList>> pageList(TqNewsList req){
        TvPageList<TvNewsList> tvPageList = vnewsContentV1Service.pageList(req);
        return Result.success(tvPageList);
    }

    /**
     * 通联新闻详情
     * @param req
     * @return
     */
    @ApiOperation(value = "通联新闻详情")
    @GetMapping("detail")
    public Result<TvNewsDetail> detail(TqNewsDetail req){
        TvNewsDetail detail = vnewsContentV1Service.detail(req);
        return Result.success(detail);
    }


    /**
     * 热门搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "热门搜索")
    @GetMapping("hotSearch")
    public Result<List<TvNewsList>> hotSearch(TqBase req){
        return Result.success(vnewsContentV1Service.hostSearch());
    }

    /**
     * 自产新闻详情
     * @param req
     * @return
     */
    @ApiOperation(value = "自产新闻详情")
    @GetMapping("detailSelf")
    public Result<TvNewsDetail> detailSelf(TqNewsDetail req){
        TvNewsDetail detail = vnewsContentV1Service.detailSelf(req);
        return Result.success(detail);
    }

    @Value("${newsTopCateId:9}")
    private Long newsTopCateId;
    /**
     * 新闻栏目
     * @param req
     * @return
     */
    @ApiOperation(value = "新闻栏目")
    @GetMapping("newsCate")
    public Result<List<TvNewsCate>> newsCate(TqBase req){
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbCmsCategory.class)
                .select("e.id cateId,e.name")
                .eq(CompareAlias.valueOf("parentId"),newsTopCateId)
                .eq(CompareAlias.valueOf("state"), CommonEnumContainer.State.NORMAL.getValue())
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.sort")))
                ;
        return Result.success(cmsMapper.cmsCateList(queryWrapper));
    }

    /**
     * 新闻搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "新闻搜索")
    @GetMapping("search")
    public Result<TvPageList<TvNewsList>> search(TqNewsList req){
        TvPageList<TvNewsList> tvPageList = vnewsContentV1Service.search(req);
        return Result.success(tvPageList);
    }
}
