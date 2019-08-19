package com.projuris.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PadraoInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	public static final String INVALID_MATRIX = "Invalid Matrix Format";
	
	private String errMsg;
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public PadraoInvalidoException(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
	
}
