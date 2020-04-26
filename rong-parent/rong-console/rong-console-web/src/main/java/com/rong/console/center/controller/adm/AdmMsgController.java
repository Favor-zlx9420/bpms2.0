package com.rong.console.center.controller.adm;

import com.rong.admin.module.entity.TbAdmMsg;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.module.query.TsAdmMsg;
import com.rong.admin.module.request.TqAdmMsg;
import com.rong.admin.module.view.TvAdmMsg;
import com.rong.admin.service.AdmMsgService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.NoPermissionException;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping(RightConst.ADM_MSG_PATH)
public class AdmMsgController extends BaseController<TbAdmMsg, TqAdmMsg, TvAdmMsg, AdmMsgService> {

    @Autowired
    public AdmMsgController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.ADM_MSG_PATH)
                .build()
                , TsAdmMsg.Fields.id
        );
    }
    /**
     * 列表页面:get
     */
    @GetMapping(value = "my-list")
    public String list(HttpServletRequest request,HttpServletResponse response, QueryInfo queryInfo)throws Exception{
        request.setAttribute("viewModel", this.viewModel);
        request.setAttribute("theads", "[]");
        request.setAttribute("queryEntity", JSONUtil.toJSONString(queryInfo));
        request.setAttribute("pageList", JSONUtil.toJSONString(dataGrid(request,response,queryInfo)));
        request.setAttribute("MsgStateDesc", CommonEnumContainer.MsgState.values());
        return viewModel.getBasePath()+"list";
    }
    @Override
    @PostMapping(value = "dataGrid")
    @ResponseBody
    public Result<TvPageList<TvAdmMsg>> dataGrid(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo)throws Exception{
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        //queryEntity.getEntity().setToAdmUserId(storage.getId());
        return super.dataGrid(request,response,queryInfo);
    }
    @Override
    public void otherEventForViewInit(HttpServletRequest request, HttpServletResponse response, TvAdmMsg view)throws Exception{
        UserStorage storage = admAspect.getAdminByServerStorage(request);
        if(!CommonUtil.isEqual(view.getToAdmUserId(),storage.getId())){
            throw new NoPermissionException("您无权查看该条信息！");
        }
        if(CommonUtil.isEqual(view.getState(), CommonEnumContainer.MsgState.Unread.getValue())){
            TvAdmMsg upMsg = new TvAdmMsg();
            upMsg.setState(CommonEnumContainer.MsgState.HaveRead.getValue());
            upMsg.setId(view.getId());
            upMsg.setUpdateDate(new Date());
            service.updateSelectiveByPrimaryKey(upMsg);
        }
    }
}
