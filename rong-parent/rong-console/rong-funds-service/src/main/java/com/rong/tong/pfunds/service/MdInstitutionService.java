package com.rong.tong.pfunds.service;

import com.rong.common.service.FundsBasicService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.request.TqMdInstitution;
import com.rong.tong.pfunds.module.view.TvMdInstitution;

import java.util.List;

public interface MdInstitutionService extends FundsBasicService<TbMdInstitution, TqMdInstitution, TvMdInstitution> {

    List<TvMdInstitution> getListByPersonId(Integer personId);
}