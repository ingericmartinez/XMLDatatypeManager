package app.validators.dd;

import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Custom JSF Validator for Year input, allows only regex in EXPR_PATTERN pattern.
 */
@FacesValidator("nombreValidator")
public class NombreValidator implements Validator, ClientValidator {
 
	private static final Logger logger = LoggerFactory.getLogger(NombreValidator.class);
	 
    public NombreValidator() {
   
        logger.info("Inicializado");
    }
 
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
        	logger.info("No se proporciono un valor");
            return;
        }
      
        if(value.toString().equals("")) {
        	logger.info("Nombre no valido");
        	context = FacesContext.getCurrentInstance();
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre Incorrecto", 
                        value + " no es un nombre valido"));
        }
        
        if(!value.toString().contains("ype")) {
        	logger.info("Nombre no valido");
        	context = FacesContext.getCurrentInstance();
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre debe incluir posijo Type", 
                        value + " no es un nombre valido"));
        }
    }
 
    public Map<String, Object> getMetadata() {
        return null;
    }
 
    public String getValidatorId() {
        return "custom.exprValidator";
    }
     
}