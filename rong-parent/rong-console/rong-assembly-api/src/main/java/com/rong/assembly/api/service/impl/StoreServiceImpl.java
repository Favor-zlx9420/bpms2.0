package com.rong.assembly.api.service.impl;

import com.rong.assembly.api.module.request.buz.TqCustomerUser;
import com.rong.assembly.api.module.response.TrCustomerServer;
import com.rong.assembly.api.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Override
    public List<TrCustomerServer> getCustomerServices(TqCustomerUser req) {
        return null;
    }
}
