package com.barrage.web.entity;

import java.io.Serializable;
import java.util.Date;

public class barrageComment implements Serializable{
	private static final long serserialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private String comment;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerserialversionuid() {
		return serserialVersionUID;
	}
	@Override
	public String toString() {
		return "barrageComment [id=" + id + ", userId=" + userId + ", comment=" + comment + ", createTime=" + createTime
				+ "]";
	}
	
}
