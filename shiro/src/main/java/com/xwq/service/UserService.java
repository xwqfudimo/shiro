package com.xwq.service;

import java.util.List;

import com.xwq.model.User;

public interface UserService {
	public User getByUsername(String username);
	public List<User> list();
	public User get(int id);
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	public int getUserIdByUsername(String username);
}
