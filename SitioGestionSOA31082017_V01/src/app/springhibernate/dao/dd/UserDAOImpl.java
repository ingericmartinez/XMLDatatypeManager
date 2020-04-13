package app.springhibernate.dao.dd;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import app.springhibernate.model.dd.CatTipos;
import app.springhibernate.model.dd.Dato;
import app.springhibernate.model.dd.UserList;
/**
 * User data access object interface implementation
 * */
@Repository
public class UserDAOImpl implements UserDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<UserList> listUsers() {
    
    	try {
    		Session session = this.sessionFactory.getCurrentSession();
            List<UserList> usersList = session.createQuery("from UserList").list();
            for(UserList u : usersList){
                logger.info("User List::"+u);
                
            }
            return usersList;
		}
		catch(HibernateException e) {
			logger.error("Hibernate exception: "+e.getMessage());
			return null;
		}
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<CatTipos> listTipos() {
    	
    	try {
    		Session session = this.sessionFactory.getCurrentSession();
            List<CatTipos> usersList = session.createQuery("from CatTipos").list();
            for(CatTipos u : usersList){
                logger.info("User List::"+u);
                
            }
            return usersList;
		}
		catch(HibernateException e) {
			logger.error("Hibernate exception: "+e.getMessage());
			return null;
		}
    }
    

	
    @SuppressWarnings("unchecked")
    @Override
    public List<Dato> listDatos() {
    	
    	try {
    		Session session = this.sessionFactory.getCurrentSession();
            List<Dato> usersList = session.createQuery("from Dato").list();
            for(Dato u : usersList){
                logger.info("Dato List::"+u);
            }
            return usersList;
		}
		catch(HibernateException e) {
			logger.error("Hibernate exception: "+e.getMessage());
			return null;
		}
    }
    

	
	
 
}