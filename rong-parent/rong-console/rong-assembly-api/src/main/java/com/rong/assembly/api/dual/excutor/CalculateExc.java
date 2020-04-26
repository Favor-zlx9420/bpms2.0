package com.rong.assembly.api.dual.excutor;

import com.rong.fundmanage.mapper.RaisedFundCurrentNavGrMapper;
import com.rong.fundmanage.mapper.RaisedFundCurrentNavMapper;
import com.rong.fundmanage.mapper.RaisedFundCurrentPerformanceMapper;
import com.rong.fundmanage.service.PrivateFundsCurrentGrService;
import com.rong.fundmanage.service.PrivateFundsCurrentIncomeService;
import com.rong.fundmanage.service.PrivateFundsCurrentNavService;
import com.rong.fundmanage.service.PrivateFundsCurrentPerfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CalculateExc {
    @Autowired
    private PrivateFundsCurrentIncomeService privateFundsCurrentIncomeService;
    @Autowired
    private PrivateFundsCurrentNavService privateFundsCurrentNavService;
    @Autowired
    private PrivateFundsCurrentPerfService privateFundsCurrentPerfService;
    @Autowired
    private PrivateFundsCurrentGrService privateFundsCurrentGrService;
    @Autowired
    private RaisedFundCurrentNavMapper raisedFundCurrentNavMapper;
    @Autowired
    private RaisedFundCurrentPerformanceMapper raisedFundCurrentPerformanceMapper;
    @Autowired
    private RaisedFundCurrentNavGrMapper raisedFundCurrentNavGrMapper;

    /**
     * 每天统计一次基金净值 私募
     */
    @Scheduled(cron = "10 10 0 * * *")
    public void resetPrivateNav(){
        privateFundsCurrentNavService.resetNav();
    }
    /**
     * 每天统计一次基金净值 公募
     */
    @Scheduled(cron = "10 12 0 * * *")
    public void resetRaisedNav(){
        raisedFundCurrentNavMapper.resetNavTemp();
        if (raisedFundCurrentNavMapper.resetNavTempCount() > 0) {
            raisedFundCurrentNavMapper.resetNav();
        }
    }

    /**
     * 每天统计一次基金收益 私募
     */
    @Scheduled(cron = "10 30 0 * * *")
    public void resetPrivateIncome(){
        privateFundsCurrentIncomeService.resetIncome();
    }
    /**
     * 每天统计一次基金 基金业绩 公募
     */
    @Scheduled(cron = "10 22 0 * * *")
    public void resetRaisedPerformance(){
        raisedFundCurrentPerformanceMapper.resetPerformanceTemp();
        if (raisedFundCurrentPerformanceMapper.resetPerformanceTempCount() > 0) {
            raisedFundCurrentPerformanceMapper.resetPerformance();
        }
    }
    /**
     * 每天统计一次基金净值增长情况 公募
     */
    @Scheduled(cron = "10 32 0 * * *")
    public void resetRaisedNavGr(){
        raisedFundCurrentNavGrMapper.resetNavGrTemp();
        if (raisedFundCurrentNavGrMapper.resetNavGrTempCount() > 0) {
            raisedFundCurrentNavGrMapper.resetNavGr();
        }
    }

    /**
     * 每天统计一次私募基金性能简表
     */
    @Scheduled(cron = "10 40 0 * * *")
    public void resetPrivatePerf(){
        privateFundsCurrentGrService.resetPrivatePerf();
    }

    /**
     * 每天统计一次性能指标 私募(基于上面的性能简表)
     */
    @Scheduled(cron = "10 50 0 * * *")
    public void resetPerf(){
        privateFundsCurrentPerfService.resetPerf();
    }

    /**
     * 每天统计一次基金净值增长
     */
    @Scheduled(cron = "10 20 0 * * *")
    public void resetGr(){
        privateFundsCurrentGrService.resetGr();
    }

    /**
     * 每天统计一次基金每年净值增长
     */
    @Scheduled(cron = "10 25 0 * * *")
    public void resetYear(){
        privateFundsCurrentGrService.resetYear();
    }

    /**
     * 每天统计一次基金公司排行榜
     */
    @Scheduled(cron = "10 10 1 * * *")
    public void restCompanyRanking(){
        privateFundsCurrentGrService.restCompanyRanking();
    }

    /**
     * 每天统计一次基金经理排行榜
     */
    @Scheduled(cron = "10 20 1 * * *")
    public void restManagerRanking(){
        privateFundsCurrentGrService.restManagerRanking();
    }

    /**
     * 每天统计一次私募基金排行榜
     */
    @Scheduled(cron = "10 30 1 * * *")
    public void restPfundRanking(){
        privateFundsCurrentGrService.restPfundRanking();
    }


    /**
     * 每天删除七天前的新闻摘要
     */
    @Scheduled(cron = "10 15 0 * * *")
    public void deleteNewsSummary(){
        privateFundsCurrentGrService.deleteNewsSummary();
    }
}
