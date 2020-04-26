package com.rong.product.service.impl;

import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.TvPageList;
import com.rong.common.util.StringUtil;
import com.rong.fundmanage.mapper.RaisedFundCurrentNavGrMapper;
import com.rong.fundmanage.mapper.RaisedFundCurrentNavMapper;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNav;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNavGr;
import com.rong.product.annotation.ProductAnnotation;
import com.rong.product.enumerate.ProductEnum;
import com.rong.product.mapper.PublicPlacementMapper;
import com.rong.product.module.request.TqProductManagement;
import com.rong.product.module.view.TvProductManagerment;
import com.rong.product.service.ProductManagementService;
import com.rong.product.utils.UploadUtils;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.query.TsFundClass;
import com.rong.tong.pfunds.mapper.MdSecurityMapper;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.user.mapper.UserProFavMapper;
import com.rong.user.module.entity.TbUserProFav;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.delete.DeleteWrapper;
import org.apache.commons.lang3.StringUtils;
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
 * @Description:公募服务接口实现类
 */
@ProductAnnotation(ProductEnum.publicPlacement)
@Service
public class PublicPlacementServiceImpl implements ProductManagementService {

    @Resource
    private PublicPlacementMapper placementMapper;

    private final CommonServiceCache commonServiceCache;
    @Resource
    private MdSecurityMapper securityMapper;
    @Resource
    private RaisedFundCurrentNavGrMapper fundCurrentNavMapper;
    @Resource
    private UserProFavMapper userProFavMapper;

    public PublicPlacementServiceImpl(CommonServiceCache commonServiceCache) {
        this.commonServiceCache = commonServiceCache;
    }

    /**
     * 公募基金类型统一查询编码
     */
    public static final int PUBLIC_OFFERING_FUND_TYPE = 10001;

    @Override
    public TvPageList<TvProductManagerment> listOfProductManagementInformation(TqProductManagement req) {
        TvPageList<TvProductManagerment> pageList = new TvPageList<>();
        PageInfo pageInfo = req.getPageInfo();
        pageInfo.setRecordCount(placementMapper.count(req.getUserId()));
        pageList.setPageInfo(pageInfo);
        List<TvProductManagerment> list = setPublicPlacementDetails(placementMapper.findThePublicOfferingDetails(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1), pageInfo.getPageSize(), req.getUserId()));
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
        int result = insertPublicOfferingInformation(securityId, product);
        if (result < 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProduct(Integer securityId) {
        int result = placementMapper.delete(new DeleteWrapper().eq(TsFundClass.Fields.securityId, securityId));
        if (result == 1) {
            return true;
        }
        return false;
    }

    private int insertPublicOfferingInformation(Long securityId, TqProductManagement product) {
        TbFundClass fundClass = new TbFundClass();
        fundClass.setSecurityId(securityId);
        fundClass.setEstablishDate(new Date());
        int result = placementMapper.insert(fundClass);
        TbMdSecurity mdSecurity = new TbMdSecurity();
        mdSecurity.setSecurityId(securityId.intValue());
        mdSecurity.setSecShortName(product.getProductName());
        result += securityMapper.insert(mdSecurity);
        TbRaisedFundCurrentNavGr currentNav = new TbRaisedFundCurrentNavGr();
        currentNav.setReturnRate1y(new BigDecimal(product.getAnnualizedReturns()).setScale(2, BigDecimal.ROUND_HALF_UP));
        result += fundCurrentNavMapper.insert(currentNav);
        TbUserProFav fav = new TbUserProFav();
        fav.setSecurityId(securityId);
        fav.setUserId(product.getUserId());
        fav.setType(0);
        result += userProFavMapper.insert(fav);
        return result;
    }

    private static CommonServiceCache getMemCache(CommonServiceCache commonServiceCache) {
        return commonServiceCache.getInstance(DictionaryKey.Keys.OTHER, CacheSerializableDelegate.jsonSeriaze());
    }

    private List<TvProductManagerment> setPublicPlacementDetails(List<TvProductManagerment> details) {
        List<TvProductManagerment> list = new ArrayList<>();
        if (details == null) {
            return list;
        }
        //TODO 这里后期第一版因为数据不足，暂时不做
        //region 计算同一证劵编码下的日涨幅

        calculatedDailyIncrease(details);

        //endregion
        for (TvProductManagerment detail : details) {
            //TODO 这里先设置一个死的状态,后期再做调整  1：（审核中）
            detail.setAuditStatus(1);
            // 公募
            detail.setProductType(2);
            //基金类型/基金策略 私募是基金策略，公募是基金类型
            detail.setTheFundType(isNullOrEmpty(findFundType(detail.getSecurityId())));
            list.add(detail);
        }
        return list;
    }

    private String findFundType(Integer securityId) {
        return placementMapper.fundTypeEnquiry(securityId, PUBLIC_OFFERING_FUND_TYPE);
    }

    public String isNullOrEmpty(String value) {
        return StringUtil.isNotEmpty(value) ? value : "-";
    }

    private void calculatedDailyIncrease(List<TvProductManagerment> details) {

    }
}
