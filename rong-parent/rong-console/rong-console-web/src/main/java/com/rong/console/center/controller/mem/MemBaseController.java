package com.rong.console.center.controller.mem;

import com.rong.admin.module.view.TvAdmFields;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.query.TsUserAccount;
import com.rong.member.module.request.TqMemBase;
import com.rong.member.module.view.TvMemBase;
import com.rong.member.service.MemBaseService;
import com.rong.member.service.MemCompanyCreditfileService;
import com.rong.member.service.MemCompanyUserinfoService;
import com.rong.member.service.MemPersonalCreditfileService;
import com.rong.member.service.MemPersonalUserinfoService;
import com.rong.member.service.UserAccountService;
import com.rong.sys.module.entity.TbLabel;
import com.rong.user.module.entity.TbInvestorQualified;
import com.rong.user.module.entity.TbUserLabel;
import com.rong.user.module.entity.TbUserVipEnd;
import com.rong.user.module.query.TsUserLabel;
import com.rong.user.module.query.TsUserVipEnd;
import com.rong.user.module.request.TqUserLabel;
import com.rong.user.module.request.TqUserVipEnd;
import com.rong.user.service.UserLabelService;
import com.rong.user.service.UserVipEndService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.entity.Element;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(RightConst.MEM_BASE_PATH)
public class MemBaseController extends BaseController<TbMemBase, TqMemBase, TvMemBase, MemBaseService> {
    @Autowired
    private UserVipEndService userVipEndService;
    @Autowired
    private UserLabelService userLabelService;
    private final DictionaryCache dictionaryCache;
    private final MemPersonalCreditfileService memPersonalCreditfileService;
    private final MemCompanyCreditfileService memCompanyCreditfileService;
    private final MemPersonalUserinfoService memPersonalUserinfoService;
    private final MemCompanyUserinfoService memCompanyUserinfoService;
    private final UserAccountService userAccountService;
    @Autowired
    public MemBaseController(DictionaryCache dictionaryCache,
                             MemPersonalCreditfileService memPersonalCreditfileService,
                             MemCompanyCreditfileService memCompanyCreditfileService,
                             MemPersonalUserinfoService memPersonalUserinfoService,
                             MemCompanyUserinfoService memCompanyUserinfoService,
                             UserAccountService userAccountService
    ) {
        super(new ViewModel.Builder()
                .basePath(RightConst.MEM_BASE_PATH)
                .build(),
                TsMemBase.Fields.id
        );
        this.dictionaryCache = dictionaryCache;
        this.memPersonalCreditfileService = memPersonalCreditfileService;
        this.memCompanyCreditfileService = memCompanyCreditfileService;
        this.memPersonalUserinfoService = memPersonalUserinfoService;
        this.memCompanyUserinfoService = memCompanyUserinfoService;
        this.userAccountService = userAccountService;
    }

    @Override
    protected MultiTableQueryWrapper multiTableQueryWrapper(HttpServletRequest request) {
        MultiTableQueryWrapper queryWrapper = super.multiTableQueryWrapper(request);
        queryWrapper
                .selectAllFiels(true)
                .select("l.id labelId,l.name labelName,rm.realName recommendUser")
                .select("iq.qualifiedResult,iq.updateDate qualifiedDate")
                .select0(
                        SelectAlias.valueOf("'--' FinancialPreferences",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbUserLabel.class,"ul"), ul->
                        ul.eqc(CompareAlias.valueOf("ul.userId"),CompareAlias.valueOf("e.id"))
                                .eq(CompareAlias.valueOf("ul.deltag"),false)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbLabel.class,"l"), l->
                        l.eqc(CompareAlias.valueOf("l.id"),CompareAlias.valueOf("ul.labelId"))
                                .eq(CompareAlias.valueOf("ul.deltag"),false)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"rm"), rm->
                        rm.eqc(CompareAlias.valueOf("rm.id"),CompareAlias.valueOf("e.recommendMemberId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbInvestorQualified.class,"iq"), rm->
                        rm.eqc(CompareAlias.valueOf("iq.userId"),CompareAlias.valueOf("e.id"))
                )
        ;
        String recommendUser = request.getParameter("recommendUser");
        if(StringUtil.isNotEmpty(recommendUser)){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("rm.phone"),recommendUser)
                    .like(CompareAlias.valueOf("rm.userName"),recommendUser)
                    .like(CompareAlias.valueOf("rm.realName"),recommendUser)
                    .like(CompareAlias.valueOf("rm.nickName"),recommendUser)
            );
        }
        String qualified = request.getParameter("qualified");
        if(StringUtil.isNotEmpty(qualified)){
            queryWrapper.eq(CompareAlias.valueOf("iq.deltag"),false);
        }
        return queryWrapper;
    }

    @Override
    public String view(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
        TvMemBase entity = service.selectOneView(multiTableQueryWrapper(request).eq(CompareAlias.valueOf("e.id"),id));
        otherEventForViewInit(request,response,entity);
        request.setAttribute("entity", JSONUtil.toJSONString(entity));
        request.setAttribute("viewModel", this.viewModel);
        return viewModel.getViewAction();
    }

    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvMemBase be)throws Exception{
        setAttrs(request);
        be
                .setState(CommonEnumContainer.State.NORMAL.getValue());
    }

    @Override
    protected void preInsert(HttpServletRequest request, HttpServletResponse response, TqMemBase req) {
        super.preInsert(request, response, req);
        req.getEntity().setRegFrom(MemEnumContainer.RegFrom.后台添加.getValue());
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvMemBase view)throws Exception{
        setAttrs(request);
        // set userinfo
        view.setPersonalUserinfo(memPersonalUserinfoService.selectItemByPrimaryKey(IdWrapper.valueOf(view.getId())));
        view.setCompanyUserinfo(memCompanyUserinfoService.selectItemByPrimaryKey(IdWrapper.valueOf(view.getId())));
    }
    private void setAttrs(HttpServletRequest request){
        Map<String,Object> attrMap = new HashMap();
        attrMap.put("memNameMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_MEMBER_USERNAME.getValue()));
        attrMap.put("memNameMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.MAXIMUM_LENGTH_OF_MEMBER_USERNAME.getValue()));
        attrMap.put("memPwdMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_MEMBER_PASSWORD.getValue()));
        attrMap.put("memPwdMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.MAXIMUM_LENGTH_OF_MEMBER_PASSWORD.getValue()));
        attrMap.put("memNickMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MEMBER_NICKNAME_MINIMUM_LENGTH.getValue()));
        attrMap.put("memNickMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.MEMBER_NICKNAME_LONGEST_LENGTH.getValue()));
        request.setAttribute("attr", attrMap);
        request.setAttribute("IdTypeDesc", CommonEnumContainer.IdType.values());
        request.setAttribute("GenderDesc", CommonEnumContainer.Gender.values());
        request.setAttribute("MemTypeDesc", MemEnumContainer.MemCate.values());
        request.setAttribute("RegFromDesc", MemEnumContainer.RegFrom.values());

    }
    @Override
    public void otherEventForViewInit(HttpServletRequest request, HttpServletResponse response, TvMemBase view)throws Exception{
        if(CommonUtil.isEqual(view.getType(), MemEnumContainer.MemCate.个人用户.getValue())){
            view.setPersonalCreditfile(memPersonalCreditfileService.selectItemByPrimaryKey(IdWrapper.valueOf(view.getId())));
            view.setPersonalUserinfo(memPersonalUserinfoService.selectItemByPrimaryKey(IdWrapper.valueOf(view.getId())));
        }else if(CommonUtil.isEqual(view.getType(), MemEnumContainer.MemCate.企业用户.getValue())){
            view.setCompanyCreditfile(memCompanyCreditfileService.selectItemByPrimaryKey(IdWrapper.valueOf(view.getId())));
            view.setCompanyUserinfo(memCompanyUserinfoService.selectItemByPrimaryKey(IdWrapper.valueOf(view.getId())));
        }
        if(null != view.getRecommendMemberId()){
            TbMemBase recommender = service.selectItemByPrimaryKey(IdWrapper.valueOf(view.getRecommendMemberId()));
            if(null != recommender){
                view.setRecommendUser(recommender.getRealName());
                view.setRecommendUserCode(recommender.getRecommendCode());
            }
        }

        //获得资金日志显示的列
        Long columnId = admColumnService.hashAuthSingle(RightConst.FINANCE_ACCOUNT_FLOW_PATH+"list");
        List<TvAdmFields> theads=admFieldsService.getFieldShowByColumnId(columnId);
        request.setAttribute("accountFlowTheads", JSONUtil.toJSONString(theads,super.PRO_FILTER_ITEMS));
        request.setAttribute("accountDataPath",RightConst.FINANCE_ACCOUNT_FLOW_PATH + "dataGrid");

        //获取充值记录显示的列
        columnId = admColumnService.hashAuthSingle(RightConst.FINANCE_RECHARGE_PATH+"list");
        theads=admFieldsService.getFieldShowByColumnId(columnId);
        request.setAttribute("rechargeTheads", JSONUtil.toJSONString(theads,super.PRO_FILTER_ITEMS));
        request.setAttribute("rechargeDataPath",RightConst.FINANCE_RECHARGE_PATH + "dataGrid");

        //获取提现日志显示的列
        columnId = admColumnService.hashAuthSingle(RightConst.FINANCE_WITHDRAW_PATH+"list");
        theads=admFieldsService.getFieldShowByColumnId(columnId);
        request.setAttribute("withdrawTheads", JSONUtil.toJSONString(theads,super.PRO_FILTER_ITEMS));
        request.setAttribute("withdrawDataPath",RightConst.FINANCE_WITHDRAW_PATH + "dataGrid");

    }

    /**
     * 获取用户账户
     * @param request 1
     * @return 2
     */
    @GetMapping(value = "user-account")
    @ResponseBody
    public Result userAccount(HttpServletRequest request, Long userId){
        List userAccounts = userAccountService.selectList(new QueryWrapper()
            .eq(TsUserAccount.Fields.userId,userId)
        );
        return Result.success(userAccounts);
    }

    /**
     * 最近注册用户列表
     * @param request 1
     * @return 2
     */
    @GetMapping(value = "lately-register")
    public String latelyRegister(HttpServletRequest request, TsMemBase queryEntity){
        TvPageList<TvMemBase> pageList = service.selectPageList(service.getMultiCommonWrapper()
            .page(new PageInfo())
            .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf(TsMemBase.Fields.id, ConstValue.MAIN_ALIAS)))
        );

        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("theads","[]");
        request.setAttribute("pageList", JSONUtil.toJSONString(pageList));
        return viewModel.getBasePath()+"lately-register";
    }



    /**
     * 查询
     * @param queryInfo
     * @return
     */
    @RequestMapping("manager-list")
    @ResponseBody
    public Result managerList(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo){
        MultiTableQueryWrapper queryWrapper = getMultiQueryInfo(request,queryInfo);
        queryWrapper.selectAllFiels(false)
                .select0(
                        SelectAlias.valueOf(TsMemBase.Fields.id,ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemBase.Fields.userName,ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemBase.Fields.position,ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemBase.Fields.realName,ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemBase.Fields.type,ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemBase.Fields.phone,ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMemBase.Fields.level,ConstValue.MAIN_ALIAS)
                )
                //.eq(CompareAlias.valueOf(TsMemBase.Fields.type,ConstValue.MAIN_ALIAS), MemEnumContainer.MemType.基金经理.getValue())
                ;
        wrapQuery(queryWrapper,request);
        return Result.success(service.selectPageList(queryWrapper));
    }

    /**
     * 用户等级操作
     * @param request 1
     * @return 2
     */
    @GetMapping(value = "level-opera")
    public String levelOpera(HttpServletRequest request,Long userId){
        //userInfo
        TbMemBase user = service.selectItemByPrimaryKey(IdWrapper.valueOf(userId));
        //currentLevelInfo
        TbUserVipEnd currentVip = userVipEndService.selectOne(new QueryWrapper()
                .eq(TsUserVipEnd.Fields.userId,userId)
                .eq(TsUserVipEnd.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                );
        if(null == currentVip){
            currentVip = new TbUserVipEnd();
            currentVip.setUserId(userId);
            currentVip.setLevel(MemEnumContainer.MemLevel.无.getValue());
        }
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("user", JSONUtil.toJSONString(user));
        request.setAttribute("entity", JSONUtil.toJSONString(currentVip));
        return viewModel.getBasePath()+"level-opera";
    }

    /**
     * 保存用户等级
     * @param req
     * @return
     */
    @RequestMapping("level-opera")
    @ResponseBody
    public Result saveUserLevel(HttpServletRequest request, HttpServletResponse response, @RequestBody TqUserVipEnd req){
        //如果id == null、新增一条记录
        if(req.getEntity().getId() == null){
            if(CommonUtil.isEqual(MemEnumContainer.MemLevel.无.getValue(),req.getEntity().getLevel())){
                return Result.success();
            }
            if(new Date().compareTo(req.getEntity().getEndDate()) > 0){
                return Result.miss(CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS,"截止时间不能小于今天");
            }
            //查询是否已有
            TbUserVipEnd has = userVipEndService.selectOne(
                    new QueryWrapper()
                            .eq(TsUserVipEnd.Fields.userId,req.getEntity().getUserId())
                            .eq(TsUserVipEnd.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
            );
            if(has != null){
                throw new DuplicateDataException("已有等级记录");
            }
            userVipEndService.insert(req);
            service.updateSelectItem(new UpdateWrapper()
                .update(
                        Elements.valueOf(TsMemBase.Fields.level,req.getEntity().getLevel()),
                        Elements.valueOf(TsMemBase.Fields.updateDate,new Date())
                )
                .eq(TsMemBase.Fields.id,req.getEntity().getUserId())
            );
        }else{
            //如果id ！= null、修改

            //如果时间过期了，则将用户等级下刷
            List<Element> ups = new ArrayList<>();
            if(req.getEntity().getLevel() != null) {
                ups.add(Elements.valueOf(TsMemBase.Fields.updateDate, new Date()));
            }
            ups.add(Elements.valueOf(TsMemBase.Fields.level,req.getEntity().getLevel()));
            if( new Date().compareTo(req.getEntity().getEndDate()) > 0){
                ups.add(Elements.valueOf(TsMemBase.Fields.level, MemEnumContainer.MemLevel.无.getValue()));
                req.getEntity().setDeltag(CommonEnumContainer.Deltag.DELETED.getValue());
            }
            service.updateSelectItem(new UpdateWrapper()
                    .update(ups)
                    .eq(TsMemBase.Fields.id,req.getEntity().getUserId())
            );
            userVipEndService.updateByPrimary(req);
        }


        return Result.success();
    }

    /**
     * 用户标签操作
     * @param request 1
     * @return 2
     */
    @GetMapping(value = "label-opera")
    public String labelOpera(HttpServletRequest request,Long userId){
        TbUserLabel userLabel = userLabelService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserLabel.Fields.userId,userId)
        );
        if(null == userLabel) {
            userLabel = new TbUserLabel();
            userLabel.setUserId(userId);
            userLabel.setRecommend(false);
        }
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("entity", JSONUtil.toJSONString(userLabel));
        return viewModel.getBasePath()+"label-opera";
    }

    /**
     * 保存用户标签
     * @param req
     * @return
     */
    @RequestMapping("label-opera")
    @ResponseBody
    public Result saveUserLabel(HttpServletRequest request, HttpServletResponse response, @RequestBody TqUserLabel req){

        //如果id == null、新增一条记录
        TbUserLabel entity = req.getEntity();
        if(entity.getId() == null){
            //查询是否已有
            TbUserLabel has = userLabelService.selectOne(
                    WrapperFactory.queryWrapper().eq(TsUserLabel.Fields.userId,entity.getUserId())
            );
            if(has != null){
                throw new DuplicateDataException("已有等级记录");
            }
            userLabelService.insert(req);
        }else{
            //如果id ！= null、修改
            userLabelService.updateByPrimary(req);
        }
        return Result.success();
    }



    @GetMapping("qualified")
    public String qualified(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo)throws Exception{

        //可能继承类需要做一些额外的 事情
        otherEventForListInit(request,response);
        //获得显示的列
        Long columnId = admColumnService.hashAuthSingle(viewModel.getBasePath()+"qualified");
        List<TvAdmFields> theads=admFieldsService.getFieldShowByColumnId(columnId);
        request.setAttribute("theads", JSONUtil.toJSONString(theads,PRO_FILTER_ITEMS));
        request.setAttribute("queryInfo", JSONUtil.toJSONString(queryInfo));
        request.setAttribute("hasAddPermission",admAspect.hasPermission(viewModel.getAddAction(),request));
        request.setAttribute("hasEditPermission",admAspect.hasPermission(viewModel.getEditAction(),request));
        request.setAttribute("hasViewPermission",admAspect.hasPermission(viewModel.getViewAction(),request));
        request.setAttribute("hasDelPermission",admAspect.hasPermission(viewModel.getDelOrRecAction(),request));
        request.setAttribute("hasDataGridPermission",admAspect.hasPermission(viewModel.getDataGridAction(),request));
        ViewModel vm = new ViewModel.Builder()
                .basePath(RightConst.MEM_BASE_PATH)
                .build();
                vm.setListAction(vm.getBasePath() + "qualified");

        request.setAttribute("viewModel", vm);
        return "base/list";
    }


}
