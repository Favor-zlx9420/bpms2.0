package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundLeverageInfo;
import lombok.Data;

@Data
public class TsFundLeverageInfo extends QueryInfo<TbFundLeverageInfo> {

    public enum Fields {
        id,
        fundId,
        category,
        idxId,
        idxCn,
        securityM,
        securityA,
        securityB,
        aShare,
        bShare,
        isConvert,
        aRatioInTa,
        bRatioInTa,
        minMergeM,
        minMergeA,
        minMergeB,
        minSplitM,
        minSplitA,
        minSplitB,
        establishDate,
        endDate,
        regularSplitDate,
        upThreshold,
        downThreshold,
        splitNote,
        isSubM,
        isRedM,
        updateTime,
        tmstamp,
        fixedReturnA,
        maxApply,
        maxRedeem,
        applyPattern,
        redeemPattern,
        isFixed,
        initialLever;
    }
}