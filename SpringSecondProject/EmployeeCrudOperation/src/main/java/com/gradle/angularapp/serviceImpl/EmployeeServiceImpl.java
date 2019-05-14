package com.gradle.angularapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gradle.angularapp.dao.IGenericDao;
import com.gradle.angularapp.entity.Employee;
import com.gradle.angularapp.service.EmployeeService;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService{
//	@Autowired
	IGenericDao<Employee> iDao;
	
	@Autowired
	   public void setIdao( IGenericDao< Employee > daoToSet ){
		iDao = daoToSet;
		iDao.setClazz( Employee.class );
	   }
	
	@Override
	public Employee get(Class<Employee> clazz,long id) throws Exception {
		
		return iDao.findOne(id);
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		
		return iDao.findAll();
	}

	@Override
	@Transactional
	public void save(Employee employee) throws Exception {
		iDao.create(employee);
		
	}

	@Override
	@Transactional
	public void update(Employee employee) throws Exception {
		iDao.update(employee);
		
	}

	/*
	 * @Override
	 * 
	 * @Transactional public void deleteAll(Employee employee) throws Exception {
	 * iDao.delete(employee);
	 * 
	 * }
	 */

	@Override
	@Transactional
	public void remove(long id) throws Exception {
		iDao.deleteById(id);
		
	}

}
