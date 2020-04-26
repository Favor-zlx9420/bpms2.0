package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardAlbum;
import lombok.Data;

@Data
public class TsUserCardAlbum extends QueryInfo<TbUserCardAlbum> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        picUrl,
        picName,
        remark;
    }
}