package com.rong.sys.module.view;

import com.rong.sys.module.entity.TbRegion;
import lombok.Data;

@Data
public class TvRegion extends TbRegion {
    private boolean hasSon;
    private String parentName;
}