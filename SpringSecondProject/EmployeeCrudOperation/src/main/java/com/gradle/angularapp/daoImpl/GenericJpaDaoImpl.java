package com.gradle.angularapp.daoImpl;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gradle.angularapp.dao.IGenericDao;


@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )

public class GenericJpaDaoImpl< T extends Serializable > extends AbstractJpaDao< T > implements IGenericDao< T >{


	

	
}
