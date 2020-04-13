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
@Table(name="cat_tipos_esquema")
@ManagedBean(name="CatTipos")
public class CatTipos {


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
	@Column(name="nombre")
    private String nombre;
    
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}




	@Column(name="estatus")
    private String estatus;
   

    @Override
    public String toString(){
        return "id="+id+", name="+nombre + " " ;
    }
}