package com.gradle.angularapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.angularapp.entity.Employee;
import com.gradle.angularapp.service.EmployeeService;
import com.gradle.angularapp.util.CustomErrorType;


@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
public class EmployeeController{
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	 @Autowired
	 private EmployeeService employeeService;
	 
	 // -------------------Retrieve Single User------------------------------------------
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/employee/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
	    public ResponseEntity<Employee> findOne(@PathVariable("id") Long id, Class<Employee> employee) throws Exception{
		 logger.info("Fetching User with id {}", id);
		 Employee emp =employeeService.get( employee, id);
		 if (emp == null) {
	            logger.error("Employee with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("Employee with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
		
	         return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	    }
	 // -------------------Retrieve All Users---------------------------------------------
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET/* , consumes = MediaType.APPLICATION_JSON_VALUE */)
	 public  ResponseEntity<List<Employee>>  findAllEmployees(HttpServletRequest req,HttpServletResponse res) throws Exception {		 
	        List<Employee> employees = null;
				try {
					
//					res.addHeader("Access-Control-Allow-Origin", "*");
//					res.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST, DELETE");
					employees = employeeService.getAllEmployees();
					 if (employees.isEmpty()) {
				            return new ResponseEntity(HttpStatus.NO_CONTENT);				           
				        }
				} catch (Exception e) {
					e.printStackTrace();
				}		       
		        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
 	 }
	  // -------------------Create a User-------------------------------------------
	 @RequestMapping(value = "/employee" ,consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.POST)
	 public ResponseEntity<Employee>  create(@RequestBody Employee employee)	throws Exception	{
		 try {
			employeeService.save(employee);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		 return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		 
		 
	 }
	  // ------------------- Update a User ------------------------------------------------
	 @RequestMapping(value = "/employee/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.PUT)
	 public @ResponseBody ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee,Class<Employee> clazz) throws Exception{
		 
		 logger.info("Updating User with id {}", id);
		  Employee entity = employeeService.get( clazz, id);
		  if (entity == null) {   
			  logger.error("Unable to update. Employee with id {} not found.", id);
		   return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		  }
		  entity.setName(employee.getName());
		  entity.setRole(employee.getRole());
		  entity.setSalary(employee.getSalary());
		  
		  
		  employeeService.update(entity);
		 
		  return new ResponseEntity<Employee>(entity, HttpStatus.OK);
		 
		 
	 }
	 
	 
	// ------------------- Delete a User-----------------------------------------
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 @RequestMapping(value = "/employee/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.DELETE)
	 public @ResponseBody ResponseEntity remove(@PathVariable("id") long id,Class<Employee> clazz) throws Exception {
		 logger.info("Fetching & Deleting User with id {}", id);
		 
		 Employee entity = employeeService.get( clazz, id);
	        if (entity == null) {
	            logger.error("Unable to delete. Employee with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("Unable to delete. Employee with id " + id + " not found."),
	                    HttpStatus.NOT_FOUND);
	        }
	        employeeService.remove(id);
	        return new ResponseEntity<Employee>(entity,HttpStatus.NO_CONTENT);
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	 // -------------------Retrieve Single User------------------------------------------
/*	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	 public @ResponseBody ResponseEntity<Employee>  get(@PathVariable("id") long id,Class<Employee> clazz) throws Exception {	 
        //logger.info("Fetching User with id {}", id);
	        Employee employee= employeeService.get(clazz,id);
	        if (employee == null) {
	            logger.error("Employee with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("Employee with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	    } 
	 
	 // -------------------Retrieve All Users---------------------------------------------
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 @RequestMapping(value = "/employees",consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
	 public @ResponseBody ResponseEntity<List<Employee>>  getAllEmployees() throws Exception {
	        List<Employee> employees = null;
			try {
				employees = employeeService.getAllEmployees();
				 if (employees.isEmpty()) {
			            return new ResponseEntity(HttpStatus.NO_CONTENT);
			           
			        }
			} catch (Exception e) {
				e.printStackTrace();
			}
	       
	        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	    }

	 @RequestMapping(value = "/employee/",consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.POST)
	 public @ResponseBody ResponseEntity<String>  save(@RequestBody Employee employee) throws Exception {
    	employeeService.save(employee);
//		return null;		 
//		 employee.setId(employeeService.getAllEmployees().size() + 1);
//	        employees.getEmployees().add(employee);
	        return new ResponseEntity<String>(HttpStatus.CREATED);
		
	 }

	 @RequestMapping(value = "/employee/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.PUT)
	 public @ResponseBody ResponseEntity<Employee> update(@PathVariable("id") int id, @RequestBody Employee employee,Class<Employee> clazz) throws Exception{
//		employeeService.update(employee);
//		return null;
		 
		 Employee emp = employeeService.get(clazz,id);
	        if(emp != null){
	            emp.setName(employee.getName());
	            emp.setRole(employee.getRole());
	            emp.setSalary(employee.getSalary());
	            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	        }
	        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		
	 }

	 @RequestMapping(value = "/employee/",consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.DELETE)
	 public @ResponseBody ResponseEntity<Employee> deleteAll(@RequestBody Employee employee) throws Exception {
		 logger.info("Deleting All Users");
		 employeeService.deleteAll(employee);
		return  new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		
	 }

	 @RequestMapping(value = "/employee/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.DELETE)
	 public @ResponseBody String remove(@PathVariable("id") long id,Class<Employee> clazz) throws Exception {
		Employee employee= employeeService.get(clazz,id);
		if(employee == null) {
			
			return "Employee Not Found";
			
		}
		employeeService.remove(id);
		return "Employee Deleted Successfully";
		
	 }
	
	*/
	 
	 
}
