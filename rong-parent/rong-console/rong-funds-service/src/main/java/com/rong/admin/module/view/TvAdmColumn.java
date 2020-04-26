package com.rong.admin.module.view;

import com.rong.admin.module.entity.TbAdmColumn;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TvAdmColumn extends TbAdmColumn {
    public TvAdmColumn(){}

    /**
     * 只复制一些基本信息，
     * @param entity
     */
    public TvAdmColumn(TbAdmColumn entity){
        setId(entity.getId());
        //setState(entity.getState());
        //setDeltag(entity.getDeltag());
        //setCreateDate(entity.getCreateDate());
        //setUpdateDate(entity.getUpdateDate());
        setParentId(entity.getParentId());
        setSort(entity.getSort());
        setName(entity.getName());
        setUrlLink(entity.getUrlLink());
        setIcon(entity.getIcon());
        setVisible(entity.getVisible());
        setIsBtn(entity.getIsBtn());
    }
    /**
     * 直属子节点
     */
    private List<TvAdmColumn> children;
}