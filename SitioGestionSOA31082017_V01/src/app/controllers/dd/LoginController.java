package app.controllers.dd;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.lang.Exception;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.springhibernate.model.dd.CatTipos;
import app.springhibernate.model.dd.Dato;
import app.springhibernate.model.dd.UserList;
import app.springhibernate.model.dd.*;
import app.validators.dd.*;
import app.springhibernate.dao.dd.*;


import javax.faces.context.FacesContext;
import javax.naming.*;

@ManagedBean(name = "LoginController")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private String login;
	private String pass;
	private SessionFactory sessionFactory;
	private String perfil;
	private String usuario;
	private String departamento;
	private String namespace;

	public List<Dato> getListaDatosUsuarios() {
		return listaDatosUsuarios;
	}

	public void setListaDatosUsuarios(List<Dato> listaDatosUsuarios) {
		this.listaDatosUsuarios = listaDatosUsuarios;
	}

	private List<Dato> listaDatosUsuarios;

	private List<String> listaTipos;

	public List<String> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<String> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String login() {
		
		 RequestContext context = RequestContext.getCurrentInstance();
	
		   boolean loggedIn = true;
	        String userName = "";
	        User u = new User();
	        String profile = "";
	        String departamento ="";
	        String namespace = "http://www.amx.com.mx/mexico/telcel/di/sds/[gerencia]/[departamento]/[Servicio Negocio]";
	        FacesMessage message = null;
	        
        boolean res;
        boolean validaPassword = false;
        
        String nombre ="";
        boolean desarrollador = false;
        boolean liderproyecto = false;
        boolean oficinaarquitectura = false;
        boolean oa = false;
        
        String nombreu ="";
        boolean desarrolladoru = false;
        boolean liderproyectou = false;
        boolean oau = false;
        
        try {
        	
        	//validar password               
        	validaPassword = new SelectEntry(login,pass).selectPassword(login, pass);
        
        	if(!validaPassword)
        	{
        		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password Incorrecto");
            	FacesContext.getCurrentInstance().addMessage(null, message);
            	//context.addCallbackParam("loggedIn", loggedIn);
            	return "Login";
        		
        	}
        	
        	
         	
        	//obtener valores del LDAP
        	     nombre = new SelectEntry(login,pass).getEntry(login);
        	     System.out.println("nombre " + nombre);
        	     desarrollador = new SelectEntry(login,pass).getEntryDesarrollador(login);
        	     System.out.println("desarrollador " + desarrollador);
        	     liderproyecto = new SelectEntry(login,pass).getEntryProyecto(login);
        	     System.out.println("liderproyecto "+ liderproyecto);
        	     oa = new SelectEntry(login,pass).getEntryArquitectura(login);
        	     System.out.println("oa " + oa);
        	     departamento = new SelectEntry(login,pass).getEntryDeptos(login);
        	     System.out.println("depto " + departamento);
                       
        } catch (NamingException e) {
        	
        	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de autenticacion de usuario:", login);
            FacesContext.getCurrentInstance().addMessage(null, message);
                       // TODO Auto-generated catch block
                       e.printStackTrace();
        }
        
        
   	     
   	  String array [] = {"","","",""};
   	  if(null != departamento && departamento.indexOf("-") != -1)
   	  {
   		  if(departamento.split("-").length > 1)
   		  {
   			   array  = departamento.split("-");			  
   			  
   		  }
   		  
   		  String gerencia = array[2];
   		  System.out.println(array[2]);
   		  String depto = array[3];
   		  System.out.println(array[3]);
   		  String nv = namespace.replace("[gerencia]",array[2]);
   	      String nvv = nv.replace("[departamento]",array[3]);
   		  System.out.println(nvv);
   		  namespace = nvv;
   	  }
             
        if(desarrollador)
        {
        	
        	profile = "desarrollador";
        	System.out.println("desarrollador : " + profile );

        }
        
        if(liderproyecto)
        {
        	
        	profile = "liderproyecto";
        	System.out.println("lider : " + profile );

        }
        
        if(oa)
        {
        	
        	profile = "oficinaarquitectura";
        	System.out.println("oficinaarquitectura : " + profile );
       }
        

        
        if(profile.equals("") || null == profile)
          {
          	
          	System.out.println("error : " );
          	message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario NO valido:", profile);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "Login";
          }
       
      
        
        this.usuario = nombre;
        this.departamento = departamento;
        this.namespace = namespace;
        this.perfil= profile;
        
       
		Session session = this.sessionFactory.openSession();
		Criteria cr = session.createCriteria(UserList.class);
	
		Criteria crt = session.createCriteria(CatTipos.class);

		Criterion estatus = Restrictions.eq("estatus", "activado");
		LogicalExpression andExpest = Restrictions.and(estatus, estatus);
		crt.add(andExpest);

		@SuppressWarnings("unchecked")
		List<CatTipos> listTipos = crt.list();
		List<String> listNombres = new ArrayList<String>();
		if (crt.list().size() > 0) {
			

			for (int i = 0; i < crt.list().size(); i++) {
				listNombres.add(listTipos.get(i).getNombre().toString());

			}

		}

		this.listaTipos = listNombres;
	
		Criteria crf = session.createCriteria(Dato.class);

		Criterion namespa = Restrictions.like("namespace", "amx"); // esb o
																	// depto
		
		LogicalExpression andExpnmsp = Restrictions.and(namespa, namespa);
		crf.add(andExpnmsp);

		List<Dato> listTiposns = crf.list();
		if (crf.list().size() > 0) {
		

			for (int i = 0; i < crf.list().size(); i++) {

			}

		}

		this.listaDatosUsuarios = listTiposns;
		// lista Filtrada

	

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("namespace", namespace);

		// validacion
		// consulta de registro guardado

		Criteria crff = session.createCriteria(Dato.class);

		Criterion namespaa = Restrictions.like("nombre", "ype");
		LogicalExpression andExpnmspp = Restrictions.and(namespaa, namespaa);
		crf.add(andExpnmspp);

		@SuppressWarnings("unchecked")
		List<Dato> listTiposnss = crff.list();

		if (listTiposnss.size() > 0) {

			for (int i = 0; i < listTiposnss.size(); i++) {
				listTiposnss.get(i).getNombre();

			}

		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaid", listTiposnss);

	
		Criteria crfff = session.createCriteria(Dato.class);
		Criterion namespaaaa = Restrictions.like("nombre", "ype");
		LogicalExpression andExpnmsppp = Restrictions.and(namespaaaa, namespaaaa);
		crf.add(andExpnmsppp);
		@SuppressWarnings("unchecked")
		List<Dato> listTiposnsss = crfff.list();
		if (listTiposnsss.size() > 0) {
			for (int i = 0; i < listTiposnsss.size(); i++) {
				listTiposnsss.get(i).getNombre();
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaid", listTiposnss);

		Criteria ac = session.createCriteria(Dato.class);
		Criterion ns = Restrictions.like("nombre", "ype");
		LogicalExpression ae = Restrictions.and(ns, ns);
		ac.add(ae);
		@SuppressWarnings("unchecked")
		List<Dato> lts = ac.list();
		if (lts.size() > 0) {
			for (int i = 0; i < lts.size(); i++) {
				lts.get(i).getNombre();
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listavalidacion", listTiposnss);

		if (desarrollador) {

			profile = "desarrollador";
		}
		if (liderproyecto) {

			profile = "liderproyecto";
		}
		if (oficinaarquitectura) {

			profile = "oficinaarquitectura";
		} else {

			profile = "desarrollador";

		}

	
		if (loggedIn) {
			logger.info("usuario match ::" + userName);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido: " + usuario, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			context.addCallbackParam("loggedIn", loggedIn);

		
			return "MenuInicial";
		} else {
			logger.info("usuario incorrecto");
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error en Password");
			FacesContext.getCurrentInstance().addMessage(null, message);
			context.addCallbackParam("loggedIn", loggedIn);
			return "Login";
		}

	}

	public String corporativos() {

		FacesMessage message = null;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Captura Tipos de Datos Corporativos", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "DatosCorporativos";

	}

	public String corporativoscomplejos() {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Captura Diccionario Departamental", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "DatosNegocio";

	}

	public String simples() {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Captura Diccionario Negocio Simples", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "DatosList";

	}

	public String simplescomplejos() {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Captura Diccionario Negocio Complejos", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "DatosListComplex";

	}

	public String diccionario() {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Diccionario de datos", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "Diccionario";

	}

	public String creacionxsd() {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "creacion de XSD", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "CrearXSD";

	}

	public String acerca() {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acerca de", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "Acerca";

	}

}
