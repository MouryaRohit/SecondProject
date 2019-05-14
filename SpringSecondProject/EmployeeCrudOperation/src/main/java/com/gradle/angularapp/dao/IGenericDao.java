package com.gradle.angularapp.dao;

import java.io.Serializable;
import java.util.List;


public interface IGenericDao<T extends Serializable> {
	
	public  T findOne(long id);
	 
	public   List<T> findAll();
	
	
	 
	public void create( T entity);
	 
	public void update( T entity);
	 
	public void delete( T entity);
	 
	public  void deleteById( long id);


	public void setClazz( Class< T > clazzToSet );
	

}
