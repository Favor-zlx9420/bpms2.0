package com.rong.console.center.controller.fundmanage;

import com.rong.common.exception.DuplicateDataException;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbOrgProxy;
import com.rong.fundmanage.module.query.TsOrgProxy;
import com.rong.fundmanage.module.request.TqOrgProxy;
import com.rong.fundmanage.module.view.TvOrgProxy;
import com.rong.fundmanage.service.OrgProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 机构管理
 */
@Controller
@RequestMapping(RightConst.ORG_PROXY_PATH)
public class OrgProxyController extends BaseController<TbOrgProxy, TqOrgProxy, TvOrgProxy, OrgProxyService> {
    @Autowired
    public OrgProxyController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ORG_PROXY_PATH)
                .build(),
                TsOrgProxy.Fields.id
        );
    }

    @Override
    @GetMapping(value="edit")
    public String edit(HttpServletRequest request, HttpServletResponse response, long id)throws Exception{
        TvOrgProxy view = new TvOrgProxy();
        view.setPartyId(id);
        view.setId(0L);
        request.setAttribute("entity", JSONUtil.toJSONString(view));
        request.setAttribute("viewModel", this.viewModel);
        viewModel.setAlterActionUrl(viewModel.getBasePath()+"edit");
        return viewModel.getBasePath()+"alter";
    }

    @Override
    @PostMapping(value = "edit")
    @ResponseBody
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqOrgProxy req)throws Exception{
        TbOrgProxy entity = req.getEntity();
        //查询用户是否已经绑定其他基金经理了。
        if (service.selectCount(WrapperFactory.queryWrapper().eq(TsOrgProxy.Fields.userId, entity.getUserId())) > 0) {
            throw new DuplicateDataException("系统用户已经绑定过其他人，请选择其他系统用户");
        }
        TbOrgProxy manager = service.selectOne(
                WrapperFactory.queryWrapper().eq(TsOrgProxy.Fields.partyId,entity.getPartyId())
        );
        //该用户没有绑定过
        if(manager == null){
            service.insert(req);
        }else{
            service.updateByPrimary(req);
        }
        return Result.success(null,"更新成功！");
    }
}
