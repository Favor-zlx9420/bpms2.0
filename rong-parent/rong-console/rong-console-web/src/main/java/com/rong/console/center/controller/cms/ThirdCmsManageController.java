package com.rong.console.center.controller.cms;

import com.rong.cms.module.entity.TbThirdNewsManage;
import com.rong.cms.module.query.TsThirdNewsManage;
import com.rong.cms.module.request.TqThirdNewsManage;
import com.rong.cms.service.ThirdNewsManageService;
import com.rong.common.module.Result;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import com.rong.tong.pfunds.module.query.TsVnewsContentV1;
import com.rong.tong.pfunds.module.request.TqVnewsContentV1;
import com.rong.tong.pfunds.module.view.TvVnewsContentV1;
import com.rong.tong.pfunds.service.VnewsContentV1Service;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Controller
@RequestMapping(RightConst.CMS_THIRD_MANAGE_PATH)
public class ThirdCmsManageController extends FundsBaseController<TbVnewsContentV1, TqVnewsContentV1, TvVnewsContentV1, VnewsContentV1Service> {
    @Autowired
    private ThirdNewsManageService thirdNewsManageService;
    @Autowired
    public ThirdCmsManageController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.CMS_THIRD_MANAGE_PATH)
                .build(),
                TsVnewsContentV1.Fields.newsId
        );
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("hasEditPermission",false);
    }
    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request){
        queryWrapper
                .selectAllFiels(false)
                .forceIndex("news_id")
                .select("m.recommend,m.sort,m.id,m.hotSearch,m.visible")
                .select("e.newsId,e.newsTitle,e.newsOriginSource,e.effectiveTime")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbThirdNewsManage.class,"m"), x->
                        x.eqc(CompareAlias.valueOf(TsThirdNewsManage.Fields.newsId,"m"),
                                CompareAlias.valueOf(TsVnewsContentV1.Fields.newsId,ConstValue.MAIN_ALIAS))
                        )
        ;
        String visibleSelector = request.getParameter("visibleSelector");
        if("true".equals(visibleSelector)){
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsThirdNewsManage.Fields.visible,"m"))
                    .eq(CompareAlias.valueOf(TsThirdNewsManage.Fields.visible,"m"),true)
                    );
        } else if ("false".equals(visibleSelector)) {
            queryWrapper.eq(CompareAlias.valueOf(TsThirdNewsManage.Fields.visible,"m"),false);
        }
        //
        String recommendSelector = request.getParameter("recommendSelector");
        if("true".equals(recommendSelector)){
            queryWrapper.eq(CompareAlias.valueOf(TsThirdNewsManage.Fields.recommend,"m"),true);
        } else if ("false".equals(recommendSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsThirdNewsManage.Fields.recommend,"m"))
                    .eq(CompareAlias.valueOf(TsThirdNewsManage.Fields.recommend,"m"),false)
            );
        }
        String hotSearchSelector = request.getParameter("hotSearchSelector");
        if("true".equals(hotSearchSelector)){
            queryWrapper.eq(CompareAlias.valueOf(TsThirdNewsManage.Fields.hotSearch,"m"),true);
        } else if ("false".equals(hotSearchSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsThirdNewsManage.Fields.hotSearch,"m"))
                    .eq(CompareAlias.valueOf(TsThirdNewsManage.Fields.hotSearch,"m"),false)
            );
        }
    }

    @Override
    @PostMapping(value = "edit")
    @ResponseBody
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqVnewsContentV1 req)throws Exception{
        Long newsId = req.getEntity().getNewsId().longValue();
        Boolean recommend = req.getRecommend();
        Boolean visible = req.getVisible();
        Boolean hotSearch = req.getHotSearch();
        BigDecimal sort = req.getSort();
        TbThirdNewsManage manager = thirdNewsManageService.selectOne(
                new QueryWrapper().eq(TsThirdNewsManage.Fields.newsId,newsId)
        );
        if(manager == null){
            //add
            manager = new TbThirdNewsManage()
                    .setNewsId(newsId)
                    .setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setVisible(visible);
            thirdNewsManageService.insert(new TqThirdNewsManage().setEntity(manager));
        }else{
            //edit
            TbThirdNewsManage up = new TbThirdNewsManage();
            up.setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setVisible(visible)
                    .setSort(sort)
                    .setId(manager.getId())
            ;
            thirdNewsManageService.updateByPrimary(new TqThirdNewsManage().setEntity(up));
        }
        return Result.success(null,"更新成功！");
    }

}
