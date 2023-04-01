package gtcm.users.api.infra.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gtcm.users.api.controller.UserController;
import gtcm.users.api.infra.exception.model.ErrorFieldValidation;
import gtcm.users.api.infra.exception.model.ErrorMessage;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class ExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity duplicateKey(DuplicateKeyException ex) {
		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new ErrorMessage("Email já cadastrado."));
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity notValidArgument(MethodArgumentNotValidException ex) {
		log.error(ex.getMessage());
	    return ResponseEntity.badRequest().body(ex.getFieldErrors().stream()
	    		.map(ErrorFieldValidation::new).toList());
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity notFound(NoSuchElementException ex) {
		log.error(ex.getMessage());
	    return ResponseEntity.notFound().build();
	}
}
