package com.barrage.web.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barrage.web.entity.UserAccount;
import com.barrage.web.mapper.LoginRegisterMapper;
import com.barrage.web.service.LoginRegisterService;


@Service("LoginRegisterService")
public class LoginRegisterServiceImpl implements LoginRegisterService{
	@Autowired
	private LoginRegisterMapper lrm;

	@Override
	public Integer insertUserAccount(UserAccount userAccount) throws Exception {
		Integer flag=0;
		flag= lrm.insertUserAccount(userAccount);
		return userAccount.getId();
		
	}
}
