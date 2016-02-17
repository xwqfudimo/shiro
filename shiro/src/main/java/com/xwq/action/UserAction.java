package com.xwq.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.xwq.model.Result;
import com.xwq.model.Role;
import com.xwq.model.User;
import com.xwq.model.UserRole;
import com.xwq.util.TextUtil;

public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private List<User> userList;
	private int id;
	private String username;
	private String password;
	private String roleIds;
	private String result;

	public List<User> getUserList() {
		return userList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getResult() {
		return result;
	}		

	/**
	 * 用户列表
	 */
	@Override
	public String execute() throws Exception {
		userList = this.userService.list();
		
		return SUCCESS;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	public String add() {
		//所有角色列表
		List<Role> roleList = this.roleService.list();
		getRequest().setAttribute("roleList", roleList);
		
		return SUCCESS;
	}
	
	/**
	 * 用户新增提交
	 * @return
	 */
	public String save() throws Exception {
		if(TextUtil.isEmpty(username) || TextUtil.isEmpty(password)) {
			result = Result.failure("用户名或密码不能为空！");
		}
		else {
			password = new Md5Hash(password, username).toHex();
			User user = new User(username, password);
			this.userService.add(user);
			
			int uid = this.userService.getUserIdByUsername(username);
			
			//为用户关联角色
			String[] ids = roleIds.split(",");
			for(String idStr : ids) {
				UserRole ur = new UserRole();
				ur.setUser_id(uid);
				ur.setRole_id(Integer.parseInt(idStr));
				
				this.roleService.addUserRole(ur);
			}
			
			result = Result.success();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 用户编辑
	 * @return
	 */
	public String edit() {
		HttpServletRequest request = getRequest();
		
		User user = this.userService.get(id);
		request.setAttribute("user", user);
		
		//所有角色列表
		List<Role> roleList = this.roleService.list();
		request.setAttribute("roleList", roleList);
		
		//用户拥有的所有角色
		List<Role> myRoles = this.roleService.getByUserId(id);
		request.setAttribute("myRoles", myRoles);
		
		return SUCCESS;
	}
	
	
	/**
	 * 用户更新
	 * @return
	 */
	public String update() throws Exception {
		if(TextUtil.isEmpty(username) || TextUtil.isEmpty(password)) {
			result = Result.failure("用户名或密码不能为空！");
		}
		else {
			User user = this.userService.get(id);
			if(user != null) {
				user.setUsername(username);
				password = new Md5Hash(password, username).toHex();
				user.setPassword(password);
				this.userService.update(user);
				
				//删除旧的用户-角色关联，添加新的用户-角色关联
				this.roleService.deleteUserRoleByUserId(id);
				
				String[] ids = roleIds.split(",");
				for(String idStr : ids) {
					UserRole ur = new UserRole();
					ur.setUser_id(id);
					ur.setRole_id(Integer.parseInt(idStr));
					
					this.roleService.addUserRole(ur);
				}
				
				result = Result.success();
			}
			else {
				result = Result.failure("用户不存在！");
			}
		}
		
		return SUCCESS;
	}	
	
	
	/**
	 * 用户删除
	 */
	public String del() throws Exception {
		this.userService.delete(id);
		
		//删除用户-角色管理
		this.roleService.deleteUserRoleByUserId(id);
		
		result = Result.success();
		
		return SUCCESS;
	}
}
