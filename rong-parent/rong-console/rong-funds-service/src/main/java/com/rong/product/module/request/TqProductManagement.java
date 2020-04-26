package com.rong.product.module.request;

import com.rong.common.module.TqBase;
import com.rong.product.module.view.TvProductManagerment;
import com.vitily.mybatis.core.wrapper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Package: com.rong.product.module.request
 * @Author: LQW
 * @Date: 2020/4/21
 * @Description:产品管理请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TqProductManagement extends TvProductManagerment {
    /**
     *  分页对象
     */
    private PageInfo pageInfo = new PageInfo();
    /**
     *  用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;
}
