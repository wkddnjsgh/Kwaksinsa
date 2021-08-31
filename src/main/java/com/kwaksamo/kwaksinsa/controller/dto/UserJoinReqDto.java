package com.kwaksamo.kwaksinsa.controller.dto;

import lombok.Data;

//간단한 회원가입 -> 수정 필요
@Data
public class UserJoinReqDto {
	private String username;
	private String email;
	private String password;
}
