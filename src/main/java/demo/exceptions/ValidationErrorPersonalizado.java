package demo.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorPersonalizado extends StandardErrorPersonalizado {
	private static final long serialVersionUID = 1L;

	private List<FieldMessagePersonalizado> errors = new ArrayList<>();

	public ValidationErrorPersonalizado(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessagePersonalizado> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessagePersonalizado(fieldName, message));
	}

}
