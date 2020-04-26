package com.rong.assembly.api.dual.excutor;

import com.rong.common.util.WrapperFactory;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class customerExc {
    @Value("${spring.profiles.active}")
    private String env;
    @Autowired
    private LabelService labelService;
    /**
     * 每天1时10分查看一下有哪些用户vip等级到期了的，将到期用户更新为无
     */
    @Scheduled(cron = "35 * * * * *")
    public void test(){
        if("dev".equals(env)) {
            labelService.selectOne(WrapperFactory.queryWrapper().select(TsLabel.Fields.id).eq(TsLabel.Fields.id, 0));
        }
    }
}
