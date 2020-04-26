package com.rong.console.center.controller.adm;

import com.rong.admin.consts.AdmConstValue;
import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.query.TsAdmUser;
import com.rong.admin.module.request.TqAdmUser;
import com.rong.admin.module.view.TvAdmUser;
import com.rong.admin.service.AdmUserService;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.module.TreeNode;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.DateUtil;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbDictionary;
import com.rong.sys.module.query.TsDictionary;
import com.rong.sys.module.view.TvDictionary;
import com.rong.sys.service.DictionaryService;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(RightConst.ADM_USER_PATH)
public class AdmUserController extends BaseController<TbAdmUser, TqAdmUser, TvAdmUser, AdmUserService> {

    private final DictionaryCache dictionaryCache;
    private final DictionaryService dictionaryService;

    @Autowired
    public AdmUserController(DictionaryCache dictionaryCache, DictionaryService dictionaryService) {
        super(new ViewModel.Builder()
                .basePath(RightConst.ADM_USER_PATH)
                .build()
            , TsAdmUser.Fields.id
        );
        this.dictionaryCache = dictionaryCache;
        this.dictionaryService = dictionaryService;
    }
    //.neq(EnumFieldAssociateCol.valueOf(TsAdmUser.Fields.id, ConstValue.MAIN_ALIAS), AdmConstValue.SUP_ADMIN_ID)
    @Override
    @PostMapping(value = "dataGrid")
    @ResponseBody
    public Result<TvPageList<TvAdmUser>> dataGrid(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo)throws Exception{
        queryInfo.addCondition(FieldValue.fromCondition("e.id.neq", AdmConstValue.SUP_ADMIN_ID));
        return super.dataGrid(request,response,queryInfo);
    }
    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvAdmUser be)throws Exception{
        List<TreeNode> trees=admColumnService.listPermissionRecursiveColumnsByPid(admAspect.getAdminByServerStorage(request),null, "65,163", 0);
        request.setAttribute("tree", JSONUtil.toJSONString(trees));
        request.setAttribute("admNameMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_BACKGROUND_USERNAME.getValue()));
        request.setAttribute("admNameMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.THE_LONGEST_LENGTH_OF_A_BACKGROUND_USERNAME.getValue()));
        request.setAttribute("admPwdMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
        request.setAttribute("admPwdMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.MAXIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
        be.setPasswordExpiration(DateUtil.addDate(new Date(),100))
        .setState(CommonEnumContainer.State.NORMAL.getValue());
        ;
    }
    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvAdmUser view)throws Exception{
        List<TreeNode> trees=admColumnService.listPermissionRecursiveColumnsByPid(admAspect.getAdminByServerStorage(request),null, view.getPermissionStr(), 0);
        request.setAttribute("tree", JSONUtil.toJSONString(trees));
        request.setAttribute("admNameMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_BACKGROUND_USERNAME.getValue()));
        request.setAttribute("admNameMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.THE_LONGEST_LENGTH_OF_A_BACKGROUND_USERNAME.getValue()));
        request.setAttribute("admPwdMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
        request.setAttribute("admPwdMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.MAXIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
    }
    @Override
    public void otherEventForViewInit(HttpServletRequest request, HttpServletResponse response, TvAdmUser view)throws Exception{
        List<TreeNode> trees=admColumnService.listPermissionRecursiveColumnsByPid(admAspect.getAdminByServerStorage(request),null, view.getPermissionStr(), 0);
        request.setAttribute("tree", JSONUtil.toJSONString(trees));
    }


    @GetMapping("config")
    public String config(HttpServletRequest request,HttpServletResponse response,long cid)throws Exception{
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("entity","{}");
        List<TvDictionary> dictionaries = dictionaryService.selectViewList(
                new MultiTableQueryWrapper()
                    .eq(CompareAlias.valueOf(TsDictionary.Fields.type),cid)
        );
        request.setAttribute("dictionaries", JSONUtil.toJSONString(dictionaries,new String[]{"id","key","value","valueType","description"}));
        return viewModel.getBasePath()+"config";
    }
    @PostMapping("config")
    @ResponseBody
    public Result config(List<TbDictionary> dictionaries){
        if(CommonUtil.isEmpty(dictionaries)){
            throw new NoExistsException("没有配置要更新");
        }

        //dictionaryService.updateByBeans(query);
        return Result.success();
    }

}
