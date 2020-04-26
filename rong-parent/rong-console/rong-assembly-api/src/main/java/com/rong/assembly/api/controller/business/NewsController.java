package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.module.request.TqGetCmsDetail;
import com.rong.assembly.api.module.request.TqGetCmsNews;
import com.rong.cms.consts.CmsEnumContainer;
import com.rong.cms.module.entity.TbCmsNews;
import com.rong.cms.module.query.TsCmsNews;
import com.rong.cms.module.view.TvCmsNews;
import com.rong.cms.service.CmsNewsService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.product.fact.ProductFactory;
import com.rong.product.module.view.TvProductManagerment;
import com.rong.product.service.ProductManagementService;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.query.TsUserCardInfo;
import com.rong.usercard.service.UserCardInfoService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rong.product.enumerate.ProductEnum.valueOf;

@Api(tags = "内容中心、公告、新闻")
@RequestMapping("cms")
@RestController
public class NewsController {

    private final CmsNewsService cmsNewsService;

    @Autowired
    private UserCardInfoService userCardInfoService;

    private ProductManagementService productManagementService;

    public NewsController(CmsNewsService cmsNewsService) {
        this.cmsNewsService = cmsNewsService;
    }

    @GetMapping("list")
    @ApiOperation(value = "内容列表")
    public Result<TvPageList<TvCmsNews>> list(TqGetCmsNews req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = cmsNewsService.getMultiCommonWrapper().selectAllFiels(false)
                ;
        if(null != req.getCateId()){
            queryWrapper
                    .eq(CompareAlias.valueOf(TsCmsNews.Fields.cateId,"e"), req.getCateId());
        }
        if(null != req.getTypeId()){
            queryWrapper
                    .eq(CompareAlias.valueOf(TsCmsNews.Fields.typeId,"e"), req.getTypeId());
        }
        return Result.success(cmsNewsService.selectPageList(queryWrapper
                .eq(CompareAlias.valueOf(TsCmsNews.Fields.state,"e"), CommonEnumContainer.State.NORMAL.getValue())
                .eq(CompareAlias.valueOf(TsCmsNews.Fields.published,"e"), CmsEnumContainer.Published.RELEASED.getValue())
                .eq(CompareAlias.valueOf(TsCmsNews.Fields.deltag,"e"), false)
                .page(pageInfo)
                .orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf(TsCmsNews.Fields.sort,"e")))
        ));
    }
    @ApiOperation(value = "内容详情")
    @GetMapping("info")
    public Result<TvCmsNews> info(TqGetCmsDetail req){
        TvCmsNews detail = cmsNewsService.selectViewByPrimaryKey(
                cmsNewsService.getMultiCommonIdWrapper(req.getId())
        );
        if(detail.getDeltag() != null && detail.getDeltag()){
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"该文章已被删除");
        }
        return Result.success(detail);
    }

    /**
     * 首页财经早报
     * 此处展示两部分内容：1：用户个人名片信息 2：要闻财经  3：推荐产品
     */
    @ApiOperation(value = "财经早报")
    @PostMapping("dailyFinanceNews")
    public Result<Map> dailyFinanceNews(TqGetCmsNews req){
        if(null == req || req.getUserId() < 0){
            throw new CustomerException("首页访问财经早报，请求参数校验不通过", CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE);
        }
        /**
         * 1：拉取用户个人名片信息
          */
        TbUserCardInfo userCardInfo = userCardInfoService.selectOne(new QueryWrapper().eq(TsUserCardInfo.Fields.userId, req.getUserId()));
        if(null == userCardInfo){
            throw new CustomerException("用户不存在", CommonEnumContainer.ResultStatus.THE_USER_DOES_NOT_EXIST);
        }

        /**
         * 2：拉取要闻数据：每个栏目仅拉取最新的一条数据(因为数据量原因，暂时不做分页处理)
         */
        List<TbCmsNews> cmsNewsList = cmsNewsService.selectCmsNewsInfoList();
        /**
         * 3: 拉取推荐产品信息列表
         */
//        TqProductManagement productInfoReq = new TqProductManagement();
//        PageInfo pageInfo = new PageInfo();
//        pageInfo.setPageIndex(req.getPageInfo().getPageIndex());
//        pageInfo.setPageSize(req.getPageInfo().getPageSize());
//        productInfoReq.setPageInfo(pageInfo);
//        productManagementService = createInstance(1);
//        TvPageList<TvProductManagerment> publicPlacementList = productManagementService.listOfProductManagementInformation(productInfoReq);
//        productManagementService = createInstance(2);
//        TvPageList<TvProductManagerment> privatePlacementList = productManagementService.listOfProductManagementInformation(productInfoReq);
//        TvPageList<TvProductManagerment> productInfoList = resetList(publicPlacementList, privatePlacementList);
        // 返回信息写入集合，展示给前端
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userCardInfo", userCardInfo);
        map.put("cmsNewsList", cmsNewsList);
//        map.put("productInfoList", productInfoList);
        return Result.success(map);
    }

    /**
     * 创建实例
     *
     * @param code 引用值
     * @return service
     */
    public ProductManagementService createInstance(Integer code) {
        return ProductFactory.create(valueOf(code));
    }

    /**
     * 重制集合
     *
     * @param publicPlacementList 公募数据
     * @param tvPageList          私募数据
     * @return list
     */
    private TvPageList<TvProductManagerment> resetList(TvPageList<TvProductManagerment> publicPlacementList, TvPageList<TvProductManagerment> tvPageList) {
        TvPageList<TvProductManagerment> list = new TvPageList<TvProductManagerment>();
        int recordCount = publicPlacementList.getPageInfo().getRecordCount();
        int count = tvPageList.getPageInfo().getRecordCount();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRecordCount(recordCount + count);
        list.setPageInfo(pageInfo);
        List<TvProductManagerment> publicPlacementListList = publicPlacementList.getList();
        List<TvProductManagerment> privatePlacementListList = tvPageList.getList();
        publicPlacementListList.addAll(privatePlacementListList);
        list.setList(publicPlacementListList);
        return list;
    }
}