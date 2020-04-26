package com.rong.console.center.util;
public class ViewModel {
	/**
	 * 类型根路径
	 */
	private final String basePath;
	
	/**
	 * 列表页面uri
	 */
	private String listAction;
	private String dataGridAction;
	/**
	 * 新增页面 和 提交
	 */
	private String addAction;
	/**
	 * 编辑页面 和 提交
	 */
	private String editAction;
	/**
	 * 查看详情页面uri
	 */
	private String viewAction;

	/**
	 * 删除或者回复url
	 */
	private String delOrRecAction;
	/**
	 * 新增或者修改提交的action
	 */
	private String alterActionUrl;

	private final String title;

	private Object extraData;

	public String getBasePath() {
		return basePath;
	}

	public String getListAction() {
		return listAction;
	}

	public void setListAction(String listAction) {
		this.listAction = listAction;
	}

	public String getDataGridAction() {
		return dataGridAction;
	}

	public void setDataGridAction(String dataGridAction) {
		this.dataGridAction = dataGridAction;
	}

	public String getAddAction() {
		return addAction;
	}

	public void setAddAction(String addAction) {
		this.addAction = addAction;
	}

	public String getEditAction() {
		return editAction;
	}

	public void setEditAction(String editAction) {
		this.editAction = editAction;
	}

	public String getViewAction() {
		return viewAction;
	}

	public void setViewAction(String viewAction) {
		this.viewAction = viewAction;
	}

	public String getDelOrRecAction() {
		return delOrRecAction;
	}

	public void setDelOrRecAction(String delOrRecAction) {
		this.delOrRecAction = delOrRecAction;
	}

	public String getAlterActionUrl() {
		return alterActionUrl;
	}

	public void setAlterActionUrl(String alterActionUrl) {
		this.alterActionUrl = alterActionUrl;
	}

	public String getTitle() {
		return title;
	}

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}
	private ViewModel(Builder builder){
		this.basePath = builder.basePath;
		this.listAction = builder.listAction;
		this.dataGridAction = builder.dataGridAction;
		this.addAction = builder.addAction;
		this.editAction = builder.editAction;
		this.viewAction = builder.viewAction;
		this.delOrRecAction = builder.delOrRecAction;
		this.alterActionUrl = builder.alterActionUrl;
		this.title = builder.title;
		this.extraData = builder.extraData;
	}
	public static class Builder{
		/**
		 * 类型根路径
		 */
		private String basePath;

		/**
		 * 列表页面uri
		 */
		private String listAction;
		private String dataGridAction;
		/**
		 * 新增页面 和 提交
		 */
		private String addAction;
		/**
		 * 编辑页面 和 提交
		 */
		private String editAction;
		/**
		 * 查看详情页面uri
		 */
		private String viewAction;

		/**
		 * 删除或者回复url
		 */
		private String delOrRecAction;
		/**
		 * 新增或者修改提交的action
		 */
		private String alterActionUrl;

		private String title;

		private Object extraData;
		public Builder listAction(String listAction){
			this.listAction = listAction;
			return this;
		}
		public Builder dataGridAction(String dataGridAction){
			this.dataGridAction = dataGridAction;
			return this;
		}
		public Builder addAction(String addAction){
			this.addAction = addAction;
			return this;
		}
		public Builder editAction(String editAction){
			this.editAction = editAction;
			return this;
		}
		public Builder viewAction(String viewAction){
			this.viewAction = viewAction;
			return this;
		}
		public Builder delOrRecAction(String delOrRecAction){
			this.delOrRecAction = delOrRecAction;
			return this;
		}
		public Builder alterActionUrl(String alterActionUrl){
			this.alterActionUrl = alterActionUrl;
			return this;
		}
		public Builder extraData(Object extraData){
			this.extraData = extraData;
			return this;
		}


		public Builder basePath(String basePath){
			this.basePath = basePath;
			this.listAction = basePath+"list";
			this.dataGridAction = basePath + "dataGrid";
			this.addAction = basePath+"add";
			this.editAction = basePath+"edit";
			this.viewAction = basePath+"view";
			this.delOrRecAction = basePath+"del-or-rec";
			return this;
		}
		public Builder title(String title){
			this.title = title;
			return this;
		}

		public ViewModel build(){
			return new ViewModel(this);
		}

	}
}
