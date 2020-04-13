package app.springhibernate.service.dd;

import java.util.List;

import app.springhibernate.model.dd.Dato;

public interface DataService {
	
	public void addDato(Dato c);
    public List<Dato> listDato();
    public List<Dato> listDatoNegocio(String user);
    public void deleteDato(Dato c);
    public void updateDato(Dato c);
    public void testLog();
}
