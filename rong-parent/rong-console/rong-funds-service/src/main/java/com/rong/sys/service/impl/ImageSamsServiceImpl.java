package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbImageSams;
import com.rong.sys.module.request.TqImageSams;
import com.rong.sys.module.view.TvImageSams;
import com.rong.sys.mapper.ImageSamsMapper;
import com.rong.sys.service.ImageSamsService;
import org.springframework.stereotype.Component;

@Component
public class ImageSamsServiceImpl extends BasicServiceImpl<TbImageSams, TqImageSams, TvImageSams, ImageSamsMapper> implements ImageSamsService {
}