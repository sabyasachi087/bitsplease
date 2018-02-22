package com.psl.semicolon.bitsplease.exception;

public class BitsPleaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6040625801202342940L;
	
	public BitsPleaseException(Exception ex){
		super(ex);
	}

}
