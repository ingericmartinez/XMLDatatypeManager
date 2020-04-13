package app.springhibernate.model.dd;


import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import app.controllers.dd.LoginController;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 */
@Entity
@Table(name="Datos")
@ManagedBean(name="data")
public class Dato {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
     
	@Column(name="nombre")
    private String nombre;
	
	@Column(name="tipo")
    private String tipo;
	
	@Column(name="patron")
    private String patron;
	
	@Column(name="longmin")
    private short longmin;
	//
	@Column(name="longmax")
    private short longmax;
	
	@Column(name="fijo")
    private String fijo;
	
	@Column(name="requerido")
    private String requerido;
	
	@Column(name="elementos")
    private String elementos;
	
	@Column(name="documentacion")
    private String documentacion;
	
	@Column(name="esquema")
    private String esquema;
	
	@Column(name="namespace")
    private String namespace;
	
	@Column(name="version")
    private String version;	

	@Column(name="subtipo")
    private String subtipo;
	
	public String getLogicid() {
		return logicid;
	}

	public void setLogicid(String logicid) {
		this.logicid = logicid;
	}


	@Column(name="logicid")
    private String logicid;
	//
	
	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	
	public String getEsquema() {
		return esquema;
	}

	public void setEsquema(String esquema) {
		this.esquema = esquema;

	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	public String getElementos() {
		return elementos;
	}

	public void setElementos(String elementos) {
		this.elementos = elementos;
	}

	public String getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}

		
	public short getLongmax() {
		return longmax;
	}

	public void setLongmax(short longmax) {
		this.longmax = longmax;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getRequerido() {
		return requerido;
	}

	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}


// fin
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPatron() {
		return patron;
	}

	public void setPatron(String patron) {
		this.patron = patron;
	}

	public short getLongmin() {
		return longmin;
	}

	public void setLongmin(short longmin) {
		this.longmin = longmin;
	}

	@Override
	public String toString() {
		return "Dato [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", patron=" + patron + ", longmin=" + longmin
				+ "]";
	}
	
	
	public String getDescription() {
		return nombre + " " + tipo + " " + patron + " " + longmin;
 	}
	
}