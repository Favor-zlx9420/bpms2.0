package com.rong.product.fact;

import com.rong.product.annotation.ProductAnnotation;
import com.rong.product.context.ProductContext;
import com.rong.product.enumerate.ProductEnum;
import com.rong.product.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.rong.product.fact
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:
 */
@Component
public class ProductFactory {

    @Autowired
    private ProductContext context;

    private static Map<ProductEnum, ProductManagementService> map = new HashMap<ProductEnum, ProductManagementService>();

    public ProductFactory() {
    }

    public @PostConstruct
    void init() {
        Map<String, Object> beans = context.getContext().getBeansWithAnnotation(ProductAnnotation.class);
        for (Object obj : beans.values()) {
            ProductAnnotation annotation = obj.getClass().getAnnotation(ProductAnnotation.class);
            map.put(annotation.value(), (ProductManagementService) obj);
        }
    }

    /**
     * 创建服务接口
     *
     * @param annotation code
     * @return service
     */
    public static ProductManagementService create(ProductEnum annotation) {
        return map.get(annotation);
    }
}
