package com.barrage.web.entity;

import java.io.Serializable;
import java.util.Date;


public class UserAccount  implements Serializable{
	private static final long serserialVersionUID = 1L;
	private Integer id;
	private String  nickName;
	private String phone;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
		return "UserAccount [id=" + id + ", nickName=" + nickName + ", phone=" + phone + ", createTime=" + createTime
				+ "]";
	}

	
}
