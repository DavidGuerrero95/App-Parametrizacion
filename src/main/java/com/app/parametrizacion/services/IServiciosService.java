package com.app.parametrizacion.services;

import org.springframework.web.multipart.MultipartFile;

import com.app.parametrizacion.models.ServiciosParametros;

public interface IServiciosService {

	ServiciosParametros ponerImagen(String nombre, MultipartFile imagen);

}
