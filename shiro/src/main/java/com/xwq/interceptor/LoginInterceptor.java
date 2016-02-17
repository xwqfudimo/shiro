package com.xwq.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Object loginUser = ctx.getSession().get("loginUser");
		
		if(loginUser == null || "".equals(loginUser.toString().trim())) {
			return "login";
		}
		
		return invocation.invoke();
	}
}
