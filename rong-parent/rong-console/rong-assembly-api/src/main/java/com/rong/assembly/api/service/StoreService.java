package com.rong.assembly.api.service;

import com.rong.assembly.api.module.request.buz.TqCustomerUser;
import com.rong.assembly.api.module.response.TrCustomerServer;

import java.util.List;

public interface StoreService {
    List<TrCustomerServer> getCustomerServices(TqCustomerUser req);
}
