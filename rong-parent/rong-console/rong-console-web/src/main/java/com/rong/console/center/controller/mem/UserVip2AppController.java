package com.rong.console.center.controller.mem;

import com.rong.admin.module.foreign.UserStorage;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.service.MemBaseService;
import com.rong.user.module.entity.TbUserVip2Apply;
import com.rong.user.module.entity.TbUserVipEnd;
import com.rong.user.module.query.TsUserVip2Apply;
import com.rong.user.module.request.TqUserVip2Apply;
import com.rong.user.module.request.TqUserVipEnd;
import com.rong.user.module.view.TvUserVip2Apply;
import com.rong.user.service.UserVip2ApplyService;
import com.rong.user.service.UserVipEndService;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping(RightConst.MEM_VPI2_PATH)
public class UserVip2AppController extends BaseController<TbUserVip2Apply, TqUserVip2Apply, TvUserVip2Apply, UserVip2ApplyService> {
    @Autowired
    private UserVipEndService userVipEndService;
    @Autowired
    private MemBaseService memBaseService;
    private String auditAction;
    @Autowired
    public UserVip2AppController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.MEM_VPI2_PATH)
                .build(),
                TsUserVip2Apply.Fields.id
                );
        auditAction = viewModel.getBasePath()+"audit";
    }


    @Override
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("auditAction",auditAction);
        request.setAttribute("hasAuditPermission",admAspect.hasPermission(auditAction,request));
    }

    /**
     * 进入审核页面
     * @param request
     * @param response
     * @param id
     * @return
     */
    @GetMapping(value="audit")
    public String audit(HttpServletRequest request, HttpServletResponse response, Long id)throws Exception{
        TvUserVip2Apply view = service.selectOneView(service.getMultiCommonWrapper()
                .eq(CompareAlias.valueOf("e.id"),id)
        );
        view.setAuditResult(CommonEnumContainer.CustomerAuditState.NOT_APPROVED.getValue());
        request.setAttribute("entity", JSONUtil.toJSONString(view));
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("auditAction",auditAction);
        request.setAttribute("hasAuditPermission",admAspect.hasPermission(auditAction,request));
        return viewModel.getBasePath()+"audit";
    }
    /**
     * 审核：
     * @param request
     * @param response
     * @param req
     * @return
     */
    @PostMapping(value="audit")
    @ResponseBody
    @Transactional// todo in service
    public Result audit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqUserVip2Apply req)throws Exception{
        UserStorage loginUser = admAspect.getAdminByServerStorage(request);
        synchronized (getClass()) {
            TbUserVip2Apply entity = req.getEntity();
            //审核记录
            entity.setAuditUserId(loginUser.getId());
            service.updateByPrimary(req);
            //审核通过，更新用户vip，添加过期记录
            Date now = entity.getUpdateDate();
            if(CommonUtil.isEqual(entity.getAuditResult(), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())){
                memBaseService.updateSelectItem(
                        WrapperFactory.updateWrapper()
                        .update(
                                Elements.valueOf(TsMemBase.Fields.level, MemEnumContainer.MemLevel.蓝色VIP.getValue()),
                                Elements.valueOf(TsMemBase.Fields.updateDate,now)
                                )
                        .eq(TsMemBase.Fields.id,entity.getAppUserId())
                );
                TbUserVipEnd vipEnd = new TbUserVipEnd();
                vipEnd.setUserId(entity.getAppUserId());
                vipEnd.setLevel(MemEnumContainer.MemLevel.蓝色VIP.getValue());
                vipEnd.setBeginDate(now);
                vipEnd.setEndDate(req.getEndDate());
                userVipEndService.insert(new TqUserVipEnd().setEntity(vipEnd));
            }
            return Result.success();
        }
    }
}
