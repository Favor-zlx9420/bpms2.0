package com.rong.console.center.controller.cms;

import com.rong.admin.module.foreign.UserStorage;
import com.rong.cache.service.DictionaryCache;
import com.rong.cms.consts.CmsEnumContainer;
import com.rong.cms.module.entity.TbCmsCategory;
import com.rong.cms.module.entity.TbCmsNews;
import com.rong.cms.module.query.TsCmsCategory;
import com.rong.cms.module.query.TsCmsNews;
import com.rong.cms.module.request.TqCmsNews;
import com.rong.cms.module.view.TvCmsNews;
import com.rong.cms.service.CmsCategoryService;
import com.rong.cms.service.CmsNewsService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.StringUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(RightConst.CMS_NEWS_PATH)
public class CmsNewsController extends BaseController<TbCmsNews, TqCmsNews, TvCmsNews, CmsNewsService> {

    private final LabelService labelService;
    private final CmsCategoryService cmsCategoryService;
    private final DictionaryCache dictionaryCache;

    @Autowired
    public CmsNewsController(LabelService labelService, CmsCategoryService cmsCategoryService, DictionaryCache dictionaryCache) {
        super(new ViewModel.Builder()
                        .basePath(RightConst.CMS_NEWS_PATH)
                        .build(),
                TsCmsNews.Fields.id
        );
        this.labelService = labelService;
        this.cmsCategoryService = cmsCategoryService;
        this.dictionaryCache = dictionaryCache;
    }

    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request) {
        super.wrapQuery(queryWrapper, request);
        String keyword = request.getParameter("keyword");
        if(StringUtil.isNotEmpty(keyword)){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("e.title"),"%"+keyword+"%")
                    .like(CompareAlias.valueOf("e.author"),"%"+keyword+"%")
            );
        }
    }

    /**
     * 列表页需要增加分组搜索
     *
     * @param request
     * @param response
     */
    @Override
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        intAttributes(request, response, null);
    }

    @Override
    @GetMapping(value = "add")
    public String add(HttpServletRequest request, HttpServletResponse response, TvCmsNews v) throws Exception {
        //add
        TvCmsNews entity = new TvCmsNews();
        TbCmsCategory category = cmsCategoryService.selectItemByPrimaryKey(IdWrapper.valueOf(
                entity.getCateId(), TsCmsCategory.Fields.typeId
        ));
        if (CommonUtil.isNotNull(category)) {
            entity.setTypeId(category.getTypeId());
        }

        UserStorage userStorage = admAspect.getAdminByServerStorage(request);

        entity.setAuthor(userStorage.getShowName());
        entity.setTop(CmsEnumContainer.Top.DID_NOT_ANSWER.getValue());
        entity.setPublished(CmsEnumContainer.Published.RELEASED.getValue());
        entity.setState(CommonEnumContainer.State.NORMAL.getValue());
        entity.setCommentable(CmsEnumContainer.Commentable.ALLOW_COMMENTS.getValue());
        entity.setComeFrom(dictionaryCache.getValue(DictionaryKey.Keys.DEFAULT_SOURCE_OF_CONTENT.getValue()));
        entity.setDisplaydate(new Date());
        entity.setSort(BigDecimal.ZERO);
        viewModel.setAlterActionUrl(viewModel.getAddAction());
        request.setAttribute("entity", JSONUtil.toJSONString(entity));
        request.setAttribute("viewModel", this.viewModel);
        intAttributes(request, response, entity);
        return viewModel.getBasePath() + "alter";
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvCmsNews view) throws Exception {
        intAttributes(request, response, view);
    }

    @Override
    public void otherEventForViewInit(HttpServletRequest request, HttpServletResponse response, TvCmsNews view) throws Exception {
        intAttributes(request,response,view);
    }

    private void intAttributes(HttpServletRequest request, HttpServletResponse response, TvCmsNews view) throws Exception {

        request.setAttribute("sysType", SysEnumContainer.LabelType.THE_LABEL_INFORMATION.getValue());
        request.setAttribute("PublishedDesc", CmsEnumContainer.Published.values());
        List labels = labelService.selectList(new QueryWrapper()
            .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.THE_LABEL_INFORMATION.getValue())
            .eq(TsLabel.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
        );
        request.setAttribute("labels", labels);
    }


    @Override
    @PostMapping(value="add")
    @ResponseBody
    public Result add_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqCmsNews req)throws Exception{
        preInsert(request,response,req);
        UserStorage adminStorage = admAspect.getAdminByServerStorage(request);
        req.getEntity().setCreateBy(adminStorage.getId());
        return Result.success(service.insert(req),"操作成功");
    }

    /**
     * 提交修改
     * @param request
     * @param response
     * @param req
     * @return
     */
    @PostMapping(value = "edit")
    @ResponseBody
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqCmsNews req)throws Exception{
        preUpdate(request,response,req);
        if(req.getEntity().getId() != null) {
            return Result.success(service.updateByPrimary(req), "更新成功！");
        }else{
            return add_post(request,response,req);
        }
    }
}
