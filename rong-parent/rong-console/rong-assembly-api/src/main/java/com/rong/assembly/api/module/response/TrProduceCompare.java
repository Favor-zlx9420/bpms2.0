package com.rong.assembly.api.module.response;

import com.rong.assembly.api.module.response.org.TrRespOrg;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.tong.pfunds.module.view.TvDateBoundary;
import com.rong.tong.pfunds.module.view.TvHisNav;
import com.rong.tong.pfunds.module.view.TvIntervalReturn;
import com.rong.tong.pfunds.module.view.TvProduceInformation;
import com.rong.tong.pfunds.module.view.TvProduceSummary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-16 15:28
 **/
@Data
public class TrProduceCompare {
    @ApiModelProperty(value = "证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "产品摘要")
    private TvProduceSummary produceSummary;

    @ApiModelProperty(value = "产品资料")
    private TvProduceInformation produceInformation;

    @ApiModelProperty(value = "基金经理")
    private TrManager manager;

    @ApiModelProperty(value = "基金公司")
    private TrRespOrg org;

    @ApiModelProperty(value = "收益走势图")
    private List<TvHisNav> hisNavs;

    @ApiModelProperty(value = "净值最大最小日期")
    private TvDateBoundary dateBoundary;

    @ApiModelProperty(value = "区间收益")
    private TvIntervalReturn intervalReturn;
}
