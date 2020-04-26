package com.rong.sys.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.sys.module.entity.TbBanner;
import lombok.Data;

import java.util.List;

@Data
public class TqBanner extends BaseRequest<TbBanner, TqBanner> {
    private List<TbBanner> pics;
    private List<Long> delPicIds;
}