package com.baizhi.dao;

import java.util.List;

import com.baizhi.entity.Emp;

public interface EmpDao {
        //添加
	 void add(Emp emp);
	//删除
	public void delete(String id);
	//修改
	public void change(Emp emp);
	//查询
	public List<Emp> select();
	//查一个
	public Emp selectOne(String id);
}
