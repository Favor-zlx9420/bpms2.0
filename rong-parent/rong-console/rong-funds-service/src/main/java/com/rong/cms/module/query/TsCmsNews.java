package com.rong.cms.module.query;

import com.rong.cms.module.entity.TbCmsNews;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsCmsNews extends QueryInfo<TbCmsNews> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        cateId,
        typeId,
        title,
        subTitle,
        description,
        author,
        comeFrom,
        commentable,
        hits,
        top,
        pageTitle,
        keyword,
        pageDescription,
        published,
        labelIds,
        picList,
        fileList,
        relatedLink,
        displaydate,
        content,
        createBy;
    }
}