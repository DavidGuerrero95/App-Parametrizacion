package com.app.parametrizacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.parametrizacion.models.ServiciosParametros;

public interface ServiciosParametrosRepository extends MongoRepository<ServiciosParametros, String>{

	@RestResource(path = "buscar-name")
	public ServiciosParametros findByNombre(@Param("nombre") String nombre);
	
	public ServiciosParametros findImageById(String id, Class<ServiciosParametros> class1);
	
	@RestResource(path = "existNombre")
	public Boolean existsByNombre(@Param("nombre") String nombre);
}
