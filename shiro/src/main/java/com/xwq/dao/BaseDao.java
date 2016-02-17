package com.xwq.dao;

import java.util.List;

public interface BaseDao<T> {
	public void insert(T t);
	public void delete(int id);
	public void update(T t);
	public T get(int id);
	public List<T> list();
	public List<T> listByPage(int offset, int pageSize);
}
