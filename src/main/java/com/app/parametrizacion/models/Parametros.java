package com.app.parametrizacion.models;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parametros")
public class Parametros {

	@Id
	private String id;

	@NotBlank(message = "Username cannot be null")
	@Size(max = 20)
	@Indexed(unique = true)
	private String username;

	private List<Double> colorPrimario;
	private List<Double> colorSecundario;
	private String logotipo;
	private List<String> imagenes;
	private String representacionMuro;
	private String tituloLogin;
	private String textoContenidoLogin;
	private String textoBotonLogin;
	private String tituloOTP;
	private String textoContenidoOTP;
	private String textoBotonOTP;
	private String tituloRegistro;
	private List<String> entradasRegistro;
	private List<String> textoBotonesRegistro;
	private String tituloWrapWidgets;
	private String textoContenidoWrapWidgets;
	private List<String> textoBotonesWrapWidgets;
	private List<String> chipsWrapWidgets;
	private String tituloHomePage;
	private String textoContenidoHomePage;
	private List<String> iconosMenuHomePage;
	private List<Double> colorBotonesHomePage;
	private List<Double> colorNavegacionHomePage;
	private List<String> iconosNavegacionHomePage;
	private List<String> destinoNavHomePage;
	private String tituloMapa;
	private List<String> ubicacionMuro;
	private List<Integer> labelMuros;

	public Parametros() {
	}

	public Parametros(String username, List<Double> colorPrimario, List<Double> colorSecundario, String logotipo,
			List<String> imagenes, String representacionMuro, String tituloLogin, String textoContenidoLogin,
			String textoBotonLogin, String tituloOTP, String textoContenidoOTP, String textoBotonOTP,
			String tituloRegistro, List<String> entradasRegistro, List<String> textoBotonesRegistro,
			String tituloWrapWidgets, String textoContenidoWrapWidgets, List<String> textoBotonesWrapWidgets,
			List<String> chipsWrapWidgets, String tituloHomePage, String textoContenidoHomePage,
			List<String> iconosMenuHomePage, List<Double> colorBotonesHomePage, List<Double> colorNavegacionHomePage,
			List<String> iconosNavegacionHomePage, List<String> destinoNavHomePage, String tituloMapa,
			List<String> ubicacionMuro, List<Integer> labelMuros) {
		super();
		this.username = username;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.logotipo = logotipo;
		this.imagenes = imagenes;
		this.representacionMuro = representacionMuro;
		this.tituloLogin = tituloLogin;
		this.textoContenidoLogin = textoContenidoLogin;
		this.textoBotonLogin = textoBotonLogin;
		this.tituloOTP = tituloOTP;
		this.textoContenidoOTP = textoContenidoOTP;
		this.textoBotonOTP = textoBotonOTP;
		this.tituloRegistro = tituloRegistro;
		this.entradasRegistro = entradasRegistro;
		this.textoBotonesRegistro = textoBotonesRegistro;
		this.tituloWrapWidgets = tituloWrapWidgets;
		this.textoContenidoWrapWidgets = textoContenidoWrapWidgets;
		this.textoBotonesWrapWidgets = textoBotonesWrapWidgets;
		this.chipsWrapWidgets = chipsWrapWidgets;
		this.tituloHomePage = tituloHomePage;
		this.textoContenidoHomePage = textoContenidoHomePage;
		this.iconosMenuHomePage = iconosMenuHomePage;
		this.colorBotonesHomePage = colorBotonesHomePage;
		this.colorNavegacionHomePage = colorNavegacionHomePage;
		this.iconosNavegacionHomePage = iconosNavegacionHomePage;
		this.destinoNavHomePage = destinoNavHomePage;
		this.tituloMapa = tituloMapa;
		this.ubicacionMuro = ubicacionMuro;
		this.labelMuros = labelMuros;
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

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public List<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}

	public String getRepresentacionMuro() {
		return representacionMuro;
	}

	public void setRepresentacionMuro(String representacionMuro) {
		this.representacionMuro = representacionMuro;
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

	public String getTituloOTP() {
		return tituloOTP;
	}

	public void setTituloOTP(String tituloOTP) {
		this.tituloOTP = tituloOTP;
	}

	public String getTextoContenidoOTP() {
		return textoContenidoOTP;
	}

	public void setTextoContenidoOTP(String textoContenidoOTP) {
		this.textoContenidoOTP = textoContenidoOTP;
	}

	public String getTextoBotonOTP() {
		return textoBotonOTP;
	}

	public void setTextoBotonOTP(String textoBotonOTP) {
		this.textoBotonOTP = textoBotonOTP;
	}

	public String getTituloRegistro() {
		return tituloRegistro;
	}

	public void setTituloRegistro(String tituloRegistro) {
		this.tituloRegistro = tituloRegistro;
	}

	public List<String> getEntradasRegistro() {
		return entradasRegistro;
	}

	public void setEntradasRegistro(List<String> entradasRegistro) {
		this.entradasRegistro = entradasRegistro;
	}

	public List<String> getTextoBotonesRegistro() {
		return textoBotonesRegistro;
	}

	public void setTextoBotonesRegistro(List<String> textoBotonesRegistro) {
		this.textoBotonesRegistro = textoBotonesRegistro;
	}

	public String getTituloWrapWidgets() {
		return tituloWrapWidgets;
	}

	public void setTituloWrapWidgets(String tituloWrapWidgets) {
		this.tituloWrapWidgets = tituloWrapWidgets;
	}

	public String getTextoContenidoWrapWidgets() {
		return textoContenidoWrapWidgets;
	}

	public void setTextoContenidoWrapWidgets(String textoContenidoWrapWidgets) {
		this.textoContenidoWrapWidgets = textoContenidoWrapWidgets;
	}

	public List<String> getTextoBotonesWrapWidgets() {
		return textoBotonesWrapWidgets;
	}

	public void setTextoBotonesWrapWidgets(List<String> textoBotonesWrapWidgets) {
		this.textoBotonesWrapWidgets = textoBotonesWrapWidgets;
	}

	public List<String> getChipsWrapWidgets() {
		return chipsWrapWidgets;
	}

	public void setChipsWrapWidgets(List<String> chipsWrapWidgets) {
		this.chipsWrapWidgets = chipsWrapWidgets;
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

	public List<String> getIconosMenuHomePage() {
		return iconosMenuHomePage;
	}

	public void setIconosMenuHomePage(List<String> iconosMenuHomePage) {
		this.iconosMenuHomePage = iconosMenuHomePage;
	}

	public List<Double> getColorBotonesHomePage() {
		return colorBotonesHomePage;
	}

	public void setColorBotonesHomePage(List<Double> colorBotonesHomePage) {
		this.colorBotonesHomePage = colorBotonesHomePage;
	}

	public List<Double> getColorNavegacionHomePage() {
		return colorNavegacionHomePage;
	}

	public void setColorNavegacionHomePage(List<Double> colorNavegacionHomePage) {
		this.colorNavegacionHomePage = colorNavegacionHomePage;
	}

	public List<String> getIconosNavegacionHomePage() {
		return iconosNavegacionHomePage;
	}

	public void setIconosNavegacionHomePage(List<String> iconosNavegacionHomePage) {
		this.iconosNavegacionHomePage = iconosNavegacionHomePage;
	}

	public List<String> getDestinoNavHomePage() {
		return destinoNavHomePage;
	}

	public void setDestinoNavHomePage(List<String> destinoNavHomePage) {
		this.destinoNavHomePage = destinoNavHomePage;
	}

	public String getTituloMapa() {
		return tituloMapa;
	}

	public void setTituloMapa(String tituloMapa) {
		this.tituloMapa = tituloMapa;
	}

	public List<String> getUbicacionMuro() {
		return ubicacionMuro;
	}

	public void setUbicacionMuro(List<String> ubicacionMuro) {
		this.ubicacionMuro = ubicacionMuro;
	}

	public List<Integer> getLabelMuros() {
		return labelMuros;
	}

	public void setLabelMuros(List<Integer> labelMuros) {
		this.labelMuros = labelMuros;
	}

}
