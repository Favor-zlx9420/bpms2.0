package com.rong.product.service;

import com.rong.common.module.TvPageList;
import com.rong.product.module.request.TqProductManagement;
import com.rong.product.module.view.TvProductManagerment;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Package: com.rong.product.service
 * @Author: LQW
 * @Date: 2020/4/26
 * @Description:
 */
public interface PrivatePlacementService {
    /**
     *  产品管理信息列表
     * @param req   请求对象
     * @return  productManagerments
     */
    TvPageList<TvProductManagerment> listOfProductManagementInformation(TqProductManagement req);

    boolean addProduct(TqProductManagement product, MultipartFile file);

    boolean deleteProduct(Integer product);
}
