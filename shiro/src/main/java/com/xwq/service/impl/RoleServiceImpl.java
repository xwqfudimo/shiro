package com.xwq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwq.dao.RoleDao;
import com.xwq.model.Role;
import com.xwq.model.UserRole;
import com.xwq.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> list() {
		return this.roleDao.list();
	}

	@Override
	public Role get(int id) {
		return this.roleDao.get(id);
	}

	@Override
	public void add(Role role) {
		this.roleDao.insert(role);
	}

	@Override
	public void update(Role role) {
		this.roleDao.update(role);
	}

	@Override
	public void delete(int id) {
		this.roleDao.delete(id);
	}

	@Override
	public void addUserRole(UserRole ur) {
		this.roleDao.addUserRole(ur);
	}

	@Override
	public int getIdByName(String name) {
		return this.roleDao.getIdByName(name);
	}

	@Override
	public List<Role> getByUserId(int userId) {
		return this.roleDao.getByUserId(userId);
	}

	@Override
	public void deleteUserRoleByUserId(int userId) {
		this.roleDao.deleteUserRoleByUserId(userId);
	}

	@Override
	public void deleteUserRoleByRoleId(int roleId) {
		this.roleDao.deleteUserRoleByRoleId(roleId);
	}

}
