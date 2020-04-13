package app.springhibernate.dao.dd;

import java.util.List;

import app.springhibernate.model.dd.Dato;
/**
 * Dato data access object interface
 * */

public interface DataDAO {
	
	public void addDato(Dato p);
    public List<Dato> listDato();
    public List<Dato> listDatoNegocio(String user);
    public void deleteDato(Dato p);
    public void updateDato(Dato p);

}
