package com.xwq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwq.dao.PermissionDao;
import com.xwq.model.Permission;
import com.xwq.model.RolePermission;
import com.xwq.service.PermissionService;
import com.xwq.util.TextUtil;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;
	
	
	@Override
	public List<Permission> list() {
		return this.permissionDao.list();
	}

	@Override
	public List<Permission> list(String search) {
		if(TextUtil.isEmpty(search)) {
			return list();
		}
		return this.permissionDao.listBySearch(search);
	}

	@Override
	public Permission get(int id) {
		return this.permissionDao.get(id);
	}

	@Override
	public void add(Permission permission) {
		this.permissionDao.insert(permission);
	}

	@Override
	public void update(Permission permission) {
		this.permissionDao.update(permission);
	}

	@Override
	public void delete(int id) {
		this.permissionDao.delete(id);
	}

	@Override
	public Map<String, List<Permission>> getAllByGroup() {
		List<Permission> permList = list();
		
		return list2map(permList);
	}

	@Override
	public void addRolePermission(RolePermission rp) {
		this.permissionDao.addRolePermission(rp);
	}

	@Override
	public List<Permission> getByRoleId(int roleId) {
		return this.permissionDao.getByRoleId(roleId);
	}

	@Override
	public void deleteRolePermissionByRoleId(int roleId) {
		this.permissionDao.deleteRolePermissionByRoleId(roleId);
	}

	@Override
	public void deleteRolePermissionByPermId(int permId) {
		this.permissionDao.deleteRolePermissionByPermId(permId);
	}

	@Override
	public Map<String, List<Permission>> getPermMapByRoleId(int id) {
		List<Permission> permList = getByRoleId(id);
		return list2map(permList);
	}

	private Map<String, List<Permission>> list2map(List<Permission> permList) {
		Set<String> nameSet = new HashSet<String>();
		for(Permission p : permList) {
			nameSet.add(p.getGroup_name());
		}
		
		Map<String, List<Permission>> result = new HashMap<String, List<Permission>>();
		for(String name : nameSet) {
			List<Permission> valueList = new ArrayList<Permission>();
			for(Permission p : permList) {
				if(p.getGroup_name().equals(name)) {
					valueList.add(p);
				}
			}
			
			result.put(name, valueList);
		}
		
		return result;
	}

	@Override
	public List<Permission> getByUserId(int userId) {
		return this.permissionDao.listByUserId(userId);
	}
}
