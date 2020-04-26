package com.rong.product.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.product.module.view.TvProductManagerment;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.view.TvPfund;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.rong.product.mapper
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:私募数据交互层
 */
public interface PrivatePlacementMapper extends CommonBasicMapper<TbPfund, TvPfund>, MultiTableMapper<TbPfund, TvPfund> {
    Integer count(@Param("userId") Long userId);

    List<TvProductManagerment> findPrivatePlacementDetailsData(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("userId") Long userId);

    String fundEnquiryStrategy(@Param("securityId") Integer securityId, @Param("strategyCode") Integer strategyCode);

    int insertPrivateFundsCurrentPfundRanking(@Param("securityId") long securityId, @Param("productName") String productName);

    int deletePrivateFundsCurrentPfundRanking(@Param("securityId") Integer securityId);
}
