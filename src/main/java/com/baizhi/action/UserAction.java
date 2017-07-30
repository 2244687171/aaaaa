package com.baizhi.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {
	@Resource
	private UserService userService;
     private String username;
     private String password;
     private User user;
     
     
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
     
	public String regist(){
		userService.insertUser(user);
		return SUCCESS;
	}
	
	public String login(){
		user = userService.showOne(username);
		String pwd = user.getPassword();
		String salt = password+user.getSalt();
		String newpwd = MD5Util.getMD5(salt);
		if(user.getUsername()!=null&&pwd.equals(newpwd)){
			return SUCCESS;
		}else{
			return ERROR;
		}
		
	}
}
