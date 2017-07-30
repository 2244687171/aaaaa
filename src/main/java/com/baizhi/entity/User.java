package com.baizhi.entity;
/**
 * 登录用户实体类
 * 
 *
 */
public class User {
  private String id;
  private String username;
  private String password;
  private String realname;
  private String sex;
  private String salt;
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(String id, String username, String password, String realname,
		String sex, String salt) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.realname = realname;
	this.sex = sex;
	this.salt = salt;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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
public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getSalt() {
	return salt;
}
public void setSalt(String salt) {
	this.salt = salt;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password="
			+ password + ", realname=" + realname + ", sex=" + sex + ", salt="
			+ salt + "]";
}

}
