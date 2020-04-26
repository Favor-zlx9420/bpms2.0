package com.rong.console.center.controller.cms;

import com.rong.cms.module.entity.TbCmsType;
import com.rong.cms.module.query.TsCmsDynProper;
import com.rong.cms.module.query.TsCmsDynproVal;
import com.rong.cms.module.query.TsCmsType;
import com.rong.cms.module.request.TqCmsType;
import com.rong.cms.module.view.TvCmsType;
import com.rong.cms.service.CmsDynProperService;
import com.rong.cms.service.CmsDynproValService;
import com.rong.cms.service.CmsTypeService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(RightConst.CMS_TYPE_PATH)
public class CmsTypeController extends BaseController<TbCmsType, TqCmsType, TvCmsType, CmsTypeService> {
    private final CmsDynProperService cmsDynProperService;
    private final CmsDynproValService cmsDynproValService;
    @Autowired
    public CmsTypeController(CmsDynProperService cmsDynProperService, CmsDynproValService cmsDynproValService) {
        super(new ViewModel.Builder()
                .basePath(RightConst.CMS_TYPE_PATH)
                .build(), TsCmsType.Fields.id);
        this.cmsDynProperService = cmsDynProperService;
        this.cmsDynproValService = cmsDynproValService;
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvCmsType view) throws Exception {
        super.otherEventForEditInit(request, response, view);
    }

    /**
     * 根据类型获取动态参数
     * @param
     * @return
     */
    @GetMapping(value="dyns")
    @ResponseBody
    public Result<List> dyns(Long typeId, Long newsId){
        //只有typeId
        //调用原型
        List list;
        if(CommonUtil.isNull(newsId)){
            list = cmsDynProperService.selectList(
              new QueryWrapper()
                      .eq(TsCmsDynProper.Fields.typeId, typeId)
                      .eq(TsCmsDynProper.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
            );
        }else{
            list = cmsDynproValService.selectViewList(cmsDynproValService.getMultiCommonWrapper()
                    .eq(CompareAlias.valueOf(TsCmsDynproVal.Fields.newsId,"e"),newsId)
            );
        }
        return Result.success(list);
    }
}
