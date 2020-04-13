package app.controllers.dd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.springhibernate.model.dd.Dato;
import app.springhibernate.service.dd.DataService;

@ManagedBean(name = "DatosListController")
@ViewScoped
public class DatosListController implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DatosListController.class);
	private static final long serialVersionUID = 8604437196619149720L;

	@ManagedProperty("#{dataService}")

	private DataService dataService;

	// inicio
	private List<String> selectedOptions;
	private List<String> selectedItems;

	private List<String> selectedOptionsNegocio;
	private List<String> selectedItemsNegocio;

	public List<String> getSelectedOptionsNegocio() {
		return selectedOptionsNegocio;
	}

	public void setSelectedOptionsNegocio(List<String> selectedOptionsNegocio) {

		if (this.selectedOptionsNegocio != null) {

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("elementosNegocio",
					this.selectedOptionsNegocio);
		}

		this.selectedOptionsNegocio = selectedOptionsNegocio;

	}

	public List<String> getSelectedItemsNegocio() {
		List<String> lista = new ArrayList<String>();

		for (int i = 0; i < datosNegocio.size(); i++) {

			lista.add(setvacioNull(datosNegocio.get(i).getNombre()));
		}

		return lista;
	}

	public void setSelectedItemsNegocio(List<String> selectedItemsNegocio) {
		this.selectedItemsNegocio = selectedItemsNegocio;
	}

	public List<String> getSelectedItems() {
		List<String> lista = new ArrayList<String>();

		for (int i = 0; i < datos.size(); i++) {

			lista.add(setvacioNull(datos.get(i).getNombre()));
		}

		return lista;
	}

	public void setSelectedItems(List<String> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public List<String> getSelectedOptions() {

		return selectedOptions;
	}

	public void setSelectedOptions(List<String> selectedOptions) {

		if (this.selectedOptions != null) {

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("elementos",
					this.selectedOptions);
		}

		this.selectedOptions = selectedOptions;
	}

	// fin
	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public Dato getData() {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().get("namespace");

		data.setNamespace(context.getExternalContext().getSessionMap().get("namespace").toString());

		StringBuffer valoraux = new StringBuffer();
		if (this.selectedOptions != null) {
			for (int i = 0; i < this.selectedOptions.size(); i++) {
				valoraux.append(selectedOptions.get(i) + "|");

			}
			data.setElementos(valoraux.toString());
		}

		if (this.selectedOptions == null) {
		}

		return data;
	}

	public void setData(Dato data) {

		this.data = data;
	}

	public List<Dato> getDatos() {
		return datos;
	}

	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	private Dato data;
	private List<Dato> datos;
	private List<Dato> datosNegocio;

	@PostConstruct
	public void init() {

		try {

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		data = new Dato();

		// carga lista corporativa
		datos = dataService.listDato();

		// carga lista de usuario
		FacesContext context = FacesContext.getCurrentInstance();
		String user = context.getExternalContext().getSessionMap().get("namespace").toString();

		datosNegocio = dataService.listDatoNegocio(user);
		logger.info("Initialized");
	}

	@SuppressWarnings("unchecked")
	public String addDato() {

		try {

			ArrayList lista = (ArrayList) FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext()
					.getSessionMap().get("elementos");

			if (lista != null) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < lista.size(); i++) {
					sb.append(lista.get(i).toString() + " ");
				}
				data.setElementos(sb.toString());

			}

			ArrayList<Dato> listaid = (ArrayList) FacesContext.getCurrentInstance().getCurrentInstance()
					.getExternalContext().getSessionMap().get("listaid");

			for (int j = 0; j < listaid.size(); j++) {
				if (listaid.get(j).getNombre().equals(this.getData().getNombre())) {
					throw new Exception("Error: NOMBRE registrado.");

				}

			}

			if (this.getData().getNombre().equals("")) {

				throw new Exception("Error en el NOMBRE");
			}

			if (this.getData().getTipo().length() < 6 && this.getData().getTipo().length() > 8) {

				throw new Exception("Error en TIPO de dato");
			}

			if (this.getData().getTipo().equals("Complejo")) {

				if (!this.getData().getSubtipo().equals("complex")) {
					throw new Exception("Error en SUBTIPO");

				}

			}

			if (!this.getData().getTipo().equals("Complejo")) {

				if (this.getData().getRequerido().length() != 2) {
					throw new Exception("Error en campo REQUERIDO");
				}

			}

			if (this.getData().getTipo().equals("Simple")) {

				if (this.getData().getSubtipo().equals("complex")) {
					throw new Exception("Error en captura de tipo SIMPLE");
				}

			}

			if (!this.getData().getEsquema().equals("corporativo")) {
				throw new Exception("Error en campo ESQUEMA");
			}

			// verificacion de igualdad
			// obtenemos lista de valores: listavalidacion

			FacesMessage message = null;

			ArrayList<Dato> listaval = (ArrayList) FacesContext.getCurrentInstance().getCurrentInstance()
					.getExternalContext().getSessionMap().get("listavalidacion");

			if (this.getData().getTipo().equals("Simple")) {
				String logidsimple = this.getData().getTipo() + this.getData().getSubtipo()
						+ this.getData().getLongmin() + this.getData().getLongmax() + this.getData().getEsquema();

				// obtenemos lista de valores: listavalidacion

				for (int j = 0; j < listaval.size(); j++) {
					if (setvacioNull(listaval.get(j).getLogicid()).contains(
							(this.getData().getTipo() + this.getData().getSubtipo() + this.getData().getLongmin()
									+ this.getData().getLongmax() + this.getData().getEsquema()))) {

						message = new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Elemento simple con similitud a otro registrado ", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						break;

					}

				}

				this.getData().setLogicid(this.getData().getTipo() + this.getData().getSubtipo()
						+ this.getData().getLongmin() + this.getData().getLongmax() + this.getData().getEsquema());
			}

			if (this.getData().getTipo().equals("Complejo")) {

				String logidcorpo = this.getData().getTipo() + this.getData().getElementos()
						+ this.getData().getEsquema();

				for (int j = 0; j < listaval.size(); j++) {
					if (setvacioNull(listaval.get(j).getLogicid()).contains(
							(this.getData().getTipo() + this.getData().getElementos() + this.getData().getEsquema()))) {
						message = new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Elemento complejo con similitud a otro registrado ", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						break;

					}

				}

				this.getData().setLogicid(
						this.getData().getTipo() + this.getData().getElementos() + this.getData().getEsquema());
			}

			// fin verificacion de igualdad

			dataService.addDato(data);
			datos = dataService.listDato();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dato añadido correctamente"));
			logger.info("Dato " + this.data + " Añadido Exitosamente");

			this.data.setNombre("");
			this.data.setTipo("");
			this.data.setSubtipo("");
			this.data.setPatron("");
			this.data.setRequerido("");
			this.data.setDocumentacion("");
			this.data.setEsquema("");

		} catch (Exception e) {
			logger.error(e.getMessage());

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			if (e.getMessage().contains("xception")) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR EN LA CAPTURA", null);
			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public String addDatoNegocio() {

		try {

			ArrayList lista = (ArrayList) FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext()
					.getSessionMap().get("elementosNegocio");
			if (lista != null) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < lista.size(); i++) {
					sb.append(lista.get(i).toString() + " ");
				}
				data.setElementos(sb.toString());
			}

			ArrayList<Dato> listaid = (ArrayList) FacesContext.getCurrentInstance().getCurrentInstance()
					.getExternalContext().getSessionMap().get("listaid");
			for (int j = 0; j < listaid.size(); j++) {
				if (listaid.get(j).getNombre().equals(this.getData().getNombre())) {
					throw new Exception("Error: NOMBRE registrado.");

				}

			}
			if (this.getData().getNombre().equals("")) {

				throw new Exception("Error en el NOMBRE");
			}

			if (this.getData().getTipo().length() < 6 && this.getData().getTipo().length() > 8) {

				throw new Exception("Error en TIPO de dato");
			}
			if (this.getData().getTipo().equals("Complejo")) {

				if (!this.getData().getSubtipo().equals("complex")) {
					throw new Exception("Error en SUBTIPO");

				}

			}
			if (!this.getData().getTipo().equals("Complejo")) {

				if (this.getData().getRequerido().length() != 2) {
					throw new Exception("Error en campo REQUERIDO");
				}

			}

			if (this.getData().getTipo().equals("Simple")) {

				if (this.getData().getSubtipo().equals("complex")) {
					throw new Exception("Error en captura de tipo SIMPLE");
				}

			}

			if (!this.getData().getEsquema().equals("negocio")) {
				throw new Exception("Error en campo ESQUEMA");
			}

			// verificacion de igualdad
			// obtenemos lista de valores: listavalidacion

			FacesMessage message = null;

			ArrayList<Dato> listaval = (ArrayList) FacesContext.getCurrentInstance().getCurrentInstance()
					.getExternalContext().getSessionMap().get("listavalidacion");

			if (this.getData().getTipo().equals("Simple")) {
				String logidsimple = this.getData().getTipo() + this.getData().getSubtipo()
						+ this.getData().getLongmin() + this.getData().getLongmax() + this.getData().getEsquema();
				// obtenemos lista de valores: listavalidacion

				for (int j = 0; j < listaval.size() - 1; j++) {

					if (setvacioNull(listaval.get(j).getLogicid()).contains((setvacioNull(this.getData().getTipo())
							+ setvacioNull(this.getData().getSubtipo()) + setvacioNull(this.getData().getEsquema()))))
					

					{
						message = new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Elemento simple con similitud a otro registrado ", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						break;

					}

				}

				this.getData().setLogicid(this.getData().getTipo() + this.getData().getSubtipo()
						+ this.getData().getLongmin() + this.getData().getLongmax() + this.getData().getEsquema());
			}

			if (this.getData().getTipo().equals("Complejo")) {

				String logidcorpo = this.getData().getTipo() + this.getData().getElementos()
						+ this.getData().getEsquema();

				for (int j = 0; j < listaval.size(); j++) {
					if (setvacioNull(listaval.get(j).getLogicid()).contains((setvacioNull(this.getData().getTipo())
							+ setvacioNull(this.getData().getElementos()) + setvacioNull(this.getData().getEsquema()))))
					// if(listaval.get(j).getLogicid().contains("ERIC"));

					{
						message = new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Elemento complejo con similitud a otro registrado ", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						break;

					}

				}

				this.getData().setLogicid(
						this.getData().getTipo() + this.getData().getElementos() + this.getData().getEsquema());
			}
			// fin verificacion de igualdad

			dataService.addDato(data);
			datos = dataService.listDato();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dato añadido correctamente"));
			logger.info("Dato " + this.data + " Añadido Exitosamente");

			this.data.setNombre("");
			this.data.setTipo("");
			this.data.setSubtipo("");
			this.data.setPatron("");
			this.data.setRequerido("");
			this.data.setDocumentacion("");
			this.data.setEsquema("");

		} catch (Exception e) {
			logger.error(e.getMessage());

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
			if (e.getMessage().contains("xception")) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR EN LA CAPTURA", null);
			}

			FacesContext.getCurrentInstance().addMessage(null, message);

		}
		return "";
	}

	public String setvacioNull(String valor) {
		if (null == valor) {
			return "";
		}

		return valor.toString();

	}

	public String deleteDato(Dato data) {
		try {
			dataService.deleteDato(data);
			datos = dataService.listDato();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("El Dato " + this.data.getDescription() + " se ha eliminado correctamente"));
			logger.info("El dato " + this.data + " Is Deleted Successfully");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "";
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			logger.info("El Dato " + this.data + " se edito correctamente");
			Dato c = (Dato) event.getObject();
			dataService.updateDato(c);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public String logout() {
		try {
			logger.info("Log out");
			return "Login";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}