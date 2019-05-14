package com.gradle.angularapp.daoImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractJpaDao< T extends Serializable > {
	 
	   @PersistenceContext
	   EntityManager entityManager;
	 
	   private Class< T > clazz;
	 
	   public void setClazz( Class< T > clazzToSet ) {
	      this.clazz = clazzToSet;
	   }
	 
	   public T findOne(long id) {
	      return entityManager.find( clazz, id );
	   }
	   
	   
	   @SuppressWarnings("unchecked")
	   public List< T > findAll(){
	      return entityManager.createQuery( "from " + clazz.getName() )
	       .getResultList();
	   }
	 
	   public void create( T entity ){
	      entityManager.persist( entity );
	   }
	 
	   public void update( T entity ){
	      entityManager.merge( entity );
	   }
	 
	   public void delete( T entity ){
	      entityManager.remove( entity );
	   }
	   public void deleteById(long id) {
	      T entity = entityManager.find( clazz, id );
	      delete( entity );
	   }
}



