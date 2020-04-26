package com.rong.console.center.config;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.module.query.TsAdmUser;
import com.rong.admin.module.req.TqUserLogin;
import com.rong.admin.module.view.TvAdmUser;
import com.rong.admin.service.AdmColumnService;
import com.rong.admin.service.AdmUserService;
import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.ComServiceFrequentCache;
import com.rong.cache.service.CommonServiceCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import com.rong.common.exception.NoLoginException;
import com.rong.common.exception.NoPermissionException;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.GUIDGenerator;
import com.rong.common.util.NumberUtil;
import com.rong.console.center.util.CookieHelper;
import com.vitily.mybatis.util.CompareAlias;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Component
@Aspect
@Order(value = 8098)
public class AuthenticationInterceptor {
    private final CommonServiceCache commonServiceCache;
    private final AdmColumnService admColumnService;
    private final DictionaryCache dictionaryCache;
    private final ComServiceFrequentCache comServiceFrequentCache;
    private final AdmUserService admUserService;

    @Autowired
    public AuthenticationInterceptor(
            CommonServiceCache commonServiceCache,
            AdmColumnService admColumnService,
            DictionaryCache dictionaryCache,
            ComServiceFrequentCache comServiceFrequentCache,
            AdmUserService admUserService
    ) {
        this.commonServiceCache = commonServiceCache;
        this.admColumnService = admColumnService;
        this.dictionaryCache = dictionaryCache;
        this.comServiceFrequentCache = comServiceFrequentCache;
        this.admUserService = admUserService;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void controllerAspect() {
    }

    public UserStorage login(TqUserLogin req) {

        //判断登录次数是否已经达到极限
        Integer maxErrorCount = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.MAXIMUM_NUMBER_OF_BACKGROUND_LOGIN_ATTEMPTS.getValue()));

        int errCount = comServiceFrequentCache.getCache(DictionaryKey.MemServiceKeyType.NUMBER_OF_BACKGROUND_USER_LOGIN_ATTEMPTS, req.getUserName());

        if (CommonUtil.isNull(maxErrorCount) || maxErrorCount.compareTo(errCount) <= 0) {
            maxErrorCount = 10;
        }

        int left = maxErrorCount - errCount;
        if (left <= 0) {
            throw new CustomerException("登录信息错误，您今日已经尝试登录次数过多，请明天此时再尝试].", CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION);
        }
        TvAdmUser entity = admUserService.selectOneView(
        		admUserService.getMultiCommonWrapper()
				.eq(CompareAlias.valueOf(TsAdmUser.Fields.userName),req.getUserName()));

        admUserService.checkAdmin(entity);
		if(CommonUtil.isNull(entity)){
			throw new CustomerException("该用户不存在", CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST);
		}
		//后台用户是否删除
		if(!entity.getDeltag().equals(CommonEnumContainer.Deltag.NORMAL.getValue())){
			throw new CustomerException("该用户已删除", CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST);
		}
		//后台用户是否被冻结
		if(!CommonUtil.isEqual(entity.getState(), CommonEnumContainer.State.NORMAL.getValue())){
			throw new CustomerException("该用户被冻结", CommonEnumContainer.ResultStatus.THE_USER_DOES_NOT_EXIST);
		}
		//密码是否过期？:null的话强制过期
		if(CommonUtil.isNull(entity.getPasswordExpiration()) || new Date().getTime() > entity.getPasswordExpiration().getTime()){
			throw new CustomerException("密码已经过期，请联系超级管理员", CommonEnumContainer.ResultStatus.TOKEN_IS_INVALID);
		}
        //加密密码对比
        String secPassword = admUserService.encryPassword(entity, req.getPassword());
        if (!CommonUtil.isEqual(secPassword, entity.getPassword())) {
            comServiceFrequentCache.setToServer(DictionaryKey.MemServiceKeyType.NUMBER_OF_BACKGROUND_USER_LOGIN_ATTEMPTS, req.getUserName());
            throw new CustomerException("登录信息错误，用户密码不正确[还可以试" + left + "次].", CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION);
        }

        //登录成功，清除错误次数
        comServiceFrequentCache.removeFromServer(DictionaryKey.MemServiceKeyType.NUMBER_OF_BACKGROUND_USER_LOGIN_ATTEMPTS, req.getUserName());
        //登录成功，剔除之前登录的记录
        commonServiceCache.getInstance(DictionaryKey.Keys.BACKGROUND_USER_TOKEN, CacheSerializableDelegate.jsonSeriaze()).removeFromServer(admUserService.encryToken(entity));


        UserStorage storage = new UserStorage();
        storage.setId(entity.getId());
        storage.setName(entity.getUserName());
        //显示昵称
        storage.setShowName(entity.getNickName());
        storage.setPermissions(admUserService.getTotalPermissions(entity));

        //登录成功 更新tokenKey

        String newTokenKey = GUIDGenerator.getGUID();
        TbAdmUser upAdmin = new TbAdmUser();
        upAdmin.setId(entity.getId());
        upAdmin.setAuthToken(newTokenKey);
        upAdmin.setLastLoginDate(new Date());
		admUserService.updateSelectiveByPrimaryKey(upAdmin);
        storage.setAuthToken(admUserService.encryToken(entity));
        if (CommonUtil.isEqual(req.getLoginMode().getValue(), CommonEnumContainer.UserLoginMode.USERNAME_PASSWORD.getValue())) {
            //save to cache
            Integer time = req.getStoreTime();
            if (CommonUtil.isNull(time)) {
                time = Integer.parseInt(dictionaryCache.getValue(DictionaryKey.Keys.BACKGROUND_USER_TOKEN.getValue()));
            }
            time *= 60 * 60;
            commonServiceCache.getInstance(DictionaryKey.Keys.BACKGROUND_USER_TOKEN, CacheSerializableDelegate.jsonSeriaze()).setToServer(storage.getAuthToken(), storage, time);
        }
        return storage;
    }

    public void logout(HttpServletRequest request) {
        Cookie cookie = CookieHelper.getCookieByName(request, DictionaryKey.Keys.BACKGROUND_USER_TOKEN.getValue());
        if (CommonUtil.isNull(cookie)) {
            return;
        }
        commonServiceCache.getInstance(DictionaryKey.Keys.BACKGROUND_USER_TOKEN, CacheSerializableDelegate.jsonSeriaze()).removeFromServer(cookie.getValue());
    }

    /**
     * 从服务端获得后台用户对象
     *
     * @return UserStorage 后台用户状态
     */
    public UserStorage getAdminByServerStorage(HttpServletRequest request){
        Cookie cookie = CookieHelper.getCookieByName(request, DictionaryKey.Keys.BACKGROUND_USER_TOKEN.getValue());
        String token = null;
        if (CommonUtil.isNotNull(cookie)) {
            token = cookie.getValue();
        }
        CommonServiceCache adminCache = commonServiceCache.getInstance(DictionaryKey.Keys.BACKGROUND_USER_TOKEN, CacheSerializableDelegate.jsonSeriaze());
        return adminCache.getFromServer(token, UserStorage.class);
    }

    public boolean hasPermission(String actionPath, HttpServletRequest request) throws Exception {
        return hasPermission(actionPath, request, false);
    }

    public boolean hasPermission(String actionPath, HttpServletRequest request, boolean logRightId) throws Exception {
        UserStorage storage = getAdminByServerStorage(request);
        if (CommonUtil.isNull(storage)) {
            throw new NoLoginException();
        }
        boolean hasAuth = false;
        Set<Long> sets = admColumnService.hashAuth(actionPath);
        if (CommonUtil.isNull(sets)) {
            return false;
        }
        Iterator<Long> it = sets.iterator();
        while (it.hasNext()) {
            hasAuth = admColumnService.hasColumnPermission(storage, it.next());
            if (hasAuth) {
                break;
            }
        }
        return hasAuth;
    }

    /**
     * 某个方法（url）是否有权限操作验证，使用 spring mvc，返回无非两个，url与jsonString，
     * 日志在其他地方配置了
     *
     * @param pJoinPoint 执行点
     * @return 返回默认返回值：假如成功的话：禁止抛出异常
     */
    @Around(value = "controllerAspect()", argNames = "pJoinPoint")
    public Object checkPermission(ProceedingJoinPoint pJoinPoint) throws Throwable {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) attributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String actionPath = request.getServletPath();
        //先看 actionPath是否非鉴权的url
        Long nonAuthId = admColumnService.hashNonAuth(actionPath);
        if (CommonUtil.isNotNull(nonAuthId)) {//非鉴权
            return pJoinPoint.proceed();
        }
        boolean hap = hasPermission(actionPath, request, true);
        if (!hap) {
            throw new NoPermissionException();
        }
        //是否有基础权限
        //最终，允许程序继续执行
        return pJoinPoint.proceed();
    }




}
