package app.springhibernate.service.dd;

import java.util.List;

import app.springhibernate.model.dd.CatTipos;
import app.springhibernate.model.dd.Dato;
import app.springhibernate.model.dd.UserList;

public interface UserService {
	
	public List<UserList> listUsers();
	public List<CatTipos> listTipos();
	public List<Dato> listDatos();
  
}
