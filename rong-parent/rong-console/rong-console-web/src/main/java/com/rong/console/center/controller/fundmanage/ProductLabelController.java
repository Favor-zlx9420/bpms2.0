package com.rong.console.center.controller.fundmanage;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbProductLabel;
import com.rong.fundmanage.module.query.TsProductLabel;
import com.rong.fundmanage.module.request.TqProductLabel;
import com.rong.fundmanage.module.view.TvProductLabel;
import com.rong.fundmanage.service.ProductLabelService;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.PRODUCT_LABEL_PATH)
public class ProductLabelController extends BaseController<TbProductLabel, TqProductLabel, TvProductLabel, ProductLabelService> {

    @Autowired
    private LabelService labelService;
    @Autowired
    public ProductLabelController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.PRODUCT_LABEL_PATH)
                .build(), TsProductLabel.Fields.id);
    }
    @Override
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        Long labelId = Long.valueOf(request.getParameter("cid"));
        TbLabel label = labelService.selectItemByPrimaryKey(IdWrapper.valueOf(labelId));
        request.setAttribute("label",label);

    }
}
