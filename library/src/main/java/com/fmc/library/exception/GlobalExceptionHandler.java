package com.fmc.library.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fmc.library.payload.CustomException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<CustomException> resourceNotFoundException(ResourceNotFoundException rex,WebRequest web){
		
		CustomException customException=new CustomException();
		
		customException.setDate(new Date());
		customException.setDetails(web.getDescription(false));
		customException.setMessage(rex.getMessage());
		
		return new ResponseEntity<CustomException>(customException,HttpStatus.NOT_FOUND) ;
		
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<CustomException> handleanyException(Exception ex,WebRequest web){
		
		CustomException customException=new CustomException();
		
		customException.setDate(new Date());
		customException.setDetails(web.getDescription(false));
		customException.setMessage(ex.getMessage());
		
		return new ResponseEntity<CustomException>(customException,HttpStatus.NOT_FOUND) ;
		
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String,String> notvalid=new HashMap();
		
		ex.getBindingResult().getAllErrors().forEach((errors) ->{
			
			String fieldvalue=((FieldError)errors).getField();
			String fieldmessage=errors.getDefaultMessage();
			
			notvalid.put(fieldvalue,fieldmessage);
		});
		return new ResponseEntity<Object>(notvalid,HttpStatus.BAD_REQUEST);
	}
	
	
}
