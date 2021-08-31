package com.kwaksamo.kwaksinsa.config.handler.exception;

public class MyUserIdNotFoundException extends RuntimeException{

	private String message;
	
	public MyUserIdNotFoundException() {
		this.message = "해당 유저의 id를 찾을 수 없습니다.";
	}
	
	public MyUserIdNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
