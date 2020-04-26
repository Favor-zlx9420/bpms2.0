package com.rong.assembly.api.service;

import com.rong.assembly.api.module.request.buz.TqFundsOfManager;
import com.rong.assembly.api.module.request.buz.TqFundsOfOrg;
import com.rong.assembly.api.module.request.uc.fav.TqFavProList;
import com.rong.assembly.api.module.response.product.TrFavFund;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.common.module.TvPageList;

public interface ProductService {
    TvPageList<TrFunds> priFundsOfOrg(TqFundsOfOrg req);
    TvPageList<TrFunds> raisedFundsOfOrg(TqFundsOfOrg req);
    TvPageList<TrFunds> priFundsOfManager(TqFundsOfManager req);
    TvPageList<TrFunds> raisedFundsOfManager(TqFundsOfManager req);
    TvPageList<TrFavFund> selectFavFunds(TqFavProList req);
}
