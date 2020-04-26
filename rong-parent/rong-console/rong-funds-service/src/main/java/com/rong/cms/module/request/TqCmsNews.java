package com.rong.cms.module.request;

import com.rong.cms.module.entity.TbCmsDynproVal;
import com.rong.cms.module.entity.TbCmsNews;
import com.rong.common.module.BaseRequest;
import lombok.Data;

import java.util.List;

@Data
public class TqCmsNews extends BaseRequest<TbCmsNews, TqCmsNews> {

    /**
     * 额外属性
     */
    private List<TbCmsDynproVal> dynList;
    private boolean changeDyn;//是否更新属性
}