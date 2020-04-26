package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.MdInstitutionMapper;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.request.TqMdInstitution;
import com.rong.tong.pfunds.module.view.TvMdInstitution;
import com.rong.tong.pfunds.service.MdInstitutionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MdInstitutionServiceImpl extends FundsBasicServiceImpl<TbMdInstitution, TqMdInstitution, TvMdInstitution, MdInstitutionMapper> implements MdInstitutionService {
    @Override
    public List<TvMdInstitution> getListByPersonId(Integer personId) {
        return mapper.getListByPersonId(personId);
    }
}