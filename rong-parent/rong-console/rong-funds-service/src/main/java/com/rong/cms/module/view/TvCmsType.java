package com.rong.cms.module.view;

import com.rong.cms.module.entity.TbCmsDynProper;
import com.rong.cms.module.entity.TbCmsType;
import lombok.Data;

import java.util.List;

@Data
public class TvCmsType extends TbCmsType {
    /**
     * 额外属性
     */
    private List<TbCmsDynProper> dyns;
}