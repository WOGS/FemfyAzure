package com.femfy.femfyapi.exception;

public class CustomException  extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5646764724137127617L;

	public CustomException(String error) {
        super(error);
    }
}

