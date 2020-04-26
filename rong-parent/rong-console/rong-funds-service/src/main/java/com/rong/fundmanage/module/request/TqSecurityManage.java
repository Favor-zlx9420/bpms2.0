package com.rong.fundmanage.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import lombok.Data;

import java.util.List;

@Data
public class TqSecurityManage extends BaseRequest<TbSecurityManage, TqSecurityManage> {
    private List<Long> labelIds;
    /**
     * 推荐理由
     */
    private String labelReason;
    /**
     * 自定义标签0
     */
    private String labelVar0;
    /**
     * 自定义标签1
     */
    private String labelVar1;
}