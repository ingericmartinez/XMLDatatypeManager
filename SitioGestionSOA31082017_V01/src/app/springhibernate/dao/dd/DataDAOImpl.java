package app.springhibernate.dao.dd;

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

import app.springhibernate.model.dd.Dato;
/**
 * Dato data access object interface implementation
 * */
@Repository
public class DataDAOImpl implements DataDAO {

	private static final Logger logger = LoggerFactory.getLogger(DataDAOImpl.class);
	private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
	@Override
	public void addDato(Dato p) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
	        session.save(p);
	        logger.info("Dato saved successfully, Dato Details="+p);
		}
		catch(HibernateException e) {
			logger.error("Hibernate exception: "+e.getMessage());
		}
	}

	@Override
	public List<Dato> listDato() {
		try {
			
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Dato> lista = session.createQuery("from Dato").list();
	        for(Dato c : lista) {
	            logger.info("Dato List::"+c);
	        }
	        return lista;
		}
		catch(HibernateException e) {
			logger.error("Hibernate exception: "+e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Dato> listDatoNegocio(String user) {
		try {
			
			Session session = this.sessionFactory.getCurrentSession();
				
			 Criteria cr = session.createCriteria(Dato.class);	       
             Criterion esquema = Restrictions.like("esquema","negocio"); 
             Criterion namespace = Restrictions.like("namespace",user); 
            LogicalExpression likeschenam = Restrictions.and(esquema,esquema);
            cr.add( likeschenam );
           
        		@SuppressWarnings("unchecked")
				List<Dato> lista = cr.list();   
        		
	        return lista;
		}
		catch(HibernateException e) {
			logger.error("Hibernate exception: "+e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteDato(Dato c) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(c);
	        logger.info("Dato deleted successfully, Dato Details="+c);
		}
		catch(HibernateException e) {
			logger.error("Hibernate exception: "+e.getMessage());
		}
	}

	@Override
	public void updateDato(Dato c) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.update(c);
	        logger.info("Dato updated successfully, Dato Details="+c);
		}
		catch(HibernateException e)
		{
			logger.error("Hibernate exception: "+e.getMessage());
		}
	}
}
