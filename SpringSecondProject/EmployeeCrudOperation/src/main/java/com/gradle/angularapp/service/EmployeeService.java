package com.gradle.angularapp.service;

import java.util.List;

import com.gradle.angularapp.entity.Employee;



public interface EmployeeService {
	
	public Employee get(Class<Employee> clazz,long id) throws Exception;
	
	public List<Employee> getAllEmployees() throws Exception;
	
	public void save(Employee employee) throws Exception;
	
	public void update(Employee employee) throws Exception;
	
	//public void deleteAll(Employee employee) throws Exception;
	
    public void remove(long id) throws Exception;
	
	

}
