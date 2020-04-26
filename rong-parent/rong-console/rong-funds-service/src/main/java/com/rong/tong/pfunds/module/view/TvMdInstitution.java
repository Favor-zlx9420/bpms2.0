package com.rong.tong.pfunds.module.view;

import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import lombok.Data;

@Data
public class TvMdInstitution extends TbMdInstitution {
    private Boolean visible = Boolean.FALSE;
    private Boolean recommend = Boolean.FALSE;
    private Boolean hotSearch = Boolean.FALSE;

    private String regCityInfo;
}