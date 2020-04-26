package com.rong.assembly.api.controller.product;

import com.rong.assembly.api.constant.ControPathConst;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.product.enumerate.ProductEnum;
import com.rong.product.module.request.TqProductManagement;
import com.rong.product.module.view.TvProductManagerment;
import com.rong.product.service.PrivatePlacementService;
import com.rong.product.service.ProductManagementService;
import com.vitily.mybatis.core.wrapper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.rong.common.consts.CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION;
import static com.rong.common.consts.CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE;
import static com.rong.common.consts.CommonEnumContainer.ResultStatus.THE_USER_DOES_NOT_EXIST;
import static com.rong.product.enumerate.ProductEnum.valueOf;

/**
 * @Package: com.rong.assembly.api.controller.product
 * @Author: LQW
 * @Date: 2020/4/21
 * @Description:产品管理控制器 控制器路径常量
 */
@Api("产品管理")
@RestController
@RequestMapping(ControPathConst.PRODUCT_MANAGEMENT_PATH)
public class ProductManagementController {


    @Autowired
    private ProductManagementService service;

    @Autowired
    private PrivatePlacementService privatePlacementService;


    @RequestMapping(ControPathConst.MethodPath.LIST_DETAILS)
    @ApiOperation(value = "产品管理列表")
    public Result listDetails(@RequestBody TqProductManagement req) {
        if (null == req.getUserId()) {
            return Result.miss(THE_USER_DOES_NOT_EXIST);
        }
        TvPageList<TvProductManagerment> publicPlacementList = privatePlacementService.listOfProductManagementInformation(req);
        TvPageList<TvProductManagerment> tvPageList = service.listOfProductManagementInformation(req);
        TvPageList<TvProductManagerment> list = resetList(publicPlacementList, tvPageList);
        return Result.success(list);
    }


    @RequestMapping(ControPathConst.MethodPath.ADD_PRODUCT)
    @ApiOperation(value = "添加产品")
    public Result addProduct(@RequestBody TqProductManagement product, @ApiParam(value = "file", required = false) @RequestParam(required = false) MultipartFile file) {
        if (null == product.getUserId()) {
            return Result.miss(THE_USER_DOES_NOT_EXIST);
        }
        if (null == product.getProductType()) {
            return Result.miss(PARAMETER_IS_NOT_COMPLETE);
        }
        if (file.isEmpty()) {
            return Result.miss(PARAMETER_IS_NOT_COMPLETE, "文件为空,请重新上传！");
        }
        boolean result = false;
        if (ProductEnum.privatePlacement.getCode().equals(product.getProductType())) {
            result = privatePlacementService.addProduct(product, file);
        } else {
            result = service.addProduct(product, file);
        }
        if (result) {
            return Result.success();
        }
        return Result.miss(ABNORMAL_OPERATION);
    }

    @RequestMapping(ControPathConst.MethodPath.DELETE_PRODUCT)
    @ApiOperation(value = "删除产品")
    public Result deleteProduct(Integer securityId, Integer type) {
        if (null == type || null == securityId) {
            return Result.miss(PARAMETER_IS_NOT_COMPLETE);
        }
        boolean result = false;
        if (type.equals(ProductEnum.privatePlacement.getCode())) {
            result = privatePlacementService.deleteProduct(securityId);
        } else {
            result = service.deleteProduct(securityId);
        }
        if (result) {
            return Result.success();
        }
        return Result.miss(ABNORMAL_OPERATION);
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
