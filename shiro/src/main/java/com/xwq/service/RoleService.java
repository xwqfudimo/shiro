package com.xwq.service;

import java.util.List;

import com.xwq.model.Role;
import com.xwq.model.UserRole;

public interface RoleService {
	public List<Role> list();
	public Role get(int id);
	public void add(Role role);
	public void update(Role role);
	public void delete(int id);
	public void addUserRole(UserRole ur);
	public int getIdByName(String name);
	public List<Role> getByUserId(int userId);
	public void deleteUserRoleByUserId(int userId);
	public void deleteUserRoleByRoleId(int roleId);
}
