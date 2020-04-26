package com.rong.common.service.impl;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.common.module.BaseRequest;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.TvPageList;
import com.rong.common.service.FundsBasicService;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import com.vitily.mybatis.core.wrapper.Page;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.delete.DeleteWrapper;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class FundsBasicServiceImpl<T,Q extends BaseRequest<T,Q>, V extends T,
		M extends MultiTableMapper<T,V> & CommonBasicMapper<T,V>>
		implements FundsBasicService<T,Q,V> {
	@Autowired(required = false)
	protected M mapper;

	protected void beforeInsert(Q req){}
	protected void afterInsert(Q req){}
	@Override
	@Transactional
	public int insert(Q req){
		beforeInsert(req);
		int res = insertSelective(req.getEntity());
		if(res > 0) {
			afterInsert(req);
		}
		return res;
	}
	@Override
	@Transactional
	public int insertSelective(T entity){
		return mapper.insertSelective(entity);
	}
	@Override
	@Transactional
	public int deleteByPrimaryKey(IdWrapper wrapper){
		return mapper.deleteByPrimaryKey(wrapper);
	}
	@Override
	@Transactional
	public int delete(DeleteWrapper wrapper){
		return mapper.delete(wrapper);
	}
	@Override
	public T selectItemByPrimaryKey(IdWrapper wrapper){
		return mapper.selectItemByPrimaryKey(wrapper);
	}
	@Override
	public T selectOne(QueryWrapper wrapper){
		return mapper.selectOne(wrapper);
	}
	@Override
	public List<T> selectList(QueryWrapper wrapper){
		return mapper.selectList(wrapper);
	}
	@Override
	public int selectCount(QueryWrapper wrapper){
		return mapper.selectCount(wrapper);
	}
	protected void beforeUpdate(Q req){}
	protected void afterUpdate(Q req){}
	@Override
	@Transactional
	public int updateByPrimary(Q req){
		beforeUpdate(req);
		int res = updateSelectiveByPrimaryKey(req.getEntity());
		if(res > 0) {
			afterUpdate(req);
		}
		return res;
	}
	@Override
	@Transactional
	public int updateSelectiveByPrimaryKey(T entity){
		return mapper.updateSelectiveByPrimaryKey(entity);
	}
	@Override
	@Transactional
	public int updateSelectItem(UpdateWrapper wrapper){
		return mapper.updateSelectItem(wrapper);
	}

	@Override
	public V selectViewByPrimaryKey(MultiTableIdWrapper wrapper){
		return mapper.selectViewByPrimaryKey(wrapper);
	}
	@Override
	public V selectOneView(MultiTableQueryWrapper wrapper){
		return mapper.selectOneView(wrapper);
	}
	@Override
	public List<V> selectViewList(MultiTableQueryWrapper wrapper){
		return mapper.selectViewList(wrapper);
	}
	@Override
	public int selectMultiTableCount(MultiTableQueryWrapper wrapper){
		return mapper.selectMultiTableCount(wrapper);
	}

	@Override
	public TvPageList<V> selectPageList(MultiTableQueryWrapper wrapper){
		if(wrapper.getPage() == null){
			throw new RuntimeException("page entity can not be null !");
		}
		TvPageList<V> pageList = new TvPageList<>();
		Page page = wrapper.getPage();
		PageInfo pageInfo = PageInfo.valueOf(page.getPageIndex(),page.getPageSize());
		pageInfo.setRecordCount(mapper.selectMultiTableCount(wrapper));
		pageList.setPageInfo(pageInfo);
		pageList.setList(mapper.selectViewList(wrapper));
		return pageList;
	}

	@Override
	public MultiTableQueryWrapper getMultiCommonWrapper(){
		return new MultiTableQueryWrapper();
	}

	@Override
	public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
		return MultiTableIdWrapper.valueOf(id);
	}

	@Override
	public List<V> getListByBean(QueryInfo<T> query) {
		return mapper.getListByBean(query);
	}

	@Override
	public int selectCount(QueryInfo<T> query) {
		return mapper.getCountPaging(query);
	}

	@Override
	public TvPageList<V> selectPageList(QueryInfo<T> query) {
		if(query.getPageInfo() == null){
			throw new RuntimeException("page entity can not be null !");
		}
		TvPageList<V> pageList = new TvPageList<>();
		Page page = query.getPageInfo();
		PageInfo pageInfo = PageInfo.valueOf(page.getPageIndex(),page.getPageSize());
		pageInfo.setRecordCount(mapper.getCountPaging(query));
		pageList.setPageInfo(pageInfo);
		pageList.setList(mapper.getListByBean(query));
		return pageList;
	}
}
