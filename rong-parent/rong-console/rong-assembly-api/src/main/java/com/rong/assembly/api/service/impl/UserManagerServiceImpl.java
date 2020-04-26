package com.rong.assembly.api.service.impl;

import com.rong.assembly.api.mapper.RespPeopleInfoMapper;
import com.rong.assembly.api.module.request.TqResetPassword;
import com.rong.assembly.api.module.response.user.TrUserFundAccountIndex;
import com.rong.assembly.api.service.TripartiteMessageService;
import com.rong.assembly.api.service.UserManagerService;
import com.rong.cache.service.ComServiceFrequentCache;
import com.rong.cache.service.CommonServiceCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.Result;
import com.rong.common.module.UserInfo;
import com.rong.common.util.Assert;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.GUIDGenerator;
import com.rong.common.util.MD5;
import com.rong.common.util.MissResultFactory;
import com.rong.common.util.NumberUtil;
import com.rong.common.util.ServiceEncryUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNav;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.req.TqCheckVerificationCode;
import com.rong.member.module.req.TqLoginByCode;
import com.rong.member.module.req.TqMemLogin;
import com.rong.member.module.req.TqMemRegister;
import com.rong.member.module.request.TqMemBase;
import com.rong.member.module.view.TvMemBase;
import com.rong.member.service.MemAuthRoleService;
import com.rong.member.service.MemBaseService;
import com.rong.member.util.MemberUtil;
import com.rong.user.module.entity.TbUserFundAccount;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RefreshScope
@Service
public class UserManagerServiceImpl implements UserManagerService {
    private final MemBaseService memBaseService;
    private final MemAuthRoleService memAuthRoleService;
    private final TripartiteMessageService tripartiteMessageService;
    private final CommonServiceCache commonServiceCache;
    private final DictionaryCache dictionaryCache;
    private final ComServiceFrequentCache comServiceFrequentCache;
    @Autowired
    private RespPeopleInfoMapper respPeopleInfoMapper;
    @Autowired
    private HttpServletRequest request;
    @Value("${user.base.roles:[]}")
    private String[] userBaseRoles;

    public UserManagerServiceImpl(MemBaseService memBaseService, MemAuthRoleService memAuthRoleService, TripartiteMessageService tripartiteMessageService, CommonServiceCache commonServiceCache, DictionaryCache dictionaryCache, ComServiceFrequentCache comServiceFrequentCache) {
        this.memBaseService = memBaseService;
        this.memAuthRoleService = memAuthRoleService;
        this.tripartiteMessageService = tripartiteMessageService;
        this.commonServiceCache = commonServiceCache;
        this.dictionaryCache = dictionaryCache;
        this.comServiceFrequentCache = comServiceFrequentCache;
    }


    @Override
    public Result register(TqMemRegister req) {
        TqCheckVerificationCode checkVerificationCode = new TqCheckVerificationCode.Builder()
                .verificationCode(req.getVerificationCode())
                .receiver(req.getPhone())
                .contentType(CommonEnumContainer.TripartiteMessageContentType.REGISTER.getValue())
                .build();
        Result<Boolean> checkSmsCodeState = tripartiteMessageService.isLegalVerificationCode(checkVerificationCode);
        if (checkSmsCodeState.sad()) {
            return checkSmsCodeState;
        }

        //假如用户已经存在则让用户登录
        TbMemBase user = memBaseService.selectOne(
                new QueryWrapper()
                        .eq(TsMemBase.Fields.userName,req.getPhone())
                        .eq(TsMemBase.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        if(null != user){
            memBaseService.updateSelectItem(
                    new UpdateWrapper()
                            .eq(TsMemBase.Fields.id,user.getId())
                            .update(Elements.valueOf(TsMemBase.Fields.lastLoginDate,new Date()))
            );
            return Result.success(createUserInfoAndSaveToCache(user,req.getStoreSeconds()));
        }
        //

        TbMemBase entity = new TbMemBase().setUserName(req.getPhone())
                .setEmail(req.getEmail())
                .setPassword(req.getPassword())
                .setRegFrom(req.getFrom())
                .setState(CommonEnumContainer.State.NORMAL.getValue());
        //判断推荐码是否存在
        if (StringUtil.isNotEmpty(req.getRecommendCode())) {
            TbMemBase rec = memBaseService.selectOne(
                    new QueryWrapper()
                    .eq(TsMemBase.Fields.recommendCode,req.getRecommendCode())
                    .eq(TsMemBase.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                    .eq(TsMemBase.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                    .select(TsMemBase.Fields.id)
            );
            if (CommonUtil.isNull(rec)) {
                return MissResultFactory.noExistsResult("推荐码不存在，请检查!!!");
            }
            entity.setRecommendMemberId(rec.getId());
        }

        entity.setType(MemEnumContainer.MemType.普通用户.getValue());
        entity.setLevel(MemEnumContainer.MemLevel.无.getValue());
        entity.setPhone(req.getPhone());


        TqMemBase tqMemBase = new TqMemBase();
        tqMemBase.setEntity(entity);
        int res = memBaseService.insert(tqMemBase);
        if(res == 2){
            throw new CustomerException("注册失败", CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION);
        }
        if (res != 1) {
            throw new CustomerException("注册失败", CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION);
        }
        tripartiteMessageService.removeVerificationCode(checkVerificationCode);
        return Result.success(createUserInfoAndSaveToCache(entity,req.getStoreSeconds()));
    }
    @Override
    public Result loginByCode(TqLoginByCode req) {
        TqCheckVerificationCode checkVerificationCode = new TqCheckVerificationCode.Builder()
                .verificationCode(req.getVerificationCode())
                .receiver(req.getPhone())
                .contentType(CommonEnumContainer.TripartiteMessageContentType.REGISTER.getValue())
                .build();
        Result<Boolean> checkSmsCodeState = tripartiteMessageService.isLegalVerificationCode(checkVerificationCode);
        if (checkSmsCodeState.sad()) {
            return checkSmsCodeState;
        }

        //假如用户已经存在则让用户登录
        TbMemBase user = memBaseService.selectOne(
                new QueryWrapper()
                        .eq(TsMemBase.Fields.userName,req.getPhone())
                        .eq(TsMemBase.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        if(null != user){
            if(null != req.getType() && !CommonUtil.isEqual(req.getType(),user.getType())){
                return Result.miss(CommonEnumContainer.ResultStatus.WITHOUT_PERMISSION, "您无此权限");
            }
            memBaseService.updateSelectItem(
                    new UpdateWrapper()
                            .eq(TsMemBase.Fields.id,user.getId())
                            .update(Elements.valueOf(TsMemBase.Fields.lastLoginDate,new Date()))
            );
            return Result.success(createUserInfoAndSaveToCache(user,req.getStoreSeconds()));
        }
        return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"用户不存在");
    }

    @Override
    @Transactional
    public Result login(TqMemLogin req) {

        //判断登录次数是否已经达到极限
        Integer maxErrorCount = NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.NUMBER_OF_MEMBER_LOGIN_ATTEMPTS.getValue()));
        String hashIp = MD5.getMD5(req.getReqIp());
        int ipErrCount = comServiceFrequentCache.getCache(DictionaryKey.MemServiceKeyType.NUMBER_OF_MEMBER_LOGIN_ATTEMPTS, hashIp);
        if (CommonUtil.isNotNull(maxErrorCount)) {
            if (maxErrorCount.compareTo(ipErrCount) <= 0) {
                return Result.miss(CommonEnumContainer.ResultStatus.TOO_MANY_REQUESTS, "您今日已经尝试登录次数过多，请明天此时再尝试！");
            }
        }
        TbMemBase user = memBaseService.selectOne(
                new QueryWrapper()
                    .eq(TsMemBase.Fields.userName,req.getUserName())
                    .eq(TsMemBase.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        if (CommonUtil.isNull(user)) {
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST, "用户不存在");
        }
        if (!CommonUtil.isEqual(memBaseService.encryPassword(user,req.getPassword()), user.getPassword())) {
            comServiceFrequentCache.setToServer(DictionaryKey.MemServiceKeyType.NUMBER_OF_MEMBER_LOGIN_ATTEMPTS, hashIp);
            return Result.miss(CommonEnumContainer.ResultStatus.WRONG_USERNAME_OR_PASSWORD, "登录信息错误，用户/密码不正确");
        }
        if (!CommonUtil.isEqual(CommonEnumContainer.State.NORMAL.getValue(), user.getState())) {
            return Result.miss(CommonEnumContainer.ResultStatus.USER_DISABLED, "登录信息错误，用户已被禁用,请联系客服");
        }
        if(null != req.getType() && !CommonUtil.isEqual(req.getType(),user.getType())){
            return Result.miss(CommonEnumContainer.ResultStatus.WITHOUT_PERMISSION, "您无此权限");
        }
        //登录成功，清除错误次数
        comServiceFrequentCache.removeFromServer(DictionaryKey.MemServiceKeyType.NUMBER_OF_MEMBER_LOGIN_ATTEMPTS, hashIp);
        memBaseService.updateSelectItem(
                new UpdateWrapper()
                    .eq(TsMemBase.Fields.id,user.getId())
                    .update(Elements.valueOf(TsMemBase.Fields.lastLoginDate,new Date()))
        );
        return Result.success(createUserInfoAndSaveToCache(user, req.getStoreSeconds()));
    }

    /**
     * 找回密码
     * @param req
     * @return
     */
    @Override
    @Transactional
    public Result resetPassword(TqResetPassword req){

        TqCheckVerificationCode checkVerificationCode = new TqCheckVerificationCode.Builder()
                .verificationCode(req.getVerificationCode())
                .receiver(req.getReceiver())
                .contentType(CommonEnumContainer.TripartiteMessageContentType.RETRIEVE_PASSWORD.getValue())
                .build();
        Result<Boolean> checkSmsCodeState = tripartiteMessageService.isLegalVerificationCode(checkVerificationCode);
        if (checkSmsCodeState.sad()) {
            return checkSmsCodeState;
        }
        TbMemBase user = memBaseService.selectOne(new QueryWrapper()
                .eq(TsMemBase.Fields.userName,req.getReceiver())
                .eq(TsMemBase.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                .select(TsMemBase.Fields.id)
        );
        Assert.notNull(user,"用户不存在！");
        user.setSalt(GUIDGenerator.getGUID());
        user.setPassword(memBaseService.encryPassword(user,req.getPassword()));
        user.setUpdateDate(new Date());
        memBaseService.updateSelectiveByPrimaryKey(user);
        return Result.success("设置成功！");
    }

    @Override
    public TrUserFundAccountIndex selectSumFundIndex(Long userId) {
        MultiTableQueryWrapper sumWrapper =
                WrapperFactory.multiQueryWrapper(TbUserFundAccount.class)
                        .select0(
                                SelectAlias.valueOf("ifnull(sum(e.principal),0) totalPrincipal",true)
                                ,
                                SelectAlias.valueOf("ifnull(sum(pcn.nav*e.share),0)  totalPriMarkValue",true)
                                ,
                                SelectAlias.valueOf("ifnull(sum(rcn.nav*e.share),0)  totalRaisedMarkValue",true)
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentNav.class,"pcn"), pcn->pcn.eqc(
                                CompareAlias.valueOf("pcn.securityId"),CompareAlias.valueOf("e.securityId")
                        ))
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbRaisedFundCurrentNav.class,"rcn"), pcn->pcn.eqc(
                                CompareAlias.valueOf("rcn.securityId"),CompareAlias.valueOf("e.securityId")
                        ))
                        .eq(CompareAlias.valueOf("e.userId"),userId)
                        .eq(CompareAlias.valueOf("e.deltag"),false)
                ;
        return respPeopleInfoMapper.selectSumFundIndex(sumWrapper);
    }

    @Override
    public Result responseUserInfoResult(Result result) {
        if(!result.isSucceed()){
            return result;
        }
        UserInfo userInfo = (UserInfo) result.getContent();
        if(null == userInfo){
            return result;
        }
        if(userInfo.isQualified()){
            request.setAttribute("mastQualified",true);
        }
        userInfo.setFundAccount0(selectSumFundIndex(userInfo.getUserId()));
        return result;
    }

    private UserInfo createUserInfoAndSaveToCache(TbMemBase user, Integer _storeSeconds) {
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        int storeSeconds = 60 * 60 * 24;
        if(null != _storeSeconds){
            storeSeconds = _storeSeconds;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setExpire(System.currentTimeMillis() + storeSeconds * 1000);//60 * 10 * 1000 = 10分钟
//        //setRoles
//        userInfo.setRoles(new ArrayList<>());
//        memAuthRoleService.selectViewList(
//                memAuthRoleService.getMultiCommonWrapper()
//                    .eq(CompareAlias.valueOf(TsMemAuthRole.Fields.userId, ConstValue.MAIN_ALIAS),user.getId())
//        ).forEach(x -> userInfo.getRoles().add(x.getRoleSymbol()));
//        for(String symbol:userBaseRoles){
//            userInfo.getRoles().add(symbol);
//        }

        userInfo.setUserId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setToken(ServiceEncryUtil.createUserToken(userInfo));
        TvMemBase memBase = memBaseService.selectViewByPrimaryKey(memBaseService.getMultiCommonIdWrapper(user.getId()));
        userInfo.setQualified(memBase.getInvestorQualified() != null && memBase.getInvestorQualified().getScore() != null);
        memCache.setToServer(userInfo.getToken(), userInfo, storeSeconds);
        memCache.setToServer(user.getType() + "-" + user.getId(), userInfo, storeSeconds);
        userInfo.setUserInfo(memBase);
        userInfo.setHasSetPassword(StringUtil.isNotEmpty(memBase.getPassword()));

        return userInfo;
    }

}
