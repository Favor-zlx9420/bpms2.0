package com.rong.assembly.api.dual.excutor;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.service.MemBaseService;
import com.rong.user.module.entity.TbUserVipEnd;
import com.rong.user.module.query.TsUserVipEnd;
import com.rong.user.service.UserVipEndService;
import com.vitily.mybatis.core.entity.Element;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.Elements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UserVipExc {
    @Autowired
    private MemBaseService memBaseService;
    @Autowired
    private UserVipEndService userVipEndService;
    /**
     * 每天1时10分查看一下有哪些用户vip等级到期了的，将到期用户更新为无
     */
    @Scheduled(cron = "10 10 1 * * *")
    public void resetNav(){
        Date now = new Date();
        List<TbUserVipEnd> expires = userVipEndService.selectList(
                new QueryWrapper()
                        .select(
                                TsUserVipEnd.Fields.userId, TsUserVipEnd.Fields.id
                        )
                        .eq(TsUserVipEnd.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                        .le(TsUserVipEnd.Fields.endDate,now)
        );
        List<Element> ups = new ArrayList<>();
        ups.add(Elements.valueOf(TsMemBase.Fields.updateDate,now));
        ups.add(Elements.valueOf(TsMemBase.Fields.level, MemEnumContainer.MemLevel.无.getValue()));
        for(TbUserVipEnd vip:expires){
            vip.setUpdateDate(now);
            vip.setDeltag(CommonEnumContainer.Deltag.DELETED.getValue());
            userVipEndService.updateSelectiveByPrimaryKey(vip);
            memBaseService.updateSelectItem(
                    new UpdateWrapper().update(ups).eq(TsMemBase.Fields.id,vip.getUserId())
            );
            //发送消息
        }
    }
}
