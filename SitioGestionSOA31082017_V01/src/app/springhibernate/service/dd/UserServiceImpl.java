package app.springhibernate.service.dd;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.springhibernate.dao.dd.UserDAO;
import app.springhibernate.model.dd.CatTipos;
import app.springhibernate.model.dd.Dato;
import app.springhibernate.model.dd.UserList;

@Service
@ManagedBean(name="userService")
@SessionScoped
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	 
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
 
    @Override
    @Transactional
    public List<UserList> listUsers() {
    	System.out.println(this.userDAO.listUsers().get(0).getNombre() + "  date" + new Date());
        return this.userDAO.listUsers();
    }
    
    @Override
    @Transactional
    public List<Dato> listDatos() {
    
        return this.userDAO.listDatos();
    }

	@Override
	public List<CatTipos> listTipos() {
		// TODO Auto-generated method stub
		 return this.userDAO.listTipos();
	}
    
    
}