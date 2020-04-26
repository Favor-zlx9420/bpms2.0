package com.rong.console.center.controller.store;

import com.alibaba.fastjson.JSON;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.fundmanage.module.query.TsProductLabel;
import com.rong.fundmanage.module.request.TqSecurityManage;
import com.rong.fundmanage.service.ProductLabelService;
import com.rong.fundmanage.service.SecurityManageService;
import com.rong.store.module.entity.TbDirectStoreProduct;
import com.rong.store.module.query.TsDirectStoreProduct;
import com.rong.store.module.request.TqDirectStoreProduct;
import com.rong.store.module.view.TvDirectStoreProduct;
import com.rong.store.service.DirectStoreProductService;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.STORE_PRODUCT_PATH)
public class StoreProductController extends BaseController<TbDirectStoreProduct, TqDirectStoreProduct, TvDirectStoreProduct, DirectStoreProductService> {
    @Autowired
    private SecurityManageService securityManageService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private ProductLabelService productLabelService;
    @Autowired
    public StoreProductController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.STORE_PRODUCT_PATH)
                .build(),
                TsDirectStoreProduct.Fields.id
                );
    }


    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvDirectStoreProduct be) throws Exception {
        super.otherEventForAddInit(request, response, be);
        attributes(request);
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvDirectStoreProduct view) throws Exception {
        super.otherEventForEditInit(request, response, view);
        view.setProductLabels(productLabelService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsProductLabel.Fields.securityId, view.getSecurityId())
                        .eq(TsProductLabel.Fields.deltag, false)

        ));
        attributes(request);
    }
    private void attributes(HttpServletRequest request){
        request.setAttribute("labelList", JSON.toJSONString(labelService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.PRODUCT_LABEL_OF_DIRECT_SALE_STORE.getValue())
                        .eq(TsLabel.Fields.deltag, false)
        )));
    }

    @Override
    protected void preInsert(HttpServletRequest request, HttpServletResponse response, TqDirectStoreProduct req){
        TbDirectStoreProduct entity = req.getEntity();
        if(entity.getSecurityId() != null){
            TbDirectStoreProduct storeProduct = service.selectOne(
                    new QueryWrapper()
                            .select(TsDirectStoreProduct.Fields.id)
                            .eq(TsDirectStoreProduct.Fields.securityId,entity.getSecurityId())
            );
            if(null != storeProduct){
                throw new DuplicateDataException("该产品已存在于直营店中！如果已删除，请使用恢复功能恢复");
            }
            //null 则先插入一条数据
            TbSecurityManage securityManage = new TbSecurityManage()
                    .setHotSearch(CommonEnumContainer.YesOrNo.DENY.getValue())
                    .setVisible(CommonEnumContainer.YesOrNo.RIGHT.getValue())
                    .setRecommend(CommonEnumContainer.YesOrNo.DENY.getValue())
                    .setType(entity.getType())
                    .setSecurityId(entity.getSecurityId())
                    .setId(SnowflakeIdWorker.create().nextId())
                    ;
            securityManageService.insert(new TqSecurityManage().setEntity(securityManage));
            entity.setSecurityId(entity.getSecurityId());
        }

    }
}
