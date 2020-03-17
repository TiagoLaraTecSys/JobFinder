package com.laratecsys.jobfinder.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	private String tentativa;
	
	public FieldMessage() {
		
	}

	public FieldMessage(String fieldName, String message, String tentativa) {
		super();
		this.fieldName = fieldName;
		this.message = message;
		this.tentativa = tentativa;
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

	public String getTentativa() {
		return tentativa;
	}

	public void setTentativa(String tentativa) {
		this.tentativa = tentativa;
	}
	
	

}
