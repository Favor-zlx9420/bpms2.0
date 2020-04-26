package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNavGr;
import com.rong.fundmanage.module.view.TvRaisedFundCurrentNavGr;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RaisedFundCurrentNavGrMapper extends CommonBasicMapper<TbRaisedFundCurrentNavGr, TvRaisedFundCurrentNavGr>, MultiTableMapper<TbRaisedFundCurrentNavGr, TvRaisedFundCurrentNavGr> {
    void resetNavGrTemp();

    Integer resetNavGrTempCount();

    void resetNavGr();
}