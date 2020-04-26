package com.rong.store.module.view;

import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import lombok.Data;

@Data
public class TvDirectStoreOrgLabel extends TbDirectStoreOrgLabel {
    private String partyShortName;
    private String partyFullName;
}