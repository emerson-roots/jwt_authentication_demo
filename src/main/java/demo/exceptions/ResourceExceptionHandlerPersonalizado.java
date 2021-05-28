package demo.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandlerPersonalizado {

	//padrao do da anotação @ControllerAdvice
	@ExceptionHandler(ObjectNotFoundExceptionPersonalizado.class)
	public ResponseEntity<StandardErrorPersonalizado> objectNotFoundPersonalizado(ObjectNotFoundExceptionPersonalizado pNotFound, HttpServletRequest pRequest){

		//instancia novo erro padrao (objeto nao encontrado) 
		StandardErrorPersonalizado err = new StandardErrorPersonalizado(HttpStatus.NOT_FOUND.value(), pNotFound.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityExceptionPersonalizado.class)
	public ResponseEntity<StandardErrorPersonalizado> dataIntegrityPersonalizado(DataIntegrityExceptionPersonalizado pNotFound,
			HttpServletRequest pRequest) {

		StandardErrorPersonalizado err = new StandardErrorPersonalizado(HttpStatus.BAD_REQUEST.value(), pNotFound.getMessage(),
				System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardErrorPersonalizado> validationPersonalizado(MethodArgumentNotValidException e,
			HttpServletRequest pRequest) {

		ValidationErrorPersonalizado err = new ValidationErrorPersonalizado(HttpStatus.BAD_REQUEST.value(), "Erro de validacao de campos",
				System.currentTimeMillis());

		//percorre cada erro adicionando ao metodo personalizado addError
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AuthorizationExceptionPersonalizado.class)
	public ResponseEntity<StandardErrorPersonalizado> authorizationPersonalizado(AuthorizationExceptionPersonalizado pNotFound,
			HttpServletRequest pRequest) {

		StandardErrorPersonalizado err = new StandardErrorPersonalizado(HttpStatus.FORBIDDEN.value(), pNotFound.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
