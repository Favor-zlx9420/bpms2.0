package com.rong.cms.module.request;

import com.rong.cms.module.entity.TbCmsDynProper;
import com.rong.cms.module.entity.TbCmsType;
import com.rong.common.module.BaseRequest;
import lombok.Data;

import java.util.List;

@Data
public class TqCmsType extends BaseRequest<TbCmsType, TqCmsType> {
    /**
     * 额外属性
     */
    private List<TbCmsDynProper> dyns;
    private List<Long> delDynIds;
}
