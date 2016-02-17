package com.xwq.action;

public class DefaultAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		getApplication().setAttribute("ctxPath", getRequest().getContextPath());
		
		return super.execute();
	}
}
