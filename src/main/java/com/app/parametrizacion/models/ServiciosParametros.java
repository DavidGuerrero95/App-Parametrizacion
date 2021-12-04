package com.app.parametrizacion.models;

import java.util.Date;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "servicios")
public class ServiciosParametros {

	@Id
	private String id;

	@Indexed(unique = true)
	private String nombre;

	@Indexed(unique = true)
	private String nombreServicio;

	private String nombreIcono;
	private List<Double> color;
	private List<String> apis;

	private String name; // file name
	private Date createdtime; // upload time
	private Binary content; // file content
	private String contenttype; // file type
	private long size; // file size

	public ServiciosParametros() {
	}

	public ServiciosParametros(String nombre, String nombreServicio, String nombreIcono, List<Double> color,
			List<String> apis, String name, Date createdtime, Binary content, String contenttype, long size) {
		super();
		this.nombre = nombre;
		this.nombreServicio = nombreServicio;
		this.nombreIcono = nombreIcono;
		this.color = color;
		this.apis = apis;
		this.name = name;
		this.createdtime = createdtime;
		this.content = content;
		this.contenttype = contenttype;
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getNombreIcono() {
		return nombreIcono;
	}

	public void setNombreIcono(String nombreIcono) {
		this.nombreIcono = nombreIcono;
	}

	public List<Double> getColor() {
		return color;
	}

	public void setColor(List<Double> color) {
		this.color = color;
	}

	public List<String> getApis() {
		return apis;
	}

	public void setApis(List<String> apis) {
		this.apis = apis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Binary getContent() {
		return content;
	}

	public void setContent(Binary content) {
		this.content = content;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
