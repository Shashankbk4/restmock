package com.fmc.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private String fieldname;
	
	private String fieldtype;
	
	private Long fieldid;

	public ResourceNotFoundException(String fieldname, String fieldtype, Long fieldid) {
		super(String.format("%s not found with %s :'%s'",fieldname,fieldtype,fieldid ));
		this.fieldname = fieldname;
		this.fieldtype = fieldtype;
		this.fieldid = fieldid;
	}

	public String getFieldname() {
		return fieldname;
	}

	public String getFieldtype() {
		return fieldtype;
	}

	public Long getFieldid() {
		return fieldid;
	}
	
	
}
