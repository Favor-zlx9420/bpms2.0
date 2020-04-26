package com.rong.console.center.controller.mem;

import com.rong.cache.service.SMSVerifyFrequentCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.StringUtil;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.user.module.entity.TbMessageHistory;
import com.rong.user.module.query.TsMessageHistory;
import com.rong.user.module.request.TqMessageHistory;
import com.rong.user.module.view.TvMessageHistory;
import com.rong.user.service.MessageHistoryService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.MESSAGE_HISTORY_PATH)
public class MessageHistoryController extends BaseController<TbMessageHistory, TqMessageHistory, TvMessageHistory, MessageHistoryService> {
    @Autowired
    private SMSVerifyFrequentCache smsVerifyFrequentCache;
    @Autowired
    public MessageHistoryController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.MESSAGE_HISTORY_PATH)
                .build()
                , TsMessageHistory.Fields.id
        );
    }

    @Override
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqMessageHistory req) throws Exception {
        TbMessageHistory history = service.selectItemByPrimaryKey(IdWrapper.valueOf(req.getEntity().getId()));
        for(CommonEnumContainer.TripartiteMessageContentType contentType: CommonEnumContainer.TripartiteMessageContentType.values()){
            String cacheKey = history.getTarget() + "_" + contentType.getValue();
            smsVerifyFrequentCache.removeFromServer(cacheKey);
        }
        return Result.success();
    }

    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request) {
        super.wrapQuery(queryWrapper, request);
        String keyword = request.getParameter("keyword");
        if(StringUtil.isNotEmpty(keyword)){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("mb.userName"),keyword)
                    .like(CompareAlias.valueOf("e.target"),keyword)
                    .like(CompareAlias.valueOf("mb.realName"),keyword)
            );
        }
    }
}
