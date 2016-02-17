package com.xwq.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.xwq.model.Result;
import com.xwq.util.TextUtil;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String result;
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}
	
	/**
	 * 用户登录
	 */
	@Override
	public String execute() throws Exception {
		if(TextUtil.isEmpty(username) || TextUtil.isEmpty(password)) {
			result = Result.failure("用户名或密码不能为空!");
		}
		else {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			
			try {
				currentUser.login(token);
				
				getSession().setAttribute("loginUser", username);
				getSession().setAttribute("uid", this.userService.getUserIdByUsername(username));
				result = Result.success();
			} catch (AuthenticationException e) {
				e.printStackTrace();
				result = Result.failure("用户名或密码错误!");
			}
		}
		
		return SUCCESS;
	}

	/**
	 * 退出登录
	 */
	public String logout() {
		getSession().removeAttribute("loginUser");
		getSession().invalidate();
		return SUCCESS;
	}
}
