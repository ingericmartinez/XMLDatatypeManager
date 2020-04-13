package app.springhibernate.dao.dd;

import java.util.List;

import app.springhibernate.model.dd.CatTipos;
import app.springhibernate.model.dd.Dato;
import app.springhibernate.model.dd.UserList;
/**
 * User data access object interface
 * */
public interface UserDAO {
	
	public List<UserList> listUsers();
	public List<CatTipos> listTipos();
	public List<Dato> listDatos();
}
