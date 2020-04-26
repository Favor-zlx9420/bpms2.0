package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.mapper.UserReservationMapper;
import com.rong.member.module.entity.TbUserReservation;
import com.rong.member.module.request.TqUserReservation;
import com.rong.member.module.view.TvUserReservation;
import com.rong.member.service.UserReservationService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.stereotype.Service;

@Service
public class UserReservationServiceImpl extends BasicServiceImpl<TbUserReservation, TqUserReservation, TvUserReservation, UserReservationMapper> implements UserReservationService {
    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return super.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select0(
                        //机构
                        SelectAlias.valueOf("(select PARTY_SHORT_NAME from `tong-rong`.`md_institution` where PARTY_ID=e.target_id and e.type=0) as reservationOrgInfo",true)
                        ,//基金经理
                        SelectAlias.valueOf("(select NAME from `tong-rong`.`md_people` where PERSON_ID=e.target_id and e.type=1) as reservationManageInfo",true)
                        ,//产品
                        SelectAlias.valueOf("(select SEC_SHORT_NAME from `tong-rong`.`md_security` where SECURITY_ID=e.target_id and e.type=2) as reservationProInfo",true)
                        ,//路演文档
                        SelectAlias.valueOf("(select title from tb_road_show_info where id=e.target_id and e.type=3) as roadshowInfo",true)
                        ,//合格投资者
                        SelectAlias.valueOf("(CASE WHEN (select id from tb_investor_qualified where user_id=e.reservation_user_id and state=1) IS NOT NULL THEN 1 ELSE 0 END) as userType",true)
                        ,//处理人
                        SelectAlias.valueOf("(select user_name from tb_mem_base where id=e.dual_user_id) as dualUserName",true)
                )
                ;
    }
}