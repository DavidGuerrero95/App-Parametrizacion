package com.app.parametrizacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.parametrizacion.models.ParametrosFlutter;


public interface ParametrosFlutterRepository extends MongoRepository<ParametrosFlutter, String>{

	@RestResource(path="buscar-parametros")
	public ParametrosFlutter findByUsername(@Param("username") String username);
	
}

