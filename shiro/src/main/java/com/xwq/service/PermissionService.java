package com.xwq.service;

import java.util.List;
import java.util.Map;

import com.xwq.model.Permission;
import com.xwq.model.RolePermission;

public interface PermissionService {
	public List<Permission> list();
	public List<Permission> list(String search);
	public Permission get(int id);
	public void add(Permission permission);
	public void update(Permission permission);
	public void delete(int id);
	public Map<String, List<Permission>> getAllByGroup();
	public void addRolePermission(RolePermission rp);
	public List<Permission> getByRoleId(int roleId);
	public void deleteRolePermissionByRoleId(int roleId);
	public void deleteRolePermissionByPermId(int permId);
	public Map<String, List<Permission>> getPermMapByRoleId(int id);
	public List<Permission> getByUserId(int userId);
}
