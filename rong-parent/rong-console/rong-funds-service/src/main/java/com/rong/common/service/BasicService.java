package com.rong.common.service;

import com.rong.common.module.BaseEntity;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.TvPageList;
import com.vitily.mybatis.core.wrapper.PageList;
import com.vitily.mybatis.core.wrapper.delete.DeleteWrapper;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;

import java.util.List;

@SuppressWarnings("unused")
public interface BasicService<T extends BaseEntity<T>,Q,V> {
	int insert(Q req);
	int insertSelective(T entity);
	int deleteByPrimaryKey(IdWrapper wrapper);
	int delete(DeleteWrapper wrapper);
	T selectItemByPrimaryKey(IdWrapper wrapper);
	T selectOne(QueryWrapper wrapper);
	List<T> selectList(QueryWrapper wrapper);
	int selectCount(QueryWrapper wrapper);
	int updateByPrimary(Q req);
	int updateSelectiveByPrimaryKey(T entity);
	int updateSelectItem(UpdateWrapper wrapper);

	V selectViewByPrimaryKey(MultiTableIdWrapper wrapper);
	V selectOneView(MultiTableQueryWrapper wrapper);
	List<V> selectViewList(MultiTableQueryWrapper wrapper);
	int selectMultiTableCount(MultiTableQueryWrapper wrapper);

	TvPageList<V> selectPageList(MultiTableQueryWrapper bean);
	PageList<V> selectPageListV(MultiTableQueryWrapper req);

	MultiTableQueryWrapper getMultiCommonWrapper();
	MultiTableQueryWrapper getMultiCommonWrapper(Class<?> tbClass);
	MultiTableIdWrapper getMultiCommonIdWrapper(Object id);

	//from xml
	List<V> getListByBean(QueryInfo<T> query);
	int selectCount(QueryInfo<T> query);
	TvPageList<V> selectPageList(QueryInfo<T> query);

}
