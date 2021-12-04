package com.app.parametrizacion.services;

import java.io.IOException;
import java.util.Date;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.parametrizacion.models.ServiciosParametros;
import com.app.parametrizacion.repository.ServiciosParametrosRepository;

@Service
public class ServiciosService implements IServiciosService {

	@Autowired
	ServiciosParametrosRepository spRepository;
	
	@Override
	public ServiciosParametros ponerImagen(String nombre, MultipartFile imagen) {
		ServiciosParametros sP = spRepository.findByNombre(nombre);
		try {
			String fileName = imagen.getOriginalFilename();
			sP.setName(fileName);
			sP.setCreatedtime(new Date());
			sP.setContent(new Binary(imagen.getBytes()));
			sP.setContenttype(imagen.getContentType());
			sP.setSize(imagen.getSize());
			return sP;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
