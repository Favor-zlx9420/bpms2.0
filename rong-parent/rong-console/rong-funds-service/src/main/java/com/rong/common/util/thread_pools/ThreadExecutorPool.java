package com.rong.common.util.thread_pools;

import com.rong.common.util.CommonUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 执行普通任务线程池,定时任务不允许使用该类
 */
@Slf4j
public class ThreadExecutorPool {

    private static ThreadExecutorPool instance = new ThreadExecutorPool();
    private static volatile boolean _init = false;
    public static ThreadExecutorPool getInstance(){
        if(!_init){
            throw new RuntimeException("executorPool not init.");
        }
        return instance;
    }
    public static void loadConfig(){
        loadConfig(new Config());
    }
    public static void loadConfig(Config config){
        instance.init(config);
    }
    private ThreadExecutorPool(){}
    private void init(Config config){
        _executor = new ThreadPoolTaskExecutor();
        _executor.setCorePoolSize(config.corePoolSize);
        _executor.setMaxPoolSize(config.maxPoolSize);
        _executor.setKeepAliveSeconds(config.keepAliveSeconds);
        _executor.setWaitForTasksToCompleteOnShutdown(config.waitForJobsToCompleteOnShutdown);
        _executor.setAwaitTerminationSeconds(config.awaitTerminationSeconds);
        _executor.setThreadNamePrefix(config.threadNamePrefix);
        _executor.setRejectedExecutionHandler(config.rejectedExecutionHandler);
        _executor.setQueueCapacity(config.queueCapacity);
        _executor.initialize();
        _init = true;
    }
    private ThreadPoolTaskExecutor _executor;
    public ThreadPoolTaskExecutor getPools(){
        return _executor;
    }
    private List<Runnable> beforeShutdownEvents = new ArrayList<>();
    public void execute(Runnable runnable){
       _executor.execute(runnable);
    }
    public void execute(Runnable runnable,Runnable closeRunnable){
        _executor.execute(runnable);
        beforeShutdownEvents.add(closeRunnable);
    }
    @PreDestroy
    public void shutdown0(){
        for(Runnable runnable:beforeShutdownEvents){
            runnable.run();
        }
        if(CommonUtil.isNotNull(_executor)) {
            _executor.shutdown();
            _executor.destroy();
        }
    }
    @Data
    @Accessors(chain = true)
    public static class Config{
        /**
         * 核心线程池
         * 至少要找出所有一直运行的线程->监听线程；然后在监听线程数量+x
         * 不然会把后续的操作放入列队中死等
         */
        private int corePoolSize = 6;
        private int maxPoolSize = 30;
        private int keepAliveSeconds = 60;
        private boolean waitForJobsToCompleteOnShutdown = true;
        private int awaitTerminationSeconds = 20;
        private int queueCapacity = 20;
        private String threadNamePrefix;
        private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
    }
}
