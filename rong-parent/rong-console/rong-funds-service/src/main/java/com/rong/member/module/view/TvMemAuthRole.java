package com.rong.member.module.view;

import com.rong.member.module.entity.TbMemAuthRole;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TvMemAuthRole extends TbMemAuthRole {
    private String roleName;
    private String roleSymbol;
}