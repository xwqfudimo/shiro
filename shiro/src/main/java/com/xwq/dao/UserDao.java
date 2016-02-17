package com.xwq.dao;

import com.xwq.model.User;

public interface UserDao extends BaseDao<User> {
	public User getByUsername(String username);
	public int getUserIdByUsername(String username);
}
