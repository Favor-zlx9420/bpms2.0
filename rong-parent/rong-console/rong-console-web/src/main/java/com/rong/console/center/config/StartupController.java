package com.rong.console.center.config;

import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.sys.mapper.LabelMapper;
import com.rong.sys.module.entity.TbImageSams;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.module.query.TsDictionary;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.module.view.TvDictionary;
import com.rong.sys.service.DictionaryService;
import com.rong.sys.service.ImageSamsService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableScheduling
@ComponentScan({"com.rong.console.center.config","com.rong.console.center.dual.exc"})
@Component
@Slf4j
public class StartupController implements CommandLineRunner {

    @Value("${spring.application.name}")
    String applicationName;
    @Value("${spring.profiles.active}")
    String projectEnv;
    private final DictionaryService dictionaryService;
    private final DictionaryCache dictionaryCache;
    private final ImageSamsService imageSamsService;
    private final ViyBasicCache viyBasicCache;

    public StartupController(DictionaryService dictionaryService, DictionaryCache dictionaryCache, ImageSamsService imageSamsService, ViyBasicCache viyBasicCache) {
        this.dictionaryService = dictionaryService;
        this.dictionaryCache = dictionaryCache;
        this.imageSamsService = imageSamsService;
        this.viyBasicCache = viyBasicCache;
    }

    @Override
    public void run(String... args){
        try {
            log.info(" >>>>>>>>>>>>> 服务[" + applicationName + "]，环境[" + projectEnv + "]启动中，正在加载数据等操作 <<<<<<<<<<<<< ");
            List<TvDictionary> dictionaries = dictionaryService.selectViewList(new MultiTableQueryWrapper().eq(
                    CompareAlias.valueOf(TsDictionary.Fields.state)
                    , CommonEnumContainer.State.NORMAL.getValue()));
            List<com.rong.cache.foreign.TbDictionary> fds = new ArrayList<>();
            for (TvDictionary d : dictionaries) {
                com.rong.cache.foreign.TbDictionary fd = new com.rong.cache.foreign.TbDictionary();
                BeanUtils.copyProperties(d, fd);
                fds.add(fd);
            }
            dictionaryCache.init(fds);
            //监听信息收集器
            viyBasicCache.subscribeMulToOne(DictionaryKey.ViyCacheSubstrTopic.PICTURE_COLLECTOR.getValue() + "-", receiveMessage -> {
                TbImageSams imageSams = JSONUtil.parseObject(receiveMessage, TbImageSams.class);
                imageSams.setCreateDate(new Date());
                imageSams.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
                imageSamsService.insertSelective(imageSams);
            });

            log.info(" >>>>>>>>>>>>> 初始化工作完毕 <<<<<<<<<<<<< ");
            //testLabel();
        } catch (Exception e) {
            Result result = CommonUtil.getResultByThrowable(e);
            log.error(" !!!!!!!!!!!!! 初始化工作错误,详细信息：" + JSONUtil.toJSONString(result.getMessage()) + " !!!!!!!!!!!!! ");
        }
    }


    @Autowired
    private LabelMapper labelMapper;
    private void testLabel(){
        @Data
        class TQQ extends QueryInfo<TbLabel> {
            private String ids;
        }
        TQQ query = new TQQ();
        query.setEntity(new TbLabel());
        query.getEntity().setId(20L);
        log.info("count0000000000:" + labelMapper.getCountPaging(query));
        log.info("list:" + JSONUtil.toJSONString(labelMapper.getListByBean(query)));
        query.setPageInfo(new PageInfo());
        log.info("count111111111111111:" + labelMapper.getCountPaging(query));
        log.info("list:" + JSONUtil.toJSONString(labelMapper.getListByBean(query)));
        query.orderBy(OrderBy.valueOf(Order.DESC, TsLabel.Fields.state))
             .orderBy(OrderBy.valueOf(Order.ASC, TsLabel.Fields.id))
                .orderBy(OrderBy.valueOf(Order.ASC, TsLabel.Fields.name))
                .orderBy(OrderBy.valueOf(Order.DESC, TsLabel.Fields.createDate));
        log.info("count2222222222222222222:" + labelMapper.getCountPaging(query));
        log.info("list:" + JSONUtil.toJSONString(labelMapper.getListByBean(query)));
        query.setIds("1,2,3");
        log.info("count33333333333333:" + labelMapper.getCountPaging(query));
        log.info("list:" + JSONUtil.toJSONString(labelMapper.getListByBean(query)));

    }

}
