package com.rong.product.service.impl;

import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.TvPageList;
import com.rong.fundmanage.mapper.PrivateFundsCurrentPerfMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentPerf;
import com.rong.product.annotation.ProductAnnotation;
import com.rong.product.enumerate.ProductEnum;
import com.rong.product.mapper.PrivatePlacementMapper;
import com.rong.product.module.request.TqProductManagement;
import com.rong.product.module.view.TvProductManagerment;
import com.rong.product.service.PrivatePlacementService;
import com.rong.product.service.ProductManagementService;
import com.rong.product.utils.UploadUtils;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.user.mapper.UserProFavMapper;
import com.rong.user.module.entity.TbUserProFav;
import com.vitily.mybatis.core.wrapper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.rong.product.service.impl
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:私募服务接口实现类
 */
@ProductAnnotation(value = ProductEnum.privatePlacement)
@Service
public class PrivatePlacementServiceImpl implements PrivatePlacementService {

    @Resource
    private PrivatePlacementMapper placementMapper;

    @Resource
    private PrivateFundsCurrentPerfMapper perfMapper;

    @Resource
    private UserProFavMapper userProFavMapper;

    private final CommonServiceCache commonServiceCache;

    public PrivatePlacementServiceImpl(CommonServiceCache commonServiceCache) {
        this.commonServiceCache = commonServiceCache;
    }

    /**
     * 私募基金策略查询统一编码
     */
    public static final int PRIVATE_EQUITY_STRATEGY = 40032;

    @Override
    public TvPageList<TvProductManagerment> listOfProductManagementInformation(TqProductManagement req) {
        TvPageList<TvProductManagerment> pageList = new TvPageList<>();
        PageInfo pageInfo = req.getPageInfo();
        pageInfo.setRecordCount(placementMapper.count(req.getUserId()));
        pageList.setPageInfo(pageInfo);
        List<TvProductManagerment> list = setPrivatePlacementDetails(placementMapper.findPrivatePlacementDetailsData(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1), pageInfo.getPageSize(), req.getUserId()));
        pageList.setList(list);
        return pageList;
    }

    @Override
    public boolean addProduct(TqProductManagement product, MultipartFile file) {
        CommonServiceCache cache = getMemCache(commonServiceCache);
        //TODO 由于时间有限，只能先这样实现，后期全面调整
        long securityId = (long) ((Math.random() * 9 + 1) * 100000);
        String filePath = System.getProperty("user.dir");
        UploadUtils.uploadFile(file, filePath);
        //TODO 暂时将文件地址保存到redis中
        cache.setToServer(String.valueOf(securityId), filePath);
        String productName = product.getProductName();
        int result = placementMapper.insertPrivateFundsCurrentPfundRanking(securityId, productName);
        result += insertPfund(securityId, product);
        result += insertPrivateFundsCurrentPerf(securityId, product);
        result += insertUserProFav(securityId, product);
        if (result < 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProduct(Integer securityId) {
        int result = placementMapper.deletePrivateFundsCurrentPfundRanking(securityId);
        if (result == 1) {
            return true;
        }
        return false;
    }

    private int insertUserProFav(long securityId, TqProductManagement product) {
        TbUserProFav fav = new TbUserProFav();
        fav.setSecurityId(securityId);
        fav.setUserId(product.getUserId());
        fav.setType(0);
        return userProFavMapper.insert(fav);
    }

    private int insertPrivateFundsCurrentPerf(long securityId, TqProductManagement product) {
        TbPrivateFundsCurrentPerf perf = new TbPrivateFundsCurrentPerf();
        perf.setSecurityId(securityId);
        //年化收益
        perf.setReturnOfLatestYear(new BigDecimal(product.getAnnualizedReturns()).setScale(2, BigDecimal.ROUND_HALF_UP));
        perf.setUpdateDate(new Date());
        return perfMapper.insert(perf);
    }

    private int insertPfund(long securityId, TqProductManagement product) {
        TbPfund tbPfund = new TbPfund();
        //存续期限
        tbPfund.setDuration(Integer.valueOf(product.getTerm()));
        //起投金额
        tbPfund.setSubscriptionStartPoint(Integer.valueOf(product.getInitialDeliveryAmount()));
        tbPfund.setSecurityId(securityId);
        tbPfund.setUpdateTime(new Date());
        tbPfund.setRecordDate(new Date());
        return placementMapper.insert(tbPfund);
    }

    private static CommonServiceCache getMemCache(CommonServiceCache commonServiceCache) {
        return commonServiceCache.getInstance(DictionaryKey.Keys.OTHER, CacheSerializableDelegate.jsonSeriaze());
    }

    private List<TvProductManagerment> setPrivatePlacementDetails(List<TvProductManagerment> details) {
        List<TvProductManagerment> list = new ArrayList<>();
        if (details == null) {
            return list;
        }
        //TODO 这里后期第一版因为数据不足，暂时不做
        //region 计算同一证劵编码下的日涨幅

//        calculatedDailyIncrease(details);

        //endregion
        for (TvProductManagerment detail : details) {
            //TODO 这里先设置一个死的状态,后期再做调整  1：（审核中）
            detail.setAuditStatus(1);
            // 私募
            detail.setProductType(1);
            //基金类型/基金策略 私募是基金策略，公募是基金类型
            detail.setTheFundType(fundEnquiryStrategy(detail.getSecurityId()));
            list.add(detail);
        }
        return list;
    }

    private String fundEnquiryStrategy(Integer securityId) {
        return placementMapper.fundEnquiryStrategy(securityId, PRIVATE_EQUITY_STRATEGY);
    }

    private void calculatedDailyIncrease(List<TvProductManagerment> details) {
        //通过两个for循环判断
    }
}
