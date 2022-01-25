package com.mendonca.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javassist.tools.rmi.ObjectNotFoundException;


@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

	
		
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity erroBadRequest() {	
	    System.err.println("Exeption criada");
		return ResponseEntity.badRequest().build();
	}
	
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class,
		ObjectNotFoundException.class
	})
	public ResponseEntity errorNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return  new  ResponseEntity<>("operacao nao permitida",HttpStatus.METHOD_NOT_ALLOWED );
	}
	
	
	
}
