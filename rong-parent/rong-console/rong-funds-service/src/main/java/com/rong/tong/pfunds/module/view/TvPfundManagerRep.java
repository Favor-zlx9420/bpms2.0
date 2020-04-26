package com.rong.tong.pfunds.module.view;

import com.rong.tong.pfunds.module.entity.TbPfundManagerRep;
import lombok.Data;

@Data
public class TvPfundManagerRep extends TbPfundManagerRep {
    private String securityFullName;
    private String securityShortName;
}