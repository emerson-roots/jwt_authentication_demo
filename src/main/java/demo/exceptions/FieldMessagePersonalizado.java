package demo.exceptions;

import java.io.Serializable;

/*
 * responsavel por personalizar as mensagens de erro
 * de validacao dos campos de dominio
 * */
public class FieldMessagePersonalizado implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;

	public FieldMessagePersonalizado() {

	}

	public FieldMessagePersonalizado(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
