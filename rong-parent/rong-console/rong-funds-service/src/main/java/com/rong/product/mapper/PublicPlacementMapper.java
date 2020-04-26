package com.rong.product.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.product.module.view.TvProductManagerment;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.view.TvFundClass;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.rong.product.mapper
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:公募数据交互层
 */
public interface PublicPlacementMapper extends CommonBasicMapper<TbFundClass, TvFundClass>, MultiTableMapper<TbFundClass, TvFundClass> {
    Integer count(@Param("userId") Long userId);

    List<TvProductManagerment> findThePublicOfferingDetails(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize, @Param("userId") Long userId);

    String fundTypeEnquiry(@Param("securityId") Integer securityId, @Param("strategyCode") Integer strategyCode);
}
