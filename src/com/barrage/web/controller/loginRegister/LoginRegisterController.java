package com.barrage.web.controller.loginRegister;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barrage.web.entity.UserAccount;
import com.barrage.web.service.LoginRegisterService;
import com.barrage.web.utils.Constants;
import com.barrage.web.utils.webUtil;



@Controller
@RequestMapping("login")
public class LoginRegisterController {
	@Autowired
	private LoginRegisterService lrs;
	@RequestMapping(value = "registerAccount")
	@ResponseBody
	public String registerAccount(HttpServletResponse response, UserAccount userAccount) {
		response.setHeader("Access-Control-Allow-Origin", Constants.cross_domain_url);
		if(userAccount.getPhone()==null || userAccount.getNickName()==null ){	
			return webUtil.results(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_VALIDATEWRONG,"验证失败，空字符串", null);
		}	
		// 验证手机号和昵称
		userAccount.setCreateTime(new Date());
		
		try {
			Integer flag = 0;
			flag = lrs.insertUserAccount(userAccount);
			System.out.println(flag);
			if (flag != 0)
				return webUtil.results(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "成功", flag);
			else
				return webUtil.results(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_DATAWRONG, "查询失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return webUtil.results(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_DATAWRONG, "查询失败", null);
		}
	}
}
