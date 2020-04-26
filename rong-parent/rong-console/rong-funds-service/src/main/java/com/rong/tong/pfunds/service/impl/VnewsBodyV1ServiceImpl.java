package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.tong.pfunds.mapper.VnewsBodyV1Mapper;
import com.rong.tong.pfunds.module.entity.TbVnewsBodyV1;
import com.rong.tong.pfunds.module.request.TqVnewsBodyV1;
import com.rong.tong.pfunds.module.view.TvVnewsBodyV1;
import com.rong.tong.pfunds.service.VnewsBodyV1Service;
import org.springframework.stereotype.Service;

@Service
public class VnewsBodyV1ServiceImpl extends BasicServiceImpl<TbVnewsBodyV1, TqVnewsBodyV1, TvVnewsBodyV1, VnewsBodyV1Mapper> implements VnewsBodyV1Service {
}