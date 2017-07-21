package com.barrage.web.service;

import com.barrage.web.entity.UserAccount;

public interface LoginRegisterService {
	/**
	 * 注册新用户,将信息插入user-account与user-info表
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	public Integer insertUserAccount(UserAccount userAccount) throws Exception;	
}
