package com.rong.console.center.controller.store;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import com.rong.store.module.query.TsDirectStoreOrgLabel;
import com.rong.store.module.request.TqDirectStoreOrgLabel;
import com.rong.store.module.view.TvDirectStoreOrgLabel;
import com.rong.store.service.DirectStoreOrgLabelService;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.STORE_ORG_LABEL_PATH)
public class StoreOrgLabelController extends BaseController<TbDirectStoreOrgLabel, TqDirectStoreOrgLabel, TvDirectStoreOrgLabel, DirectStoreOrgLabelService> {

    @Autowired
    private LabelService labelService;
    @Autowired
    public StoreOrgLabelController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.STORE_ORG_LABEL_PATH)
                .build(), TsDirectStoreOrgLabel.Fields.id);
    }
    @Override
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        Long labelId = Long.valueOf(request.getParameter("cid"));
        TbLabel label = labelService.selectItemByPrimaryKey(IdWrapper.valueOf(labelId));
        request.setAttribute("label",label);

    }
}
