package com.baizhi.entity;
/**
 * 部门员工实体类
 */
import java.util.Date;

public class Emp {

   private String id;
   private String name;
   private Integer age;
   private double salary;
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}

public Emp(String id, String name, Integer age, double salary) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	this.salary = salary;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}
@Override
public String toString() {
	return "Emp [id=" + id + ", name=" + name + ", salary=" + salary + ", age="
			+ age + "]";
}
  
   
}
