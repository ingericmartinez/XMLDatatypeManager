package app.springhibernate.service.dd;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.springhibernate.dao.dd.DataDAO;
import app.springhibernate.model.dd.Dato;


@Service
@ManagedBean(name = "dataService")
@SessionScoped
public class DataServiceImpl implements DataService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1120798525255629783L;
	private static final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
	
	public DataDAO getDataDAO() {
		return dataDAO;
	}

	public void setDataDAO(DataDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	private DataDAO dataDAO;


	@Override
	@Transactional
	public void addDato(Dato c) {
		this.dataDAO.addDato(c);
	}

	@Override
	@Transactional
	public List<Dato> listDato() {
		return this.dataDAO.listDato();
	}

	@Override
	@Transactional
	public List<Dato> listDatoNegocio(String user) {
		return this.dataDAO.listDatoNegocio(user);
	}

	
	@Override
	@Transactional
	public void deleteDato(Dato c) {
		this.dataDAO.deleteDato(c);
	}

	@Override
	@Transactional
	public void updateDato(Dato c) {
		this.dataDAO.updateDato(c);
	}

	@Override
	public void testLog() {
		
	}

}