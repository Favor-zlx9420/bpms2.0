package com.rong.member.util;

import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.member.module.entity.TbMemCompanyCreditfile;
import com.rong.member.module.entity.TbMemCompanyUserinfo;
import com.rong.member.module.entity.TbMemPersonalCreditfile;
import com.rong.member.module.entity.TbMemPersonalUserinfo;
import com.rong.member.module.view.TvMemBase;

/**
 * 会员相关
 * @author lether
 *
 */
public class MemberUtil {
	public static CommonServiceCache getMemCache(CommonServiceCache commonServiceCache){
		return commonServiceCache.getInstance(DictionaryKey.Keys.MEMBER_OF_THE_TOKEN, CacheSerializableDelegate.jsonSeriaze());
	}
	public static void checkMemberName(String name,String phone,String email){
		if(CommonUtil.isPhone(name) && !StringUtil.isEmpty(phone) && !CommonUtil.isEqual(name, phone)){
			throw new CustomerException("登录名为手机号但是跟用户手机号不一致", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
		}else if(CommonUtil.isEmail(name) && !StringUtil.isEmpty(email) && !CommonUtil.isEqual(name, email)){
			throw new CustomerException("登录名为Email但是跟用户Email不一致", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
		}
	}

	public static void outputMemBase(TvMemBase e){
		e.setUpdateDate(null);
		e.setDeltag(null);
		e.setSort(null);
		e.setSalt(null);
		e.setPassword(null);
		e.setQuestion(null);
		e.setAnswer(null);
		//outputMemPersonalInfo(e.getPersonalUserinfo());
		//outputMemCompanyUserinfo(e.getCompanyUserinfo());
	}
	public static void outputMemPersonalInfo(TbMemPersonalUserinfo e){
		if(CommonUtil.isNotNull(e)) {
			e.setUpdateDate(null);
			e.setDeltag(null);
		}
	}
	public static void outputMemPersonalCreditfile(TbMemPersonalCreditfile e){
		if(CommonUtil.isNotNull(e)) {
			e.setUpdateDate(null);
			e.setDeltag(null);
		}
	}
	public static void outputMemCompanyUserinfo(TbMemCompanyUserinfo e){
		if(CommonUtil.isNotNull(e)) {
			e.setUpdateDate(null);
			e.setDeltag(null);
		}
	}
	public static void outputMemCompanyCreditfile(TbMemCompanyCreditfile e){
		if(CommonUtil.isNotNull(e)) {
			e.setUpdateDate(null);
			e.setDeltag(null);
		}
	}
}
