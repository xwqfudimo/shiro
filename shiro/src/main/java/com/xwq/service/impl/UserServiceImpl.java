package com.xwq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwq.dao.UserDao;
import com.xwq.model.User;
import com.xwq.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User getByUsername(String username) {
		return this.userDao.getByUsername(username);
	}

	@Override
	public List<User> list() {
		return this.userDao.list();
	}

	@Override
	public User get(int id) {
		return this.userDao.get(id);
	}

	@Override
	public void add(User user) {
		this.userDao.insert(user);
	}

	@Override
	public void update(User user) {
		this.userDao.update(user);
	}

	@Override
	public void delete(int id) {
		this.userDao.delete(id);
	}

	@Override
	public int getUserIdByUsername(String username) {
		return this.userDao.getUserIdByUsername(username);
	}
}
