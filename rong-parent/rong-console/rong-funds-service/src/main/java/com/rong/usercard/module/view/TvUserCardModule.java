package com.rong.usercard.module.view;

import com.rong.usercard.module.entity.TbUserCardModule;
import lombok.Data;

import java.util.List;

@Data
public class TvUserCardModule extends TbUserCardModule {
    private List<?> details;
}