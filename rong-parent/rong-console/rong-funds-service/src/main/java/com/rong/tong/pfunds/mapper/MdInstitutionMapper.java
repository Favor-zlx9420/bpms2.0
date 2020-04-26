package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.view.TvMdInstitution;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MdInstitutionMapper extends CommonBasicMapper<TbMdInstitution, TvMdInstitution>, MultiTableMapper<TbMdInstitution, TvMdInstitution> {

    List<TvMdInstitution> getListByPersonId(@Param("personId")Integer personId);
}