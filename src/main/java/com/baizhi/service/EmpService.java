package com.baizhi.service;

import java.util.List;

import com.baizhi.entity.Emp;

public interface EmpService {
	     //添加员工信息
		public void insertEmp(Emp emp);
		//删除员工信息
		public void dropEmp(String id);
		//修改员工信息
		public void UpdateEmp(Emp emp);
		//查询员工信息
		public List<Emp> selectEmp();
		
		public Emp showOne(String id);
		
}
