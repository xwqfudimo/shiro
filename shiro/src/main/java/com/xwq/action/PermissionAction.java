package com.xwq.action;

import java.util.List;

import com.xwq.model.Permission;
import com.xwq.model.Result;
import com.xwq.util.TextUtil;

public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private String result;
	private List<Permission> permissionList;
	private int id;
	private String name;
	private String url;
	private String group_name;
	private String search_name;

	public String getResult() {
		return result;
	}
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getSearch_name() {
		return search_name;
	}
	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}
	
	
	/**
	 * 权限列表
	 */
	@Override
	public String execute() throws Exception {
		permissionList = this.permissionService.list(search_name);
		
		return SUCCESS;
	}
	
	/**
	 * 权限新增
	 * @return
	 */
	public String save() throws Exception {
		if(TextUtil.isEmpty(name) || TextUtil.isEmpty(url)) {
			result = Result.failure("权限名称或url不能为空!");
		}
		else {
			Permission perm = new Permission();
			perm.setName(name);
			perm.setUrl(url);
			perm.setGroup_name(group_name);
			
			this.permissionService.add(perm);
			result = Result.success();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 权限编辑
	 * @return
	 */
	public String edit() {
		Permission perm = this.permissionService.get(id);
		getRequest().setAttribute("perm", perm);
		
		return SUCCESS;
	}
	
	
	/**
	 * 权限更新
	 * @return
	 */
	public String update() throws Exception {
		if(TextUtil.isEmpty(name) || TextUtil.isEmpty(url)) {
			result = Result.failure("权限名称或url不能为空！");
		}
		else {
			Permission perm = this.permissionService.get(id);
			
			if(perm == null) {
				result = Result.failure("权限不存在！");
			}
			else {
				perm.setName(name);
				perm.setUrl(url);
				perm.setGroup_name(group_name);
				
				this.permissionService.update(perm);
				result = Result.success();
			}
		}
		
		return SUCCESS;
	}	
	
	
	/**
	 * 权限删除
	 */
	public String del() throws Exception {
		this.permissionService.delete(id);
		result = Result.success();
		
		//删除角色-权限关联
		this.permissionService.deleteRolePermissionByPermId(id);
		
		return SUCCESS;
	}
}
