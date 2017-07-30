package com.baizhi.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Util;
import com.baizhi.util.SaltUtil;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	//添加
	@Override
	public void insertUser(User user) {
		//设置主键
		user.setId(UUID.randomUUID().toString());
		//产生随机盐
		String salt = SaltUtil.getSalt();
		//对用户密码进行MD5加密
		String pwd = user.getPassword()+salt;
		String newpwd = MD5Util.getMD5(pwd);
		//保存新密码和随机盐
		user.setPassword(newpwd);
		user.setSalt(salt);
		//调用dao层方法
		userDao.addUser(user);
	}
  //查询
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public User showOne(String name) {
		User user = userDao.selectOne(name);
		return user;
	}

}
