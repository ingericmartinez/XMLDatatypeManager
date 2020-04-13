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
@FacesValidator("expresionValidator")
public class ExpresionValidator implements Validator, ClientValidator {
 
	private static final Logger logger = LoggerFactory.getLogger(ExpresionValidator.class);
	 
	private Pattern pattern;
  
     //private static final String YEAR_PATTERN = "([1-9][0-9][0-9][0-9])";
	 //private static final String YEAR_PATTERN = "([a-zA-Z0-9]*)";
	//private static final String EXPRESION_PATTERN = "^((?:(?:[^?+*{}()[\\]\\\\|]+|\\\\.|\\[(?:\\^?\\\\.|\\^[^\\\\]|[^\\\\^])(?:[^\\]\\\\]+|\\\\.)*\\]|\\((?:\\?[:=!]|\\?<[=!]|\\?>)?(?1)??\\)|\\(\\?(?:R|[+-]?\\d+)\\))(?:(?:[?+*]|\\{\\d+(?:,\\d*)?\\})[?+]?)?|\\|)*)$";
   private static final String EXPRESION_PATTERN = ".*";
   
    public ExpresionValidator() {
        pattern = Pattern.compile(EXPRESION_PATTERN);
        logger.info("Inicializado");
    }
 
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
        	logger.info("No se proporciono un valor");
            return;
        }
         
        if(!pattern.matcher(value.toString()).matches()) {
        	logger.info("el valor no es una expresion regular valida");
        	context = FacesContext.getCurrentInstance();
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en patron", 
                        value + " no es un valor valido"));
        }
    }
 
    public Map<String, Object> getMetadata() {
        return null;
    }
 
    public String getValidatorId() {
        return "custom.exprValidator";
    }
     
}