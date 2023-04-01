package gtcm.users.api.infra.exception.model;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorFieldValidation {
	String field;
	String message;
	
	public ErrorFieldValidation(FieldError error) {
		this(error.getField(), error.getDefaultMessage());
	}
}
