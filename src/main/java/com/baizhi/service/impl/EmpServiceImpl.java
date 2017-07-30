package com.baizhi.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.EmpDao;
import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
@Service("empService")
@Transactional
public class EmpServiceImpl implements EmpService{
	@Resource
	private EmpDao empDao;
	//添加
	@Override
	public void insertEmp(Emp emp) {
		emp.setId(UUID.randomUUID().toString());
         empDao.add(emp);
	}
   //删除
	@Override
	public void dropEmp(String id) {
		empDao.delete(id);
	}
   //修改
	@Override
	public void UpdateEmp(Emp emp) {
		empDao.change(emp);
	}
   //查询
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Emp> selectEmp() {
		List<Emp> list = empDao.select();
		return list;
	}
    //查一个
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Emp showOne(String id) {
		Emp emp = empDao.selectOne(id);
		return emp;
	}

}
