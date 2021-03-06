package com.app.parametrizacion.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.parametrizacion.clients.ProyectosFeignClient;
import com.app.parametrizacion.models.Parametros;
import com.app.parametrizacion.models.ParametrosFlutter;
import com.app.parametrizacion.models.Proyectos;
import com.app.parametrizacion.models.ServiciosParametros;
import com.app.parametrizacion.repository.ParametrosFlutterRepository;
import com.app.parametrizacion.repository.ParametrosRepository;
import com.app.parametrizacion.repository.ServiciosParametrosRepository;
import com.app.parametrizacion.services.IServiciosService;

@RestController
public class ParametrosController {

	@Autowired
	ServiciosParametrosRepository spRepository;

	@Autowired
	IServiciosService sService;

	@Autowired
	ParametrosRepository pRepository;

	@Autowired
	ParametrosFlutterRepository pfRepository;

	@Autowired
	ProyectosFeignClient pClient;

	@PutMapping("/parametros/arreglar/")
	@ResponseStatus(code = HttpStatus.OK)
	public void arreglarParametros() {
		Parametros p = pRepository.findByUsername("parameters");
		List<Proyectos> pr = pClient.getProyectos();
		List<Integer> lI = new ArrayList<Integer>();
		for (int i = 0; i < pr.size(); i++) {
			lI.add(i + 1);
		}
		p.setCodigoProyectos(lI);
		pRepository.save(p);
	}
	
	@GetMapping("/parametros/proyectos/obtener-codigo/")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer obtenerCodigo() {
		return pRepository.findByUsername("parameters").getCodigoProyectos().size();
	}
	
	@GetMapping("/parametros/proyectos/obtener-lista-codigos/")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Integer> obtenerListaCodigo() {
		return pRepository.findByUsername("parameters").getCodigoProyectos();
	}
	
	@PutMapping("/parametros/proyectos/agregar-proyecto/")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean agregarProyecto() throws IOException{
		try {
			Parametros p = pRepository.findByUsername("parameters");
			List<Integer> lP = p.getCodigoProyectos();
			lP.add(lP.size()+1);
			p.setCodigoProyectos(lP);
			pRepository.save(p);
			return true;
		} catch (Exception e) {
			throw new IOException("error agregando codigo proyecto: " + e.getMessage());
		}
	}

	@PostMapping("/parametros/flutter/crear/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> crearParametrosFlutter(@RequestBody @Validated ParametrosFlutter pF) {
		pfRepository.save(pF);
		return ResponseEntity.ok("Creacion parametros flutter exitosa");
	}

	@GetMapping("/parametros/flutter/ver/{parametros}")
	@ResponseStatus(code = HttpStatus.OK)
	public ParametrosFlutter verParametrosFlutter(@PathVariable("parametros") String parametros) {
		ParametrosFlutter pF = pfRepository.findByUsername(parametros);
		return pF;
	}

	@PostMapping("/parametros/servicios/crear/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> crearServicios(@RequestBody @Validated ServiciosParametros sP) {
		spRepository.save(sP);
		return ResponseEntity.ok("Creacion servicio exitosa");
	}

	@PutMapping("/parametros/servicios/colocarImagen/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> editarImagen(@PathVariable("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen) {
		ServiciosParametros sP = sService.ponerImagen(nombre, imagen);
		spRepository.save(sP);
		return ResponseEntity.ok("Imagen colocada");
	}

	@GetMapping("/parametros/servicios/obtenerServicios/")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ServiciosParametros> listaServicios() {
		return spRepository.findAll();
	}

	@GetMapping("/parametros/servicios/obtenerNombre/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public ServiciosParametros listaServiciosNombre(@PathVariable("nombre") String nombre) {
		return spRepository.findByNombre(nombre);
	}

	// Descargar imagen
	@GetMapping(value = "/parametros/servicios/downloadImagen/{nombre}", produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public byte[] image(@PathVariable("nombre") String nombre) {
		ServiciosParametros sP = spRepository.findByNombre(nombre);
		byte[] data = null;
		ServiciosParametros file = spRepository.findImageById(sP.getId(), ServiciosParametros.class);
		if (file != null) {
			data = file.getContent().getData();
		}
		return data;
	}

	@GetMapping("/parametros/get/lista")
	public List<Parametros> getParametros() {
		return pRepository.findAll();
	}

	@PostMapping("/parametros/crear/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String crearUsuario(@RequestBody Parametros paremetro) {
		pRepository.save(paremetro);
		return "Parametros creados exitosamente";
	}

	@GetMapping("/parametros/get/colorPrimario")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Double> getColorPrimario() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getColorPrimario();
	}

	@PutMapping("/parametros/editar/colorPrimario")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarColorPrimario(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setColorPrimario(parametro.getColorPrimario());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/colorSecundario")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Double> getColorSecundario() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getColorSecundario();
	}

	@PutMapping("/parametros/editar/colorSecundario")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarcolorSecundario(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setColorSecundario(parametro.getColorSecundario());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/logotipo")
	@ResponseStatus(code = HttpStatus.OK)
	public String getLogotipo() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getLogotipo();
	}

	@PutMapping("/parametros/editar/logotipo")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarLogotipo(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setLogotipo(parametro.getLogotipo());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/imagenes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getImagenes() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getImagenes();
	}

	@PutMapping("/parametros/editar/imagenes")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarImagenes(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setImagenes(parametro.getImagenes());
		return pRepository.save(param);
	}

	@PutMapping("/parametros/terminosycondiciones/editar/")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTerminosYCondiciones(@RequestParam("tyc") String tyc) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTerminosYCondiciones(tyc);
		return pRepository.save(param);
	}

	@GetMapping("/parametros/terminosycondiciones/obtener/")
	@ResponseStatus(code = HttpStatus.OK)
	public String obtenerTerminosYCondiciones() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTerminosYCondiciones();
	}

	@GetMapping("/parametros/get/representacionMuro")
	@ResponseStatus(code = HttpStatus.OK)
	public String getRepresentacionMuro() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getRepresentacionMuro();
	}

	@PutMapping("/parametros/editar/representacionMuro")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarRepresentacionMuro(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setRepresentacionMuro(parametro.getRepresentacionMuro());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/tituloLogin")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTituloLogin() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTituloLogin();
	}

	@PutMapping("/parametros/editar/tituloLogin")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTituloLogin(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTituloLogin(parametro.getTituloLogin());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoContenidoLogin")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTextoContenidoLogin() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoContenidoLogin();
	}

	@PutMapping("/parametros/editar/textoContenidoLogin")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoContenidoLogin(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoContenidoLogin(parametro.getTextoContenidoLogin());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoBotonLogin")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTextoBotonLogin() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoBotonLogin();
	}

	@PutMapping("/parametros/editar/textoBotonLogin")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoBotonLogin(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoBotonLogin(parametro.getTextoBotonLogin());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/tituloOTP")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTituloOTP() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTituloOTP();
	}

	@PutMapping("/parametros/editar/tituloOTP")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTituloOTP(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTituloOTP(parametro.getTituloOTP());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoContenidoOTP")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTextoContenidoOTP() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoContenidoOTP();
	}

	@PutMapping("/parametros/editar/textoContenidoOTP")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoContenidoOTP(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoContenidoOTP(parametro.getTextoContenidoOTP());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoBotonOTP")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTextoBotonOTP() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoBotonOTP();
	}

	@PutMapping("/parametros/editar/textoBotonOTP")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoBotonOTP(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoBotonOTP(parametro.getTextoBotonOTP());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/tituloRegistro")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTituloRegistro() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoBotonOTP();
	}

	@PutMapping("/parametros/editar/tituloRegistro")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTituloRegistro(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTituloRegistro(parametro.getTituloRegistro());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/entradasRegistro")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getEntradasRegistro() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getEntradasRegistro();
	}

	@PutMapping("/parametros/editar/entradasRegistro")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarEntradasRegistro(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setEntradasRegistro(parametro.getEntradasRegistro());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoBotonesRegistro")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getTextoBotonesRegistro() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoBotonesRegistro();
	}

	@PutMapping("/parametros/editar/textoBotonesRegistro")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoBotonesRegistro(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoBotonesRegistro(parametro.getTextoBotonesRegistro());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/tituloWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTituloWrapWidgets() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTituloWrapWidgets();
	}

	@PutMapping("/parametros/editar/tituloWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTituloWrapWidgets(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTituloWrapWidgets(parametro.getTituloWrapWidgets());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoContenidoWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTextoContenidoWrapWidgets() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoContenidoWrapWidgets();
	}

	@PutMapping("/parametros/editar/textoContenidoWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoContenidoWrapWidgets(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoContenidoWrapWidgets(parametro.getTextoContenidoWrapWidgets());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoBotonesWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getTextoBotonesWrapWidgets() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoBotonesWrapWidgets();
	}

	@PutMapping("/parametros/editar/textoBotonesWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoBotonesWrapWidgets(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoBotonesWrapWidgets(parametro.getTextoBotonesWrapWidgets());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/chipsWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getChipsWrapWidgets() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getChipsWrapWidgets();
	}

	@PutMapping("/parametros/editar/chipsWrapWidgets")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarChipsWrapWidgets(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setChipsWrapWidgets(parametro.getChipsWrapWidgets());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/tituloHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTituloHomePage() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTituloHomePage();
	}

	@PutMapping("/parametros/editar/tituloHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTituloHomePage(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTituloHomePage(parametro.getTituloHomePage());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/textoContenidoHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTextoContenidoHomePage() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTextoContenidoHomePage();
	}

	@PutMapping("/parametros/editar/textoContenidoHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTextoContenidoHomePage(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTextoContenidoHomePage(parametro.getTextoContenidoHomePage());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/iconosMenuHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getIconosMenuHomePage() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getIconosMenuHomePage();
	}

	@PutMapping("/parametros/editar/iconosMenuHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarIconosMenuHomePage(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setIconosMenuHomePage(parametro.getIconosMenuHomePage());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/colorBotonesHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Double> getColorBotonesHomePage() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getColorBotonesHomePage();
	}

	@PutMapping("/parametros/editar/colorBotonesHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarColorBotonesHomePage(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setColorBotonesHomePage(parametro.getColorBotonesHomePage());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/colorNavegacionHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Double> getColorNavegacionHomePage() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getColorNavegacionHomePage();
	}

	@PutMapping("/parametros/editar/colorNavegacionHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarColorNavegacionHomePage(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setColorNavegacionHomePage(parametro.getColorNavegacionHomePage());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/iconosNavegacionHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getIconosNavegacionHomePage() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getIconosNavegacionHomePage();
	}

	@PutMapping("/parametros/editar/iconosNavegacionHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarIconosNavegacionHomePage(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setIconosNavegacionHomePage(parametro.getIconosNavegacionHomePage());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/destinoNavHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getDestinoNavHomePage() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getIconosNavegacionHomePage();
	}

	@PutMapping("/parametros/editar/destinoNavHomePage")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarDestinoNavHomePage(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setDestinoNavHomePage(parametro.getDestinoNavHomePage());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/tituloMapa")
	@ResponseStatus(code = HttpStatus.OK)
	public String getTituloMapa() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getTituloMapa();
	}

	@PutMapping("/parametros/editar/tituloMapa")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarTituloMapa(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setTituloMapa(parametro.getTituloMapa());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/ubicacionMuro")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getUbicacionMuro() {
		Parametros param = pRepository.findByUsername("parameters");
		return param.getUbicacionMuro();
	}

	@PutMapping("/parametros/editar/ubicacionMuro")
	@ResponseStatus(code = HttpStatus.OK)
	public Parametros editarUbicacionMuro(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		param.setUbicacionMuro(parametro.getUbicacionMuro());
		return pRepository.save(param);
	}

	@GetMapping("/parametros/get/labelMuros")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Integer> getLabelMuros() {
		Parametros parame = pRepository.findByUsername("parameters");
		return parame.getLabelMuros();
	}

	@PutMapping("/parametros/editar/labelMurosManejo")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean editarLabelMurosManejo() throws IOException {
		Parametros param = pRepository.findByUsername("parameters");
		Integer countMuro = param.getLabelMuros().size() + 1;
		List<Integer> listaLabelMuro = param.getLabelMuros();
		listaLabelMuro.add(countMuro);
		param.setLabelMuros(listaLabelMuro);
		pRepository.save(param);
		return true;
	}

	@PutMapping("/parametros/editar/labelMuroManejoDel/{number}")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean editarLabelMuroManejoDelete(@PathVariable Integer number) throws IOException {
		Parametros paramMuro = pRepository.findByUsername("parameters");
		List<Integer> listaLabelMuro = new ArrayList<Integer>();
		for (int i = 0; i <= number; i++) {
			listaLabelMuro.add(i + 1);
		}
		paramMuro.setLabelMuros(listaLabelMuro);
		pRepository.save(paramMuro);
		return true;
	}

	@PutMapping("/parametros/editar")
	@ResponseStatus(HttpStatus.CREATED)
	public Parametros editar(@RequestBody Parametros parametro) {
		Parametros param = pRepository.findByUsername("parameters");
		if (parametro.getColorPrimario() != null)
			param.setColorPrimario(parametro.getColorPrimario());
		if (parametro.getColorSecundario() != null)
			param.setColorSecundario(parametro.getColorSecundario());
		if (parametro.getLogotipo() != null)
			param.setLogotipo(parametro.getLogotipo());
		if (parametro.getImagenes() != null)
			param.setImagenes(parametro.getImagenes());
		if (parametro.getRepresentacionMuro() != null)
			param.setRepresentacionMuro(parametro.getRepresentacionMuro());
		if (parametro.getTituloLogin() != null)
			param.setTituloLogin(parametro.getTituloLogin());
		if (parametro.getTextoContenidoLogin() != null)
			param.setTituloLogin(parametro.getTituloLogin());
		if (parametro.getTextoBotonLogin() != null)
			param.setTextoBotonLogin(parametro.getTextoBotonLogin());
		if (parametro.getTituloOTP() != null)
			param.setTituloOTP(parametro.getTituloOTP());
		if (parametro.getTextoContenidoOTP() != null)
			param.setTextoContenidoOTP(parametro.getTextoContenidoOTP());
		if (parametro.getTextoBotonOTP() != null)
			param.setTextoBotonOTP(parametro.getTextoBotonOTP());
		if (parametro.getTituloRegistro() != null)
			param.setTituloRegistro(parametro.getTituloRegistro());
		if (parametro.getEntradasRegistro() != null)
			param.setEntradasRegistro(parametro.getEntradasRegistro());
		if (parametro.getTextoBotonesRegistro() != null)
			param.setTextoBotonesRegistro(parametro.getTextoBotonesRegistro());
		if (parametro.getTituloWrapWidgets() != null)
			param.setTituloWrapWidgets(parametro.getTituloWrapWidgets());
		if (parametro.getTextoContenidoWrapWidgets() != null)
			param.setTextoContenidoWrapWidgets(parametro.getTextoContenidoWrapWidgets());
		if (parametro.getTextoBotonesWrapWidgets() != null)
			param.setTextoBotonesWrapWidgets(parametro.getTextoBotonesWrapWidgets());
		if (parametro.getChipsWrapWidgets() != null)
			param.setChipsWrapWidgets(parametro.getChipsWrapWidgets());
		if (parametro.getTituloHomePage() != null)
			param.setTituloHomePage(parametro.getTituloHomePage());
		if (parametro.getTextoContenidoHomePage() != null)
			param.setTextoContenidoHomePage(parametro.getTextoContenidoHomePage());
		if (parametro.getIconosMenuHomePage() != null)
			param.setIconosMenuHomePage(parametro.getIconosMenuHomePage());
		if (parametro.getColorBotonesHomePage() != null)
			param.setColorBotonesHomePage(parametro.getColorBotonesHomePage());
		if (parametro.getColorNavegacionHomePage() != null)
			param.setColorNavegacionHomePage(parametro.getColorNavegacionHomePage());
		if (parametro.getIconosNavegacionHomePage() != null)
			param.setIconosNavegacionHomePage(parametro.getIconosNavegacionHomePage());
		if (parametro.getDestinoNavHomePage() != null)
			param.setDestinoNavHomePage(parametro.getDestinoNavHomePage());
		if (parametro.getTituloMapa() != null)
			param.setTituloMapa(parametro.getTituloMapa());
		if (parametro.getUbicacionMuro() != null)
			param.setUbicacionMuro(parametro.getUbicacionMuro());
		return pRepository.save(param);
	}

}