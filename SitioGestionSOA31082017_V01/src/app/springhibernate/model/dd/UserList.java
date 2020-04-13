package app.springhibernate.model.dd;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author
 *
 */
@Entity
@Table(name="USERS")
@ManagedBean(name="user")
public class UserList {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
	@Column(name="nombre")
    private String nombre;
    
	@Column(name="login")
    private String login;
    
	@Column(name="passhash")
    private String passhash;
	
	//inicio nuevos datos
	
	@Column(name="perfil")
    private String perfil;
	
	@Column(name="usuario")
    private String usuario;
    
	@Column(name="departamento")
    private String departamento;    
	
	@Column(name="namespace")
    private String namespace;
    
	//fin nuevos datos
	
	
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}


	
	public int getId() {
		return id;
	}

    public void setId(int id) {
		this.id = id;
	}

    public String getUsuario() {
		return usuario;
	}

    public void setusuario(String usuario) {
		this.usuario = usuario;
	}

    public String getLogin() {
		return login;
	}

    public void setLogin(String login) {
		this.login = login;
	}

    public String getPasshash() {
		return passhash;
	}

    public void setPasshash(String passhash) {
		this.passhash = passhash;
	}

    @Override
    public String toString(){
        return "id="+id+", name="+usuario + ", login="+login + ", passhash="+passhash;
    }
}