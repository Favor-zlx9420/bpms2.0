package com.rong.console.center.controller;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.module.query.TsAdmMemorandum;
import com.rong.admin.module.query.TsAdmMsg;
import com.rong.admin.module.req.TqUserLogin;
import com.rong.admin.module.view.TvAdmColumn;
import com.rong.admin.module.view.TvAdmUser;
import com.rong.admin.service.AdmColumnService;
import com.rong.admin.service.AdmMemorandumService;
import com.rong.admin.service.AdmMsgService;
import com.rong.admin.service.AdmUserService;
import com.rong.cache.service.DictionaryCache;
import com.rong.cms.consts.CmsEnumContainer;
import com.rong.common.consts.BusinessEnumContainer;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.consts.FinanceEnumContainer;
import com.rong.common.exception.NoPermissionException;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.DateUtil;
import com.rong.common.util.EnumHelperUtil;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.NumberUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.config.AuthenticationInterceptor;
import com.rong.console.center.config.TCaptchaVerify;
import com.rong.console.center.util.CookieHelper;
import com.rong.console.center.util.RightConst;
import com.rong.member.consts.MemEnumContainer;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统管理
 * @author lether
 *
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {
	@Value("${spring.profiles.active}")
	private String env;
	@Value("${tq.chaVerify.app_id:2014115124}")
	private String txTCaptchaAppId;
	@Autowired
      AdmUserService admUserService;
	@Autowired
      AuthenticationInterceptor authenticationInterceptor;
	@Autowired
      DictionaryCache dictionaryCache;
	@Autowired
      AuthenticationInterceptor admAspect;
	@Autowired
      AdmMsgService admMsgService;
	@Autowired
      AdmMemorandumService admMemorandumService;
	@Autowired
      TCaptchaVerify tCaptchaVerify;
	@Autowired
      AdmColumnService admColumnService;
	@Autowired
	private LabelService labelService;
	/**
	 * 个人信息
	 * @param request 1
	 * @return 2
	 */
	@GetMapping(value = "sys/info")
	public String info(HttpServletRequest request)throws Exception{
        UserStorage adminStorage = admAspect.getAdminByServerStorage(request);
        request.setAttribute("admPwdMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
		request.setAttribute("admPwdMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.MAXIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
		request.setAttribute("entity", JSONUtil.toJSONString(admUserService.selectViewByPrimaryKey(MultiTableIdWrapper.valueOf(adminStorage.getId()))));
		return "sys/info";
	}
	/**
	 * 个人信息：编辑提交
     * @param admin 1
	 * @param request 2
	 * @return 3
	 */
	@PostMapping(value = "sys/info")
	@ResponseBody
	public Result info(@RequestBody TvAdmUser admin, HttpServletRequest request, HttpServletResponse response)throws Exception{
		UserStorage adminStorage = admAspect.getAdminByServerStorage(request);
		admin.setId(adminStorage.getId());
		TbAdmUser user = new TbAdmUser();
		BeanUtils.copyProperties(admin,user);
		admUserService.updateByPassword(user, admin.getNewPassword(), NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.HOW_MANY_DAYS_ARE_LEFT_AFTER_CHANGING_THE_PASSWORD.getValue()),30));
		adminStorage.setShowName(admin.getNickName());//更新显示名称

		return Result.success();
	}


	/***********************************不需要鉴权的**************************************/

	/**
	 * 登录后台系统:post 用户名 密码 验证码方式
	 * @param userLogin TqUserLogin
	 * @return
	 */
	@PostMapping(value="login")
	@ResponseBody
	public Result<UserStorage> login(HttpServletRequest request, HttpServletResponse response, TqUserLogin userLogin)throws Exception{
		if(StringUtil.isEmpty(userLogin.getUserName()) || StringUtil.isEmpty(userLogin.getPassword())){
			throw new NoPermissionException("请输入用户名／密码／验证码.");
		}
		if("prod".equals(env) ||"test".equals(env)){
			if(tCaptchaVerify.verifyTicket(request.getParameter("ticket"),request.getParameter("token"),request) == -1){
				throw new NoPermissionException("校验验证码失败！请重新校验！");
			}
		}
		userLogin.setLoginMode(CommonEnumContainer.UserLoginMode.USERNAME_PASSWORD);
		UserStorage storage=admAspect.login(userLogin);
		if(CommonUtil.isNotNull(userLogin.getStoreTime())) {
			CookieHelper.addCookie(response, DictionaryKey.Keys.BACKGROUND_USER_TOKEN.getValue(), storage.getAuthToken(), userLogin.getStoreTime() * 60);
		}

		return Result.success(storage);
	}


	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@GetMapping("logout")
	public String logout(HttpServletRequest request){
		Map<String,String> attrMap = null;
		try {
			Cookie cookie = CookieHelper.getCookieByName(request, DictionaryKey.Keys.BACKGROUND_USER_TOKEN.getValue());
			if(CommonUtil.isNotNull(cookie)) {
				TqUserLogin userLogin = new TqUserLogin();
				userLogin.setToken(cookie.getValue());
				authenticationInterceptor.logout(request);
			}
			attrMap = new HashMap <>();
			attrMap.put("admNameMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_BACKGROUND_USERNAME.getValue()));
			attrMap.put("admNameMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.THE_LONGEST_LENGTH_OF_A_BACKGROUND_USERNAME.getValue()));
			attrMap.put("admPwdMinLen", dictionaryCache.getValue(DictionaryKey.Keys.MINIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
			attrMap.put("admPwdMaxLen", dictionaryCache.getValue(DictionaryKey.Keys.MAXIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD.getValue()));
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
		request.setAttribute("attr", JSONUtil.toJSONString(attrMap));
		request.setAttribute("env",env);
		request.setAttribute("txTCaptchaAppId",txTCaptchaAppId);
		return "login";
	}

	/**
	 * 系统首页
	 * @return 1
	 */
	@GetMapping("")
	public String index(HttpServletRequest request)throws Exception{
		UserStorage admin=admAspect.getAdminByServerStorage(request);
		if(CommonUtil.isNotNull(admin)) {
			TvAdmUser tvAdmin = admUserService.selectOneView(MultiTableIdWrapper.valueOf(admin.getId()));

			request.setAttribute("tvAdmin", JSONUtil.toJSONString(tvAdmin));
			request.setAttribute("remindDay", Integer.valueOf(dictionaryCache.getValue(DictionaryKey.Keys.THERE_ARE_STILL_A_FEW_DAYS_LEFT_FOR_PASSWORD_EXPIRATION_REMINDER.getValue())));
			//提醒消息
			request.setAttribute("msgNoReadCount",admMsgService.selectCount(
					new QueryWrapper()
							.eq(TsAdmMsg.Fields.toAdmUserId,admin.getId())
							.eq(TsAdmMsg.Fields.state, CommonEnumContainer.MsgState.Unread.getValue())
			));
			//备忘录
			request.setAttribute("memoIreNoDealCount",admMemorandumService.selectCount(
					new QueryWrapper()
							.eq(TsAdmMemorandum.Fields.admUserId,admin.getId())
							.eq(TsAdmMemorandum.Fields.state, CommonEnumContainer.AdmMemorandumState.Unprocessed.getValue())
			));
			request.setAttribute("nextDate", DateUtil.dateToDateString(DateUtil.addDate(new Date(),1), DateUtil.yyyy_MM_dd_EN));
			//columns
			List<TvAdmColumn> columns = admColumnService.listPermissionColumns(admin);
			boolean hasLabelAuth = admAspect.hasPermission(RightConst.COMM_LABEL_PATH + "dataGrid",request);
			//hasLabelAuth = false;
			if(hasLabelAuth){
				addLabelColumn(columns,226L, SysEnumContainer.LabelType.BRAND_LABEL,RightConst.PRODUCT_LABEL_PATH + "list");
				addLabelColumn(columns,74L, SysEnumContainer.LabelType.PRODUCT_LABEL_OF_DIRECT_SALE_STORE,RightConst.STORE_PRODUCT_LABEL_PATH + "list");
				addLabelColumn(columns,75L, SysEnumContainer.LabelType.STORE_LABEL,RightConst.STORE_ORG_LABEL_PATH + "list");
			}
			request.setAttribute("columns", JSONUtil.toJSONString(columns));


			return "/default";
		}
		return logout(request);
	}

	private void addLabelColumn(List<TvAdmColumn> columns, Long parentId, SysEnumContainer.LabelType labelType, String link){
		List<TbLabel> labels = labelService.selectList(WrapperFactory.queryWrapper()
				.eq(TsLabel.Fields.type, labelType.getValue())
				.eq(TsLabel.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
		);
		labels.forEach(x->{
			TvAdmColumn column = new TvAdmColumn();
			column.setIsBtn(false);
			column.setSort(x.getSort());
			column.setId(x.getId());
			column.setName(x.getName());
			column.setParentId(parentId);
			column.setUrlLink(link);
			column.setVisible(true);
			columns.add(column);
		});
	}

	//将enum信息输出到前台
	@GetMapping("layout/const")
	@ResponseBody
	public String respConsts(HttpServletRequest request,HttpServletResponse response){
		Map map = new HashMap();
		putMapToHash(CommonEnumContainer.MsgState.class,map);
		putMapToHash(CommonEnumContainer.AdmMemorandumState.class,map);
		putMapToHash(CommonEnumContainer.State.class,map);
		putMapToHash(CommonEnumContainer.Deltag.class,map);
		putMapToHash(CommonEnumContainer.LogState.class,map);
		putMapToHash(FinanceEnumContainer.FundsType.class,map);
		putMapToHash(CommonEnumContainer.Platform.class,map);
		putMapToHash(FinanceEnumContainer.FundsDirection.class,map);
		putMapToHash(FinanceEnumContainer.RechargeState.class,map);
		putMapToHash(FinanceEnumContainer.RechargeMode.class,map);
		putMapToHash(FinanceEnumContainer.WithdrawState.class,map);
		putMapToHash(FinanceEnumContainer.WithdrawMode.class,map);
		putMapToHash(MemEnumContainer.ClassesFrameType.class,map);
		putMapToHash(MemEnumContainer.CustomerPickState.class,map);
		putMapToHash(MemEnumContainer.CustomerPayState.class,map);
		putMapToHash(CommonEnumContainer.Default.class,map);
		putMapToHash(MemEnumContainer.MemBankCardState.class,map);
		putMapToHash(CommonEnumContainer.BankCardType.class,map);
		putMapToHash(MemEnumContainer.MemCate.class,map);
		putMapToHash(MemEnumContainer.RegFrom.class,map);
		putMapToHash(MemEnumContainer.CreditfileCertifyState.class,map);
		putMapToHash(MemEnumContainer.CreditfileType.class,map);
		putMapToHash(MemEnumContainer.EducationLevel.class,map);
		putMapToHash(SysEnumContainer.BannerPage.class,map);
		putMapToHash(SysEnumContainer.SysType.class,map);
		putMapToHash(SysEnumContainer.LabelType.class,map);
		putMapToHash(SysEnumContainer.DictionaryType.class,map);
		putMapToHash(SysEnumContainer.AdvertiseType.class,map);
		putMapToHash(CommonEnumContainer.Gender.class,map);
		putMapToHash(CommonEnumContainer.IdType.class,map);
		putMapToHash(CmsEnumContainer.Top.class,map);
		putMapToHash(CmsEnumContainer.Published.class,map);
		putMapToHash(CommonEnumContainer.CustomerUserState.class,map);
		putMapToHash(CommonEnumContainer.YesOrNo.class,map);

		putMapToHash(CommonEnumContainer.OrgType.class,map);
		putMapToHash(CommonEnumContainer.ProductType.class,map);
		putMapToHash(CommonEnumContainer.CommentType.class,map);
		putMapToHash(MemEnumContainer.MemLevel.class,map);
		putMapToHash(MemEnumContainer.MemType.class,map);
		putMapToHash(CommonEnumContainer.CustomerAuditState.class,map);
		putMapToHash(CommonEnumContainer.DealStatus.class,map);
		putMapToHash(CommonEnumContainer.AdvertisePosition.class,map);
		putMapToHash(CommonEnumContainer.ReservationDealStatus.class,map);
		putMapToHash(CommonEnumContainer.ReservationType.class,map);
		putMapToHash(CommonEnumContainer.CustomerServiceAuditResult.class,map);
		putMapToHash(BusinessEnumContainer.InvestStrategyType.class,map);
		putMapToHash(CommonEnumContainer.InvestorQualifiedResult.class,map);
		putMapToHash(CommonEnumContainer.NewsState.class,map);

		map.put("RATE_UNIT", NumberUtil.RATE_UNIT);
		request.setAttribute("Consts", JSONUtil.toJSONString(map));
		return "var Consts="+ JSONUtil.toJSONString(map);
	}
	private <T extends Enum<T>> void putMapToHash(Class<T> clazz,Map map){
		map.put(clazz.getSimpleName(), EnumHelperUtil.enumToHash(clazz));
	}
}
