package com.app.parametrizacion.models;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parametrosflutter")
public class ParametrosFlutter {

	@Id
	private String id;

	@NotBlank(message = "Username cannot be null")
	@Size(max = 20)
	@Indexed(unique = true)
	private String username;

	private List<Double> colorPrimario;
	private List<Double> colorSecundario;
	private List<String> imagenes;
	private String tituloLogin;
	private String textoContenidoLogin;
	private String textoBotonLogin;
	private String tituloHomePage;
	private String textoContenidoHomePage;
	private List<Double> colorBotonesHomePage;

	public ParametrosFlutter() {
	}

	public ParametrosFlutter(String username, List<Double> colorPrimario, List<Double> colorSecundario,
			List<String> imagenes, String tituloLogin, String textoContenidoLogin, String textoBotonLogin,
			String tituloHomePage, String textoContenidoHomePage, List<Double> colorBotonesHomePage) {
		super();
		this.username = username;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.imagenes = imagenes;
		this.tituloLogin = tituloLogin;
		this.textoContenidoLogin = textoContenidoLogin;
		this.textoBotonLogin = textoBotonLogin;
		this.tituloHomePage = tituloHomePage;
		this.textoContenidoHomePage = textoContenidoHomePage;
		this.colorBotonesHomePage = colorBotonesHomePage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Double> getColorPrimario() {
		return colorPrimario;
	}

	public void setColorPrimario(List<Double> colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public List<Double> getColorSecundario() {
		return colorSecundario;
	}

	public void setColorSecundario(List<Double> colorSecundario) {
		this.colorSecundario = colorSecundario;
	}

	public List<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}

	public String getTituloLogin() {
		return tituloLogin;
	}

	public void setTituloLogin(String tituloLogin) {
		this.tituloLogin = tituloLogin;
	}

	public String getTextoContenidoLogin() {
		return textoContenidoLogin;
	}

	public void setTextoContenidoLogin(String textoContenidoLogin) {
		this.textoContenidoLogin = textoContenidoLogin;
	}

	public String getTextoBotonLogin() {
		return textoBotonLogin;
	}

	public void setTextoBotonLogin(String textoBotonLogin) {
		this.textoBotonLogin = textoBotonLogin;
	}

	public String getTituloHomePage() {
		return tituloHomePage;
	}

	public void setTituloHomePage(String tituloHomePage) {
		this.tituloHomePage = tituloHomePage;
	}

	public String getTextoContenidoHomePage() {
		return textoContenidoHomePage;
	}

	public void setTextoContenidoHomePage(String textoContenidoHomePage) {
		this.textoContenidoHomePage = textoContenidoHomePage;
	}

	public List<Double> getColorBotonesHomePage() {
		return colorBotonesHomePage;
	}

	public void setColorBotonesHomePage(List<Double> colorBotonesHomePage) {
		this.colorBotonesHomePage = colorBotonesHomePage;
	}

}
