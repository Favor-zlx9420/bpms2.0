package com.rong.assembly.api.module.response;

import com.rong.store.module.view.TvDirectStoreOrg;
import com.rong.store.module.view.TvDirectStoreUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TrStoreCenterIndex {
    @ApiModelProperty("机构信息")
    private TvDirectStoreOrg orgInfo;
    @ApiModelProperty("驻点客服列表")
    private List<TvDirectStoreUser> customerUsers;
}
