package com.rong.tong.pfunds.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import lombok.Data;

@Data
public class TqMdInstitution extends BaseRequest<TbMdInstitution, TqMdInstitution> {
    private Boolean visible;
    private Boolean recommend;
    private Boolean hotSearch;
}