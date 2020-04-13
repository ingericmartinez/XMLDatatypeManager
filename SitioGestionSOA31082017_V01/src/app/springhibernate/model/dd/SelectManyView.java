package app.springhibernate.model.dd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import app.springhibernate.dao.dd.DataDAOImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import app.springhibernate.model.dd.Dato;
 
@ManagedBean
@Repository
public class SelectManyView {
     
    private List<String> selectedOptions;
	private List<String> selectedItems;
	
	private static final Logger logger = LoggerFactory.getLogger(SelectManyView.class);
	private SessionFactory sessionFactory;
	
    public List<String> getSelectedItems() {
    	List<String> lista = new ArrayList<String>();
    	
    	return lista;
	}

	public void setSelectedItems(List<String> selectedItems) {
		this.selectedItems = selectedItems;
	}


     
    public List<String> getSelectedOptions() {    	
        return selectedOptions;
    }
 
    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }
}