package com.xwq.action;

import java.util.List;
import java.util.Map;

import com.xwq.model.Permission;
import com.xwq.model.Result;
import com.xwq.model.Role;
import com.xwq.model.RolePermission;
import com.xwq.util.TextUtil;

public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String result;
	private List<Role> roleList;
	private int id;
	private String name;
	private String description;
	private String permIds;
	
	public String getResult() {
		return result;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPermIds() {
		return permIds;
	}
	public void setPermIds(String permIds) {
		this.permIds = permIds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * 角色列表
	 */
	@Override
	public String execute() throws Exception {
		roleList = this.roleService.list();
		
		return SUCCESS;
	}
	
	/**
	 * 查看角色
	 */
	public String view() {
		//角色拥有的权限map
		Map<String, List<Permission>> permMap = this.permissionService.getPermMapByRoleId(id);
		getRequest().setAttribute("permMap", permMap);
		
		Role role = this.roleService.get(id);
		getRequest().setAttribute("role", role);
		
		return SUCCESS;
	}
	
	/**
	 * 新增角色
	 */
	public String add() {
		//所有权限分组map
		Map<String, List<Permission>> allPermissionMap = this.permissionService.getAllByGroup();
		getRequest().setAttribute("allPermissionMap", allPermissionMap);
		
		return SUCCESS;
	}
	
	/**
	 * 新增角色提交
	 * @return
	 */
	public String save() throws Exception {
		if(TextUtil.isEmpty(name)) {
			result = Result.failure("角色标识名不能为空！");
		}
		else {
			Role role = new Role();
			role.setName(name);
			role.setDescription(description);
			this.roleService.add(role);
			
			int rid = this.roleService.getIdByName(name);
			
			//为角色关联权限
			String[] ids = permIds.split(",");
			for(String id : ids) {
				RolePermission rp = new RolePermission();
				rp.setRole_id(rid);
				rp.setPermission_id(Integer.parseInt(id));
				
				this.permissionService.addRolePermission(rp);
			}
			
			result = Result.success();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 角色编辑
	 * @return
	 */
	public String edit() {
		//所有权限分组map
		Map<String, List<Permission>> allPermissionMap = this.permissionService.getAllByGroup();
		getRequest().setAttribute("allPermissionMap", allPermissionMap);
		
		//角色拥有的所有权限
		List<Permission> permList = this.permissionService.getByRoleId(id);
		getRequest().setAttribute("permList", permList);
		
		Role role = this.roleService.get(id);
		getRequest().setAttribute("role", role);
		
		return SUCCESS;
	}
	
	
	/**
	 * 角色更新
	 * @return
	 */
	public String update() throws Exception {
		if(TextUtil.isEmpty(name)) {
			result = Result.failure("角色标识名不能为空！");
		}
		else {
			Role role = this.roleService.get(id);
			
			if(role != null) {
				role.setName(name);
				role.setDescription(description);
				this.roleService.update(role);
				
				//删除旧的角色-权限关联，添加新的角色-权限关联
				this.permissionService.deleteRolePermissionByRoleId(role.getId());
				
				String[] ids = permIds.split(",");
				for(String idStr : ids) {
					RolePermission rp = new RolePermission();
					rp.setRole_id(id);
					rp.setPermission_id(Integer.parseInt(idStr));
					
					this.permissionService.addRolePermission(rp);
				}
				
				result = Result.success();
			}
			else {
				result = Result.failure("角色不存在！");
			}
		}
		
		return SUCCESS;
	}	
	
	
	/**
	 * 角色删除
	 */
	public String del() throws Exception {
		this.roleService.delete(id);
		
		//删除用户-角色管理
		this.roleService.deleteUserRoleByRoleId(id);
		//删除角色-权限关联
		this.permissionService.deleteRolePermissionByRoleId(id);
		
		result = Result.success();
		
		return SUCCESS;
	}
}
