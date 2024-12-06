package com.adp.employeeservice.utils;


import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;



public class CentralExceptionHandler extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 3303254948974359578L;

	public CentralExceptionHandler(String message) {
		super(message);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setStatus(HttpStatus.BAD_REQUEST.value());

        String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(", "));

        errorInfo.setError(errorMsg);
        errorInfo.setDate(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}


	 @ExceptionHandler(EmployeeException.class)
	    public ResponseEntity<ErrorInfo> exceptionHandler(EmployeeException ex) {
	        ErrorInfo errorInfo = new ErrorInfo();
	        errorInfo.setStatus(HttpStatus.BAD_REQUEST.value());
	        String errorMsg = ex.getMessage();
	        errorInfo.setError(errorMsg);
	        errorInfo.setDate(LocalDateTime.now());
	        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);


}

}
