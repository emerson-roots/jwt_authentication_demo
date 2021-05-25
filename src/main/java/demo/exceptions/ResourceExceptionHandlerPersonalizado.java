package demo.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandlerPersonalizado {

	//padrao do da anotação @ControllerAdvice
	@ExceptionHandler(ObjectNotFoundExceptionPersonalizado.class)
	public ResponseEntity<StandardErrorPersonalizado> objectNotFoundEmerson(ObjectNotFoundExceptionPersonalizado pNotFound, HttpServletRequest pRequest){

		//instancia novo erro padrao (objeto nao encontrado) 
		StandardErrorPersonalizado err = new StandardErrorPersonalizado(HttpStatus.NOT_FOUND.value(), pNotFound.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
