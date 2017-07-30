package com.baizhi.dao;

import com.baizhi.entity.User;

public interface UserDao {
	   //注册新用户
	   public void addUser(User user);
	   //查一个
	   public User selectOne(String name);
}
