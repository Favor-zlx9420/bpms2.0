package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.module.request.TqSearch;
import com.rong.assembly.api.module.request.TqSearchIndex;
import com.rong.common.module.Result;
import com.rong.common.module.TqBase;
import com.rong.common.module.TvPageList;
import com.rong.tong.fund.service.FundClassService;
import com.rong.tong.pfunds.module.request.TqCompanyRanking;
import com.rong.tong.pfunds.module.request.TqManagerRanking;
import com.rong.tong.pfunds.module.request.TqRanking;
import com.rong.tong.pfunds.module.request.TqSuperProduce;
import com.rong.tong.pfunds.module.view.TvCompanyRanking;
import com.rong.tong.pfunds.module.view.TvHostSearch;
import com.rong.tong.pfunds.module.view.TvManagerRanking;
import com.rong.tong.pfunds.module.view.TvRanking;
import com.rong.tong.pfunds.module.view.TvRankingSimple;
import com.rong.tong.pfunds.module.view.TvSearchPfundInfo;
import com.rong.tong.pfunds.module.view.TvSearchPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvSearchPfundManager;
import com.rong.tong.pfunds.module.view.TvSuperProduce;
import com.rong.tong.pfunds.service.BusinessService;
import com.rong.tong.pfunds.service.PfundInstInfoService;
import com.rong.tong.pfunds.service.PfundManagerService;
import com.rong.tong.pfunds.service.PfundService;
import com.vitily.mybatis.core.wrapper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "产品业务")
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private PfundService pfundService;

    @Autowired
    private PfundInstInfoService pfundInstInfoService;

    @Autowired
    private PfundManagerService pfundManagerService;

    @Autowired
    private FundClassService fundClassService;

    /**
     * 搜索类型数量
     * @param req
     * @return
     */
    @ApiOperation(value = "搜索类型数量")
    @GetMapping("searchIndex")
    public Result search(TqSearchIndex req){
        return Result.success(businessService.searchIndex(req.getKey()));
    }

    /**
     * 私募产品搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "私募产品搜索")
    @GetMapping("searchFundInfo")
    public Result<TvPageList<TvSearchPfundInfo>> searchFundInfo(TqSearch req){
        TvPageList<TvSearchPfundInfo> tvPageList = pfundService.getSearchPfundInfo(req.getPageInfo() == null ? new PageInfo() : req.getPageInfo()
                , "%" + req.getKey() + "%", req.getLoginUserId());
        return Result.success(tvPageList);
    }

    /**
     * 公募搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "公募搜索")
    @GetMapping("searchFundClassInfo")
    public Result<TvPageList<TvSearchPfundInfo>> searchFundClassInfo(TqSearch req){
        TvPageList<TvSearchPfundInfo> tvPageList = fundClassService.getSearchFundClassInfo(req.getPageInfo() == null ? new PageInfo() : req.getPageInfo()
                , "%" + req.getKey() + "%", req.getLoginUserId());
        return Result.success(tvPageList);
    }

    /**
     * 公司搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "公司搜索")
    @GetMapping("searchFundOrg")
    public Result<TvPageList<TvSearchPfundInstInfo>> searchFundOrg(TqSearch req){
        TvPageList<TvSearchPfundInstInfo> tvPageList = pfundInstInfoService.getSearchPfundInstInfo(
                req.getPageInfo()==null?new PageInfo():req.getPageInfo()
                , "%"+req.getKey()+"%");
        return Result.success(tvPageList);
    }

    /**
     * 经理搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "经理搜索")
    @GetMapping("searchFundManager")
    public Result<TvPageList<TvSearchPfundManager>> searchFundManager(TqSearch req){
        TvPageList<TvSearchPfundManager> tvPageList = pfundManagerService.getSearchPfundManager(req.getPageInfo()==null?new PageInfo():req.getPageInfo()
                , "%"+req.getKey()+"%");
        return Result.success(tvPageList);
    }

    /**
     * 热门搜索
     * @param req
     * @return
     */
    @ApiOperation(value = "热门搜索")
    @GetMapping("hostSearch")
    public Result<List<TvHostSearch>> hostSearch(TqBase req){
        return Result.success(businessService.hostSearch());
    }

    /**
     * 热门搜索(全称)
     * @param req
     * @return
     */
    @ApiOperation(value = "热门搜索(全称)")
    @GetMapping("hostSearchFull")
    public Result<List<TvHostSearch>> hostSearchFull(TqBase req){
        return Result.success(businessService.hostSearchFull());
    }

    /**
     * pc端私募排行榜
     * @param req
     * @return
     */
    @ApiOperation(value = "pc端私募排行榜")
    @GetMapping("ranking")
    public Result<TvPageList<TvRanking>> ranking(TqRanking req){
        TvPageList<TvRanking> tvPageList = businessService.ranking(req);
        return Result.success(tvPageList);
    }

    /**
     * app端排行榜
     * @param req
     * @return
     */
    @ApiOperation(value = "app端排行榜")
    @GetMapping("rankingSimple")
    public Result<TvPageList<TvRankingSimple>> rankingSimple(TqRanking req){
        TvPageList<TvRankingSimple> tvPageList = businessService.rankingSimple(req);
        return Result.success(tvPageList);
    }

    /**
     * 在售优品(优选基金)
     * @param req
     * @return
     */
    @ApiOperation(value = "在售优品(优选基金)")
    @GetMapping("superProduce")
    public Result<TvPageList<TvSuperProduce>> superProduce(TqSuperProduce req){
        TvPageList<TvSuperProduce> tvPageList = businessService.superProduce(req);
        return Result.success(tvPageList);
    }


    /**
     * 公司排行榜
     * @param req
     * @return
     */
    @ApiOperation(value = "公司排行榜")
    @GetMapping("companyRanking")
    public Result<TvPageList<TvCompanyRanking>> companyRanking(TqCompanyRanking req){
        TvPageList<TvCompanyRanking> tvPageList = businessService.companyRanking(req);
        return Result.success(tvPageList);
    }

    /**
     * 经理排行榜
     * @param req
     * @return
     */
    @ApiOperation(value = "经理排行榜")
    @GetMapping("managerRanking")
    public Result<TvPageList<TvManagerRanking>> managerRanking(TqManagerRanking req){
        TvPageList<TvManagerRanking> tvPageList = businessService.managerRanking(req);
        return Result.success(tvPageList);
    }
}
