package com.xwq.dao;

import java.util.List;

import com.xwq.model.Permission;
import com.xwq.model.RolePermission;

public interface PermissionDao extends BaseDao<Permission> {
	public List<Permission> listBySearch(String search);
	public void addRolePermission(RolePermission rp);
	public List<Permission> getByRoleId(int roleId);
	public void deleteRolePermissionByRoleId(int roleId);
	public void deleteRolePermissionByPermId(int permId);
	public List<Permission> listByUserId(int userId);
}
