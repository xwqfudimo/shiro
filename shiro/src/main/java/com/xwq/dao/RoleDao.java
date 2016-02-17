package com.xwq.dao;

import java.util.List;

import com.xwq.model.Role;
import com.xwq.model.UserRole;

public interface RoleDao extends BaseDao<Role> {
	public void addUserRole(UserRole ur);

	public int getIdByName(String name);

	public List<Role> getByUserId(int userId);

	public void deleteUserRoleByUserId(int userId);

	public void deleteUserRoleByRoleId(int roleId);
}
