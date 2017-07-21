package com.barrage.web.mapper;

import com.barrage.web.entity.UserAccount;

public interface  LoginRegisterMapper {
	/**
	 *
	 * 注册新用户，将信息插入tb_user_account表
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	public Integer insertUserAccount(UserAccount userAccount) throws Exception;
}
