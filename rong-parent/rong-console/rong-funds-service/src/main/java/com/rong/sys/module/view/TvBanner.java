package com.rong.sys.module.view;

import com.rong.sys.module.entity.TbBanner;
import lombok.Data;

import java.util.List;

@Data
public class TvBanner extends TbBanner {
    private List<TbBanner> pics;
    private String createName;
}