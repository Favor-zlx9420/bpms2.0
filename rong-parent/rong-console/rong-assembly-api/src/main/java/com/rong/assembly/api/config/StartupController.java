package com.rong.assembly.api.config;

import com.rong.auth.module.entity.TbAuthRole;
import com.rong.auth.module.request.TqAuthRole;
import com.rong.auth.module.view.TvAuthRole;
import com.rong.auth.service.AuthRoleService;
import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableScheduling
@ComponentScan({"com.rong.assembly.api.config","com.rong.assembly.api.dual.excutor"})
@Component
@Slf4j
public class StartupController implements CommandLineRunner {
    private final CommonServiceCache commonServiceCache;
    private final AuthRoleService authRoleService;

    @Autowired
    public StartupController(CommonServiceCache commonServiceCache, AuthRoleService authRoleService) {
        this.commonServiceCache = commonServiceCache;
        this.authRoleService = authRoleService;
    }
    @Value("${spring.application.name}")
    String applicationName;
    @Value("${spring.profiles.active}")
    String projectEnv;

    @Override
    public void run(String... args){
        try {
            log.info(" >>>>>>>>>>>>> 服务["+applicationName+"]，环境["+projectEnv+"]启动中，正在加载数据等操作 <<<<<<<<<<<<< ");
            loadRoleResources();
            //testFundsInfo();
            log.info(" >>>>>>>>>>>>> 初始化工作完毕 <<<<<<<<<<<<< ");
        }catch (Exception e){
            Result result = CommonUtil.getResultByThrowable(e);
            log.error(" !!!!!!!!!!!!! 初始化工作错误,详细信息："+ JSONUtil.toJSONString(result.getMessage())+" !!!!!!!!!!!!! ");
        }
    }

    /*
     * 加载角色拥有的资源
     * */
    private void loadRoleResources(){
        CommonServiceCache roleResourcesCache = commonServiceCache.getInstance(DictionaryKey.Keys.RESOURCES_OWNED_BY_THE_FRONT_END_ROLE, CacheSerializableDelegate.jsonSeriaze());
        roleResourcesCache.removeFromServer(DictionaryKey.Keys.RESOURCES_OWNED_BY_THE_FRONT_END_ROLE.getValue());
        TqAuthRole req = new TqAuthRole();
        req.setEntity(new TbAuthRole().setState(CommonEnumContainer.State.NORMAL.getValue()).setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue()));
        List<TvAuthRole> authRoles = authRoleService.getResourcesOfAuthRole(req);
        if(authRoles.isEmpty()){
            return;
        }
        Map<String, List<String>> maps = new HashMap<>();
        authRoles.forEach(x->maps.put(x.getSymbol(),x.getResources()));
        roleResourcesCache.hmset(DictionaryKey.AuthResourceType.FOREGROUND.toString(),maps);
    }














}
