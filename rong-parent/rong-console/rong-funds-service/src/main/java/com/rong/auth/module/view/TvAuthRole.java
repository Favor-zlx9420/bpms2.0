package com.rong.auth.module.view;

import com.rong.auth.module.entity.TbAuthRole;
import lombok.Data;

import java.util.List;

@Data
public class TvAuthRole extends TbAuthRole {
    private Integer resourceType;
    private List<String> resources;
}