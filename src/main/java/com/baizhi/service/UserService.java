package com.baizhi.service;

import com.baizhi.entity.User;

public interface UserService {
	    //添加用户
		public void insertUser(User user);
		//验证登录
		public User showOne(String name);

}
