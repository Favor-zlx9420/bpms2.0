package com.rong.common.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.PrimaryKey;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity<T extends BaseEntity> implements Serializable {
	@PrimaryKey()
	@Column("`id`")
	@ApiModelProperty(value = "id，可排序，排序字段为'e.id'")
	private Long id;
	@Column("`create_date`")
	@ApiModelProperty(value = "创建时间，可排序，排序字段为'e.createDate'")
	private Date createDate;
	@Column("`update_date`")
	@ApiModelProperty(value = "更新时间，可排序，排序字段为'e.updateDate'")
	private Date updateDate;
	@JsonIgnore
	@Column("`deltag`")
	@ApiModelProperty(hidden = true)
	private Boolean deltag;
	public Long getId() {
		return id;
	}

	public T setId(Long id) {
		this.id = id;
		return (T)this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public T setCreateDate(Date createDate) {
		this.createDate = createDate;
		return (T)this;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public T setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return (T)this;
	}

	public Boolean getDeltag() {
		return deltag;
	}

	public T setDeltag(Boolean deltag) {
		this.deltag = deltag;
		return (T)this;
	}
}
