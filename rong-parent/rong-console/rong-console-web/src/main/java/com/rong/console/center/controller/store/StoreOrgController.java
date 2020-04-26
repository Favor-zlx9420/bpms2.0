package com.rong.console.center.controller.store;

import com.alibaba.fastjson.JSON;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbOrgManage;
import com.rong.fundmanage.module.request.TqOrgManage;
import com.rong.fundmanage.service.OrgManageService;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.service.MemBaseService;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.query.TsDirectStoreOrg;
import com.rong.store.module.query.TsDirectStoreOrgLabel;
import com.rong.store.module.request.TqDirectStoreOrg;
import com.rong.store.module.view.TvDirectStoreOrg;
import com.rong.store.service.DirectStoreOrgLabelService;
import com.rong.store.service.DirectStoreOrgService;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.STORE_ORG_PATH)
public class StoreOrgController extends BaseController<TbDirectStoreOrg, TqDirectStoreOrg, TvDirectStoreOrg, DirectStoreOrgService> {
    @Autowired
    private OrgManageService orgManageService;
    @Autowired
    private MemBaseService memBaseService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private DirectStoreOrgLabelService directStoreOrgLabelService;
    private String bindUserAction;
    @Autowired
    public StoreOrgController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.STORE_ORG_PATH)
                .build(),
                TsDirectStoreOrg.Fields.id
                );
        bindUserAction = viewModel.getBasePath() + "bind-user";
    }

    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvDirectStoreOrg be) throws Exception {
        super.otherEventForAddInit(request, response, be);
        attributes(request);
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvDirectStoreOrg view) throws Exception {
        super.otherEventForEditInit(request, response, view);
        view.setOrgLabels(directStoreOrgLabelService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsDirectStoreOrgLabel.Fields.partyId, view.getPartyId())
                        .eq(TsDirectStoreOrgLabel.Fields.deltag, false)

        ));
        attributes(request);
    }
    private void attributes(HttpServletRequest request){
        request.setAttribute("labelList", JSON.toJSONString(labelService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.PRODUCT_LABEL_OF_DIRECT_SALE_STORE.getValue())
                        .eq(TsLabel.Fields.deltag, false)
        )));
    }
    @Override
    protected void preInsert(HttpServletRequest request, HttpServletResponse response, TqDirectStoreOrg req){
        TbDirectStoreOrg entity = req.getEntity();
        if(entity.getPartyId() != null && entity.getUserId() != null){
            TbDirectStoreOrg storeOrg = service.selectOne(
                    new QueryWrapper()
                            .select(TsDirectStoreOrg.Fields.id)
                            .eq(TsDirectStoreOrg.Fields.partyId,entity.getPartyId())
            );
            if(null != storeOrg){
                throw new DuplicateDataException("该机构已存在于直营店中！如果已删除，请使用恢复功能恢复");
            }
            TbDirectStoreOrg user = service.selectOne(
                    new QueryWrapper()
                            .select(TsDirectStoreOrg.Fields.id)
                            .eq(TsDirectStoreOrg.Fields.userId,entity.getUserId())
            );
            if(null != user){
                throw new DuplicateDataException("该用户已经是其他直营店用户，请选择另外的用户");
            }
            //null 则先插入一条数据
            TbOrgManage orgManage = new TbOrgManage()
                     .setHotSearch(CommonEnumContainer.YesOrNo.DENY.getValue())
                     .setVisible(CommonEnumContainer.YesOrNo.RIGHT.getValue())
                    .setRecommend(CommonEnumContainer.YesOrNo.DENY.getValue())
                    .setType(entity.getType())
                    .setPartyId(entity.getPartyId())
                    .setId(SnowflakeIdWorker.create().nextId())
            ;
            orgManageService.insert(new TqOrgManage().setEntity(orgManage));
            entity.setPartyId(entity.getPartyId());
        }

    }

    @GetMapping(value="bind-user")
    public String bindUser(HttpServletRequest request,HttpServletResponse response,Long userId)throws Exception{
        TbMemBase user;
        if(null == userId){
            user = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(userId));
        }else{
            user = new TbMemBase();
        }

        request.setAttribute("entity", JSONUtil.toJSONString(user));
        viewModel.setAlterActionUrl(viewModel.getBasePath()+"edit");
        request.setAttribute("viewModel", this.viewModel);
        return viewModel.getBasePath()+"bind-user";
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("bindUserAction",bindUserAction);
    }
}
