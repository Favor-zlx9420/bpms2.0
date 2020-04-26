package com.rong.assembly.api.service;

import com.rong.assembly.api.module.request.TqOrgInfo;
import com.rong.assembly.api.module.request.TqPeopleInfo;
import com.rong.assembly.api.module.response.org.TrRespOrg;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.common.consts.CommonEnumContainer;

import java.util.List;

public interface OrgService {
    TrRespOrg selectPriOrgInfo(TqOrgInfo req);
    TrRespOrg selectRaisedOrgInfo(TqOrgInfo req);
    List<TrManager> selectManagerOfPriOrg(TqOrgInfo req);
    List<TrManager> selectManagerOfRaisedOrg(TqOrgInfo req);
    List<TrRespOrg> getServiceOrgOfManager(TqPeopleInfo peopleInfo);
    CommonEnumContainer.OrgType getOrgType(Long partyId);
}
