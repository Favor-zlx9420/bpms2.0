package com.rong.assembly.api.controller.usercenter;

import com.rong.assembly.api.mapper.RespPeopleInfoMapper;
import com.rong.assembly.api.module.request.TqGetUserInfoByAuthToken;
import com.rong.assembly.api.module.request.uc.*;
import com.rong.assembly.api.module.request.uc.qualified.TqQualifiedInfo;
import com.rong.assembly.api.module.request.uc.reservation.TqReservationList;
import com.rong.assembly.api.module.request.uc.reservation.TqUpdateReservation;
import com.rong.assembly.api.module.request.uc.reservation.TqViewRoadShowList;
import com.rong.assembly.api.module.request.uc.reservation.TqViewRoadShowOpera;
import com.rong.assembly.api.module.response.TrCheckFile;
import com.rong.assembly.api.module.response.people.UserVipInfo;
import com.rong.assembly.api.service.UserManagerService;
import com.rong.assembly.api.util.UploadFileUtil;
import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.CommonServiceCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.module.TqUserAuthBase;
import com.rong.common.module.TvPageList;
import com.rong.common.module.UserInfo;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.DateUtil;
import com.rong.common.util.EnumHelperUtil;
import com.rong.common.util.GUIDGenerator;
import com.rong.common.util.IDCardUtil;
import com.rong.common.util.MD5;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.view.TvOrgProxy;
import com.rong.fundmanage.service.OrgProxyService;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbUserComment;
import com.rong.member.module.entity.TbUserReservation;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.query.TsUserComment;
import com.rong.member.module.query.TsUserReservation;
import com.rong.member.module.request.TqUserComment;
import com.rong.member.module.request.TqUserReservation;
import com.rong.member.module.view.TvMemBase;
import com.rong.member.module.view.TvUserReservation;
import com.rong.member.service.MemBaseService;
import com.rong.member.service.UserCommentService;
import com.rong.member.service.UserReservationService;
import com.rong.member.util.MemberUtil;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.entity.TbUserRoadShowView;
import com.rong.roadshow.module.query.TsRoadShowInfo;
import com.rong.roadshow.module.query.TsUserRoadShowReservation;
import com.rong.roadshow.module.request.TqUserRoadShowView;
import com.rong.roadshow.module.view.TvUserRoadShowView;
import com.rong.roadshow.service.RoadShowInfoService;
import com.rong.roadshow.service.UserRoadShowViewService;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.query.TsDirectStoreOrg;
import com.rong.store.service.DirectStoreOrgService;
import com.rong.sys.module.entity.TbImageSams;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.query.TsMdInstitution;
import com.rong.tong.pfunds.service.MdInstitutionService;
import com.rong.user.module.entity.TbInvestorQualified;
import com.rong.user.module.entity.TbUserCommentReply;
import com.rong.user.module.entity.TbUserEmploymentCert;
import com.rong.user.module.entity.TbUserFeedBack;
import com.rong.user.module.entity.TbUserVip1Apply;
import com.rong.user.module.entity.TbUserVip2Apply;
import com.rong.user.module.entity.TbUserVipEnd;
import com.rong.user.module.query.TsInvestorQualified;
import com.rong.user.module.query.TsUserCommentReply;
import com.rong.user.module.query.TsUserFeedBack;
import com.rong.user.module.query.TsUserVip1Apply;
import com.rong.user.module.query.TsUserVip2Apply;
import com.rong.user.module.query.TsUserVipEnd;
import com.rong.user.module.request.TqInvestorQualified;
import com.rong.user.module.request.TqUserCommentReply;
import com.rong.user.module.request.TqUserEmploymentCert;
import com.rong.user.module.request.TqUserFeedBack;
import com.rong.user.module.request.TqUserVip1Apply;
import com.rong.user.module.request.TqUserVip2Apply;
import com.rong.user.module.view.TvUserFeedBack;
import com.rong.user.service.InvestorQualifiedService;
import com.rong.user.service.UserCommentReplyService;
import com.rong.user.service.UserEmploymentCertService;
import com.rong.user.service.UserFeedBackService;
import com.rong.user.service.UserVip1ApplyService;
import com.rong.user.service.UserVip2ApplyService;
import com.rong.user.service.UserVipEndService;
import com.vitily.mybatis.core.entity.Element;
import com.vitily.mybatis.core.entity.FieldValue;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 用户中心
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("user/center")
public class UserCenterController {
    @Autowired
    private CommonServiceCache commonServiceCache;
    @Autowired
    private MemBaseService memBaseService;
    @Autowired
    private InvestorQualifiedService investorQualifiedService;
    @Autowired
    private UserRoadShowViewService userRoadShowViewService;
    @Autowired
    private RoadShowInfoService roadShowInfoService;
    @Autowired
    DictionaryCache dictionaryCache;
    @Autowired
    ViyBasicCache viyBasicCache;
    @Autowired
    private UserCommentService userCommentService;
    @Autowired
    private UserCommentReplyService userCommentReplyService;
    @Autowired
    private DirectStoreOrgService directStoreOrgService;
    @Autowired
    private UserFeedBackService userFeedBackService;
    @Autowired
    private RespPeopleInfoMapper peopleInfoMapper;
    @Autowired
    private MdInstitutionService mdInstitutionService;
    @Autowired
    private OrgProxyService orgProxyService;
    @Autowired
    private UserVipEndService userVipEndService;
    @Autowired
    private UserVip1ApplyService userVip1ApplyService;
    @Autowired
    private UserVip2ApplyService userVip2ApplyService;
    @Autowired
    private UserReservationService userReservationService;
    @Autowired
    private UserManagerService userManagerService;

    /**
     * 引入用户金融从业认证服务接口
     */
    @Autowired
    private UserEmploymentCertService userEmploymentCertService;

    /**
     * 通过token获取用户信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "通过token以及用户名获取用户信息")
    @GetMapping(value = "getUserInfoByAuthToken")
    public Result<UserInfo<TvMemBase>> getUserInfoByAuthToken(TqGetUserInfoByAuthToken req) {

        //先查看用户是否已经登录
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        UserInfo userInfo = memCache.getFromServer(req.getUserAuthToken(), UserInfo.class);
        TvMemBase user = memBaseService.selectViewByPrimaryKey(memBaseService.getMultiCommonIdWrapper(userInfo.getUserId()));
        userInfo.setUserInfo(user);
        return userManagerService.responseUserInfoResult(Result.success(userInfo));
    }

    /**
     * 更新用户资料
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "更新用户资料")
    @PostMapping("info/update")
    public Result<Boolean> updateInfo(@RequestBody TqUpdateUserInfo req) {
        //先查看用户是否已经登录
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        UserInfo userInfo = memCache.getFromServer(req.getUserAuthToken(), UserInfo.class);
        TbMemBase user = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(userInfo.getUserId(),
                TsMemBase.Fields.realName,
                TsMemBase.Fields.idNo,
                TsMemBase.Fields.email,
                TsMemBase.Fields.password,
                TsMemBase.Fields.jgInfo,
                TsMemBase.Fields.id
        ));
        List<Element> ups = new ArrayList<>();
        if (StringUtil.isNotEmpty(req.getRealName())) {
            if (req.getRealName().length() > 100) {
                return Result.miss(CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS, "用户名长度不能超过100");
            }
            ups.add(Elements.valueOf(TsMemBase.Fields.realName, req.getRealName()));
        }
        if (StringUtil.isNotEmpty(req.getIdNo())) {
            if (!IDCardUtil.isIDCard(req.getIdNo())) {
                return Result.miss(CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS, "非法身份证号码");
            }
            ups.add(Elements.valueOf(TsMemBase.Fields.idNo, req.getIdNo()));
        }
        if (StringUtil.isNotEmpty(req.getEmail())) {
            if (!CommonUtil.isEmail(req.getEmail())) {
                return Result.miss(CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS, "email 非法，请输入正确的email");
            }
            ups.add(Elements.valueOf(TsMemBase.Fields.email, req.getEmail()));
        }
        if (StringUtil.isEmpty(user.getJgInfo()) && StringUtil.isNotEmpty(req.getJgInfo())) {
            ups.add(Elements.valueOf(TsMemBase.Fields.jgInfo, req.getJgInfo()));
        }
        if (StringUtil.isNotEmpty(req.getPassword())) {
            if (StringUtil.isNotEmpty(user.getPassword())) {
                return Result.miss(CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS, "您已设置过密码，请通过找回密码或者修改密码功能重设密码");
            }
            user.setSalt(GUIDGenerator.getGUID());
            ups.add(Elements.valueOf(TsMemBase.Fields.salt, user.getSalt()));
            ups.add(Elements.valueOf(TsMemBase.Fields.password, memBaseService.encryPassword(user, req.getPassword())));
        }
        if (StringUtil.isNotEmpty(req.getHeadPortrait())) {
            ups.add(Elements.valueOf(TsMemBase.Fields.headPortrait, req.getHeadPortrait()));
        }
        if (StringUtil.isNotEmpty(req.getNickName())) {
            ups.add(Elements.valueOf(TsMemBase.Fields.nickName, req.getNickName()));
        }
        if (StringUtil.isNotEmpty(req.getGreet())) {
            ups.add(Elements.valueOf(TsMemBase.Fields.greet, req.getGreet()));
        }
        if (StringUtil.isNotEmpty(req.getAddress())) {
            ups.add(Elements.valueOf(TsMemBase.Fields.address, req.getAddress()));
        }
        if (!ups.isEmpty()) {
            memBaseService.updateSelectItem(new UpdateWrapper()
                    .update(ups)
                    .eq(TsMemBase.Fields.id, req.getUserId())
            );
        }

        return Result.success(Boolean.TRUE);
    }

    /**
     * 更新用户密码（通旧密码方式修改）
     *
     * @param req
     * @return
     */    @ApiOperation(value = "更新用户密码（通旧密码方式修改）")
    @PostMapping("password/update")
    public Result<Boolean> updatePassword(@RequestBody TqUpdatePassword req) {
        //先查看用户是否已经登录
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        UserInfo userInfo = memCache.getFromServer(req.getUserAuthToken(), UserInfo.class);
        TbMemBase user = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(userInfo.getUserId(),
                TsMemBase.Fields.id,
                TsMemBase.Fields.password,
                TsMemBase.Fields.salt
        ));
        //判断旧密码是否正确
        if (!CommonUtil.isEqual(user.getPassword(), memBaseService.encryPassword(user, req.getOldPassword()))) {
            return Result.miss(CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS, "旧密码不正确");
        }
        user.setUpdateDate(new Date());
        user.setSalt(GUIDGenerator.getGUID());
        user.setPassword(memBaseService.encryPassword(user, req.getNewPassword()));
        memBaseService.updateSelectiveByPrimaryKey(user);

        return Result.success(Boolean.TRUE);
    }

    /**
     * 提交合格投资人认证
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "合格投资人认证设置")
    @PostMapping("qualified/update")
    public Result<TbInvestorQualified> updateQualified(@RequestBody TqQualifiedInfo req) {
        //先查看用户是否已经登录
        TbInvestorQualified qualified = investorQualifiedService.selectOne(
                new QueryWrapper().select(TsInvestorQualified.Fields.id)
                        .eq(TsInvestorQualified.Fields.userId, req.getUserId())
        );
        boolean has = qualified != null;
        if (!has) {
            qualified = new TbInvestorQualified();
            qualified.setUserId(req.getUserId());
        }
        //计算分数
        int score = 0;
        qualified.setInvestorType(req.getInvestorType());

        qualified.setExpected(req.getExpected());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getExpected()).getValue();
        qualified.setFallingLimit(req.getFallingLimit());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getFallingLimit()).getValue();
        qualified.setIncomeGrowth(req.getIncomeGrowth());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getIncomeGrowth()).getValue();
        qualified.setInvestmentPeriod(req.getInvestmentPeriod());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getInvestmentPeriod()).getValue();
        qualified.setInvestmentRatio(req.getInvestmentRatio());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getInvestmentRatio()).getValue();

        qualified.setVcExperience(req.getVcExperience());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getVcExperience()).getValue();
        qualified.setMonetaryAssetsIncome(req.getMonetaryAssetsIncome());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getMonetaryAssetsIncome()).getValue();
        qualified.setPurpose(req.getPurpose());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getPurpose()).getValue();
        qualified.setUnderstandingInvestments(req.getUnderstandingInvestments());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getUnderstandingInvestments()).getValue();
        qualified.setTreatInvestmentLosses(req.getTreatInvestmentLosses());
        score += CommonEnumContainer.InvestorQualifiedScore.valueOf(req.getTreatInvestmentLosses()).getValue();
        qualified.setScore(score);
        qualified.setState(CommonEnumContainer.State.NORMAL.getValue());
        //
        qualified.setQualifiedResult(CommonEnumContainer.InvestorQualifiedResult.getByScore(score).name());
        qualified.setUpdateDate(new Date());
        if (has) {
            investorQualifiedService.updateByPrimary(new TqInvestorQualified().setEntity(qualified));
        } else {
            investorQualifiedService.insert(new TqInvestorQualified().setEntity(qualified));
        }
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        UserInfo userInfo = memCache.getFromServer(req.getUserAuthToken(), UserInfo.class);
        userInfo.setQualified(true);
        memCache.setToServer(req.getUserAuthToken(), userInfo);

        return Result.success(qualified);
    }

    /**
     * 提交金融从业认证
     * @author zhanglei
     * @param req
     * @date 2020-04-23
     * @return
     */
    @ApiOperation(value = "金融从业认证")
    @PostMapping("finance/employmentCert")
    public Result<TbUserEmploymentCert> saveEmploymentCertInfo(@RequestBody TqUserEmploymentCert req) {
        /**
         * 校验用户金融从业认证请求参数
         */
        if (null == req || null == req.getUserId() || null == req.getOrgType() || StringUtil.isEmpty(req.getOrgName()) || StringUtil.isEmpty(req.getUserName()) || null == req.getSex() || StringUtil.isEmpty(req.getPositionName()) || null == req.getEmploymentYearNum() || StringUtil.isEmpty(req.getPersonalCardImg())) {
            throw new CustomerException("用户金融从业认证，请求参数校验不通过", CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE);
        }

        return userEmploymentCertService.saveUserEmploymentCertInfo(req);
    }


    /**
     * 我查看的路演记录
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "我查看的路演记录")
    @GetMapping("roadShow/viewList")
    public Result<TvPageList<TvUserRoadShowView>> viewRoadShowList(TqViewRoadShowList req) {
        MultiTableQueryWrapper queryWrapper = userRoadShowViewService.getMultiCommonWrapper();
        queryWrapper
                .eq(CompareAlias.valueOf("e.userId"), req.getUserId())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
                //.ge(CompareAlias.valueOf("rs.showDate"), DateUtil.getCurDateTime(DateUtil.JSONINPUT_yyyy_MM_dd_HH_mm_ss_EN))
                .page(req.getPageInfo())
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.updateDate")))
        ;
        return Result.success(userRoadShowViewService.selectPageList(queryWrapper));
    }

    /**
     * 查看路演
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "查看路演")
    @PostMapping("roadShow/view")
    public Result<Boolean> viewRoadShow(@RequestBody TqViewRoadShowOpera req) {
        TbRoadShowInfo roadShowInfo = roadShowInfoService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getRoadShowId()
                , TsRoadShowInfo.Fields.id
                , TsRoadShowInfo.Fields.state
                , TsRoadShowInfo.Fields.deltag
                , TsRoadShowInfo.Fields.showDate
                , TsRoadShowInfo.Fields.viewUsers
        ));
        if (null == roadShowInfo || roadShowInfo.getDeltag() == true
                || !CommonUtil.isEqual(roadShowInfo.getState(), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())) {
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST, "路演不存在");
        }
        TbUserRoadShowView has = userRoadShowViewService.selectOne(
                new QueryWrapper()
                        .select(TsUserRoadShowReservation.Fields.id, TsUserRoadShowReservation.Fields.deltag)
                        .eq(TsUserRoadShowReservation.Fields.userId, req.getUserId())
                        .eq(TsUserRoadShowReservation.Fields.roadShowId, req.getRoadShowId())
        );
        if (has == null) {
            TbUserRoadShowView res = new TbUserRoadShowView()
                    .setRoadShowId(req.getRoadShowId())
                    .setUserId(req.getUserId())
                    .setUpdateDate(new Date());
            userRoadShowViewService.insert(new TqUserRoadShowView().setEntity(res));
            //增加查看按人数
            roadShowInfoService.updateSelectItem(
                    new UpdateWrapper()
                            .update(
                                    Elements.valueOf(TsRoadShowInfo.Fields.updateDate, new Date()),
                                    Elements.valueOf(TsRoadShowInfo.Fields.viewUsers, roadShowInfo.getViewUsers() + 1)
                            )
                            .eq(TsRoadShowInfo.Fields.id, req.getRoadShowId())
            );
        } else {
            TbUserRoadShowView up = new TbUserRoadShowView();
            up.setId(has.getId());
            userRoadShowViewService.updateByPrimary(new TqUserRoadShowView().setEntity(up));
        }

        return Result.success(Boolean.TRUE);
    }

    /**
     * 上传图片、文档
     *
     * @param req
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传图片,请用form格式post文件")
    @PostMapping("/img/upload")
    public Result<TbImageSams> uploadFile(TqUploadSimple req, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return Result.success(upfile(req, request, response));
    }

    private TbImageSams upfile(TqUploadSimple req, HttpServletRequest request, HttpServletResponse response) throws Exception {


        String fileName = req.getUserId() + "-" + getClass().getName() + req.getType() + "-";
        TqUploadSimple.UploadFileType uploadFileType = EnumHelperUtil.getByValue(TqUploadSimple.UploadFileType.class, req.getType());
        if(null == req.getPicIndex()){
            req.setPicIndex(0);
        }
        //最大数量，有限额
        if (uploadFileType.getMaxCount() > 0) {
            fileName += req.getPicIndex() % uploadFileType.getMaxCount();
            fileName = MD5.getMD5(fileName);
        } else {
            //无限额，因此需要记住哪个用户上传的多,不签名
            fileName = req.getUserId() + "-" + req.getType() + "-" + DateUtil.getCurDateTime(DateUtil.yyyyMMddHHmmss);
        }
        return UploadFileUtil.kindEditorUploadOfName("", dictionaryCache, viyBasicCache, req.getImg(), request, response,
                uploadFileType.getDirName(), "user-upload", fileName, false, false);
    }


    /**
     * 上传大文件[分片上传]
     */
    @ApiOperation(value = "上传视频等大文件[分片上传]")
    @PostMapping("/file/upload")
    public Result<TrCheckFile> uploadLargeFile(TqUploadShareFile req)throws IOException {
        return Result.success(UploadFileUtil.shareUpload(dictionaryCache,req));
    }

    /**
     * 检查文件是否已经存在：根据传过来的MD5 和路径查询。
     */
    @ApiOperation(value = "检查文件是否已经存在：根据传过来的MD5 和路径查询")
    @GetMapping("/file/checkFile")
    public Result<TrCheckFile> checkFile(TqCheckFile req){
        return Result.success(UploadFileUtil.exitsFile(dictionaryCache,req));
    }


    @Value("${maxAuditComment:5}")
    private int maxAuditComment;

    /**
     * 提交评论
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "提交评论")
    @PostMapping("comment/submit")
    public Result<Boolean> submitComment(@RequestBody TqSubmitComment req) {
        TbMemBase typeMem = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getUserId(),
                TsMemBase.Fields.type,
                TsMemBase.Fields.level
        ));
        if (!CommonUtil.isEqual(typeMem.getLevel(), MemEnumContainer.MemLevel.蓝色VIP.getValue())
                &&
                !CommonUtil.isEqual(typeMem.getLevel(), MemEnumContainer.MemLevel.金色VIP.getValue())
        ) {
            return Result.miss(CommonEnumContainer.ResultStatus.THE_DATA_STATE_IS_INCORRECT, "只有蓝色vip或者金色vip才允许评论");
        }
        int noAuditCount = userCommentService.selectCount(
                new QueryWrapper()
                        .eq(TsUserComment.Fields.commentUserId, req.getUserId())
                        .eq(TsUserComment.Fields.auditResult, CommonEnumContainer.CustomerAuditState.TO_AUDIT.getValue())
        );
        if (noAuditCount > maxAuditComment) {
            return Result.miss(CommonEnumContainer.ResultStatus.THE_DATA_STATE_IS_INCORRECT, "只能提交5条未审核的评论");
        }
        if (CommonUtil.isEqual(req.getType(), CommonEnumContainer.CommentType.ORGANIZATION.getValue())) {

        } else if (CommonUtil.isEqual(req.getType(), CommonEnumContainer.CommentType.PRODUCT.getValue())) {

        } else if (CommonUtil.isEqual(req.getType(), CommonEnumContainer.CommentType.FUND_MANAGER.getValue())) {

        } else {
            //路演
        }
        TbUserComment comment = new TbUserComment();
        comment.setAuditResult(CommonEnumContainer.CustomerAuditState.TO_AUDIT.getValue());
        comment.setCommentUserId(req.getUserId());
        comment.setCommentUserName(req.getUserInfo().getUserName());
        comment.setContent(req.getContent());
        comment.setType(req.getType());
        comment.setTargetId(req.getTargetId());
        comment.setVisible(false);
        userCommentService.insert(new TqUserComment().setEntity(comment));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 提交回复
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "提交回复")
    @PostMapping("commentReply/submit")
    public Result<Boolean> submitCommentReply(@RequestBody TqSubmitCommentReply req) {
        //查出评论对象
        TbUserComment comment = userCommentService.selectOne(
                WrapperFactory.queryWrapper().select("id,targetId,type")
                        .eq(TsUserComment.Fields.id, req.getCommentId())
                        .eq(TsUserComment.Fields.auditResult, CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())
                        .eq(TsUserComment.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        if (null == comment) {
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST, "评论不存在");
        }
        //查看是否已经有回复（待审核、审核通过、再次提交）
        int has = userCommentReplyService.selectCount(
                WrapperFactory.queryWrapper()
                        .eq(TsUserCommentReply.Fields.commentId, req.getCommentId())
                        .eq(TsUserCommentReply.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                        .notIn(TsUserCommentReply.Fields.auditResult, Collections.singletonList(CommonEnumContainer.CustomerAuditState.NOT_APPROVED.getValue()))
        );
        if (has > 0) {
            return Result.miss(CommonEnumContainer.ResultStatus.REPEATING_DATA, "已有回复或待审核回复");
        }
        TbMemBase typeMem = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getUserId(),
                TsMemBase.Fields.type,
                TsMemBase.Fields.level
        ));
        if (!CommonUtil.isEqual(typeMem.getLevel(), MemEnumContainer.MemLevel.蓝色VIP.getValue())
                &&
                !CommonUtil.isEqual(typeMem.getLevel(), MemEnumContainer.MemLevel.金色VIP.getValue())
        ) {
            return Result.miss(CommonEnumContainer.ResultStatus.THE_DATA_STATE_IS_INCORRECT, "只有蓝色vip或者金色vip才允许评论和回复");
        }
        //如果是机构且是直营店且当前用户是管理者
        String replyUserName = req.getUserInfo().getUserName();
        if (CommonUtil.isEqual(CommonEnumContainer.CommentType.ORGANIZATION.getValue(), comment.getType())) {
            if (directStoreOrgService.selectCount(
                    WrapperFactory.queryWrapper()
                            .eq(TsDirectStoreOrg.Fields.partyId, comment.getTargetId())
                            .eq(TsDirectStoreOrg.Fields.userId, req.getUserId())
            ) > 0) {
                replyUserName = "店主";
            }
        }
        TbUserCommentReply reply = new TbUserCommentReply();
        reply.setAuditResult(CommonEnumContainer.CustomerAuditState.TO_AUDIT.getValue());
        reply.setReplyUserId(req.getUserId());
        reply.setReplyUserName(replyUserName);
        reply.setContent(req.getContent());
        reply.setCommentId(comment.getId());
        userCommentReplyService.insert(new TqUserCommentReply().setEntity(reply));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 提交意见反馈
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "提交意见反馈")
    @PostMapping("feedback/submit")
    public Result<Boolean> feedbackSubmit(@RequestBody TqSubmitFeedback req) {
        TbUserFeedBack feedBack = new TbUserFeedBack();
        feedBack.setContent(req.getContent());
        feedBack.setLink(req.getLink());
        feedBack.setResult(CommonEnumContainer.ReservationDealStatus.UNTREATED.getValue());
        feedBack.setTitle(req.getTitle());
        feedBack.setVisible(CommonEnumContainer.YesOrNo.RIGHT.getValue());
        feedBack.setSubmitUserId(req.getUserId());
        userFeedBackService.insert(new TqUserFeedBack().setEntity(feedBack));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 我提交的意见反馈记录
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "我提交的意见反馈记录")
    @GetMapping("feedback/list")
    public Result<TvPageList<TvUserFeedBack>> feedbackSubmit(TqMyFeedback req) {
        if (null == req.getPageInfo()) {
            req.setPageInfo(new PageInfo());
        }
        MultiTableQueryWrapper queryWrapper = userFeedBackService.getMultiCommonWrapper()
                .eq(CompareAlias.valueOf(TsUserFeedBack.Fields.submitUserId, "e"), req.getUserId())
                .eq(CompareAlias.valueOf(TsUserFeedBack.Fields.visible, "e"), CommonEnumContainer.YesOrNo.RIGHT.getValue())
                .eq(CompareAlias.valueOf(TsUserFeedBack.Fields.deltag, "e"), CommonEnumContainer.Deltag.NORMAL.getValue())
                .page(req.getPageInfo());
        return Result.success(userFeedBackService.selectPageList(queryWrapper));
    }

    /**
     * 金V/蓝V 信息
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "金V/蓝V 信息")
    @GetMapping("vipInfo")
    public Result<UserVipInfo> vipInfo(TqUserAuthBase req) {
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbMemBase.class)
                .select("e.level,e.userName,e.realName,e.nickName,e.headPortrait,e.id userId")
                .select0(
                        SelectAlias.valueOf("(select end_date from tb_user_vip_end where user_id=e.id and deltag = false limit 1) endDate", true)
                        , SelectAlias.valueOf("ifnull(ds.party_id,so.party_id) partyId", true)
                        , SelectAlias.valueOf("(select count(DISTINCT investor_user_id) from tb_direct_store_service_history where customer_user_id=e.id and audit_result in(0,1)) serviceUserCount", true)
                        , SelectAlias.valueOf("(select count(id) from tb_direct_store_service_history where customer_user_id=e.id and audit_result in(0)) orders", true)
                        , SelectAlias.valueOf("(select count(id) from tb_road_show_info where upload_user_id = e.id and state = 1) userRoadshowCount", true)
                        , SelectAlias.valueOf("case when ds.type = 0 then true else false end customer", true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbDirectStoreUser.class, "ds"), ds ->
                        ds.eqc(CompareAlias.valueOf("ds.userId"), CompareAlias.valueOf("e.id")))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbDirectStoreOrg.class, "so"), ds ->
                        ds.eqc(CompareAlias.valueOf("so.userId"), CompareAlias.valueOf("e.id")))
                .eq(CompareAlias.valueOf("e.id"), req.getUserId());
        UserVipInfo vipInfo = peopleInfoMapper.selectUserVipInfo(queryWrapper);
        //是否开通直营店，开通直营店partyId 不为null
        if (vipInfo.getPartyId() != null) {
            TbMdInstitution mdInstitution = mdInstitutionService.selectOne(WrapperFactory.queryWrapper()
                    .eq(TsMdInstitution.Fields.partyId, vipInfo.getPartyId())
            );
            vipInfo.setOrgVip(true);
            vipInfo.setPartyShortName(mdInstitution.getPartyShortName());
        } else {
            //未开通,查看机构代理，如果没有机构代理则机构为null
            TvOrgProxy orgProxy = orgProxyService.selectOneView(WrapperFactory.multiQueryWrapper()
                    .select("e.partyId,mi.partyShortName")
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class, "mi"), md ->
                            md.eqc(CompareAlias.valueOf("mi.partyId"), CompareAlias.valueOf("e.partyId"))
                    )
                    .eq(CompareAlias.valueOf("e.userId"), req.getUserId())
            );
            if (null != orgProxy) {
                vipInfo.setPartyShortName(orgProxy.getPartyShortName());
                vipInfo.setPartyId(orgProxy.getPartyId());
            }
        }
        return Result.success(vipInfo);
    }

    /**
     * 申请蓝vip
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "申请蓝vip")
    @PostMapping("vip2/app")
    public Result<Boolean> vipInfo2(@RequestBody TqAppVip2 req) {
        TbUserVipEnd userVipEnd = userVipEndService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserVipEnd.Fields.userId, req.getUserId())
                        .eq(TsUserVipEnd.Fields.deltag, false)
                        .eq(TsUserVipEnd.Fields.level, MemEnumContainer.MemLevel.蓝色VIP.getValue())
                        .ge(TsUserVipEnd.Fields.endDate, new Date())
        );
        if (null != userVipEnd) {
            return Result.miss(CommonEnumContainer.ResultStatus.REPEATING_DATA, "用户已经是蓝V");
        }

        TbUserVip2Apply apply = userVip2ApplyService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserVip2Apply.Fields.appUserId, req.getUserId())
                        .eq(TsUserVip2Apply.Fields.auditResult, CommonEnumContainer.CustomerAuditState.TO_AUDIT.getValue())
        );
        if (null != apply) {
            return Result.miss(CommonEnumContainer.ResultStatus.REPEATING_DATA, "已经申请过蓝V");
        }
        apply = new TbUserVip2Apply();
        BeanUtils.copyProperties(req, apply);
        apply.setAppUserId(req.getUserId());
        apply.setAuditResult(CommonEnumContainer.CustomerAuditState.TO_AUDIT.getValue());

        userVip2ApplyService.insert(new TqUserVip2Apply().setEntity(apply));

        return Result.success(true);
    }

    /**
     * 申请金色vip
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "申请金色vip")
    @PostMapping("vip1/app")
    public Result<Boolean> vipInfo1(@RequestBody TqAppVip1 req) {
        TbUserVipEnd userVipEnd = userVipEndService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserVipEnd.Fields.userId, req.getUserId())
                        .eq(TsUserVipEnd.Fields.deltag, false)
                        .ge(TsUserVipEnd.Fields.endDate, new Date())
        );
        if (null != userVipEnd) {
            return Result.miss(CommonEnumContainer.ResultStatus.REPEATING_DATA, "用户已经是"
                    + EnumHelperUtil.getByValue(MemEnumContainer.MemLevel.class, userVipEnd.getLevel()).getDesc());
        }

        TbUserVip1Apply apply = userVip1ApplyService.selectOne(
                WrapperFactory.queryWrapper().eq(TsUserVip1Apply.Fields.appUserId, req.getUserId())
                        .eq(TsUserVip1Apply.Fields.auditResult, CommonEnumContainer.CustomerAuditState.TO_AUDIT.getValue())
        );
        if (null != apply) {
            return Result.miss(CommonEnumContainer.ResultStatus.REPEATING_DATA, "已经申请过金V");
        }
        apply = new TbUserVip1Apply();
        BeanUtils.copyProperties(req, apply);
        apply.setAppUserId(req.getUserId());
        apply.setAuditResult(CommonEnumContainer.CustomerAuditState.TO_AUDIT.getValue());

        userVip1ApplyService.insert(new TqUserVip1Apply().setEntity(apply));

        return Result.success(true);
    }


    /**
     * 我的预约列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "我的预约列表")
    @GetMapping("reservation/list")
    public Result<TvPageList<TvUserReservation>> myReservations(TqReservationList req) {
        TbMemBase user = memBaseService.selectOne(WrapperFactory.queryWrapper().eq(TsMemBase.Fields.id, req.getUserId()));
        MultiTableQueryWrapper queryWrapper = userReservationService.getMultiCommonWrapper()
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .or(or -> or
                        .eq(CompareAlias.valueOf("e.reservationUserId"), req.getUserId())
                        .eq(CompareAlias.valueOf("e.phone"), user.getPhone())
                )
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                .page(req.getPageInfo());
        if (null != req.getDealStatus()) {
            queryWrapper.where(FieldValue.fromCondition("e.dealStatus.eq", req.getDealStatus()));
        }
        if (null != req.getType()) {
            queryWrapper.where(FieldValue.fromCondition("e.type.eq", req.getType()));
        }
        TvPageList<TvUserReservation> pageList = userReservationService.selectPageList(queryWrapper);
        for (TvUserReservation v : pageList.getList()) {
            if (CommonUtil.isEqual(v.getType(), CommonEnumContainer.ReservationType.ORGANIZATION.getValue())) {
                v.setInfo(v.getReservationOrgInfo());
            } else if (CommonUtil.isEqual(v.getType(), CommonEnumContainer.ReservationType.PRODUCT.getValue())) {
                v.setInfo(v.getReservationProInfo());
            } else if (CommonUtil.isEqual(v.getType(), CommonEnumContainer.ReservationType.FUND_MANAGER.getValue())) {
                v.setInfo(v.getReservationManageInfo());
            }
        }
        return Result.success(pageList);
    }

    /**
     * 我的预约详情
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "我的预约详情")
    @GetMapping("reservation/detail")
    public Result<TvUserReservation> myReservationDetail(TqUpdateReservation req) {
        TbMemBase user = memBaseService.selectOne(WrapperFactory.queryWrapper().eq(TsMemBase.Fields.id, req.getUserId()));
        TvUserReservation reservation = userReservationService.selectOneView(
                WrapperFactory.multiQueryWrapper()
                        .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                        .eq(CompareAlias.valueOf("e.id"), req.getId())
                        .or(or -> or
                                .eq(CompareAlias.valueOf("e.reservationUserId"), req.getUserId())
                                .eq(CompareAlias.valueOf("e.phone"), user.getPhone())
                        )
        );
        if (null == reservation) {
            throw new NoExistsException("预约不存在");
        }
        if (CommonUtil.isEqual(reservation.getType(), CommonEnumContainer.ReservationType.ORGANIZATION.getValue())) {
            reservation.setInfo(reservation.getReservationOrgInfo());
        } else if (CommonUtil.isEqual(reservation.getType(), CommonEnumContainer.ReservationType.PRODUCT.getValue())) {
            reservation.setInfo(reservation.getReservationProInfo());
        } else if (CommonUtil.isEqual(reservation.getType(), CommonEnumContainer.ReservationType.FUND_MANAGER.getValue())) {
            reservation.setInfo(reservation.getReservationManageInfo());
        }
        return Result.success(reservation);
    }

    /**
     * 再次提醒客服
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "再次提醒客服")
    @PostMapping("reservation/remindAgain")
    public Result<Boolean> reservationRemindAgain(@RequestBody TqUpdateReservation req) {
        TbMemBase user = memBaseService.selectOne(WrapperFactory.queryWrapper().eq(TsMemBase.Fields.id, req.getUserId()));
        TbUserReservation reservation = userReservationService.selectOne(
                WrapperFactory.queryWrapper().select(TsUserReservation.Fields.id, TsUserReservation.Fields.dealStatus)
                        .eq(TsUserReservation.Fields.id, req.getId())
                        .or(or -> or
                                .eq(TsUserReservation.Fields.reservationUserId, req.getUserId())
                                .eq(TsUserReservation.Fields.phone, user.getPhone())
                        )
        );
        if (null == reservation) {
            throw new NoExistsException("预约不存在");
        }
        if (CommonUtil.isEqual(reservation.getDealStatus(), CommonEnumContainer.DealStatus.PROCESSED.getValue())) {
            throw new DuplicateDataException("已经处理过该预约");
        }
        TbUserReservation up = new TbUserReservation();
        up
                .setId(req.getId())
                .setUpdateDate(new Date())
        ;
        userReservationService.updateByPrimary(new TqUserReservation().setEntity(up));
        return Result.success(true);
    }


}
