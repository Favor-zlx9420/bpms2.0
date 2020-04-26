package com.rong.console.center.controller.store;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.exception.DuplicateDataException;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.query.TsDirectStoreUser;
import com.rong.store.module.request.TqDirectStoreUser;
import com.rong.store.module.view.TvDirectStoreUser;
import com.rong.store.service.DirectStoreUserService;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.STORE_MANAGER_PATH)
public class StoreManagerController extends BaseController<TbDirectStoreUser, TqDirectStoreUser, TvDirectStoreUser, DirectStoreUserService> {
    @Autowired
    public StoreManagerController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.STORE_MANAGER_PATH)
                .build(),
                TsDirectStoreUser.Fields.id
                );
    }
    @Override
    protected void preInsert(HttpServletRequest request, HttpServletResponse response, TqDirectStoreUser req){
        TbDirectStoreUser entity = req.getEntity();
        if(entity.getUserId() != null){
            TbDirectStoreUser storeOrg = service.selectOne(
                    new QueryWrapper()
                            .select(TsDirectStoreUser.Fields.id)
                            .eq(TsDirectStoreUser.Fields.userId,entity.getUserId())
            );
            if(null != storeOrg){
                throw new DuplicateDataException("该用户已存在于直营店中！如果已删除，请使用恢复功能恢复");
            }
            return;
        }
        throw new CustomerException("用户id必须上传", CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE);
    }
}
