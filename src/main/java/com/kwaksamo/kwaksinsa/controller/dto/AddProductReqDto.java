package com.kwaksamo.kwaksinsa.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


// 상품 추가, 서버에 요청할 때 받음
@Data
public class AddProductReqDto {
	private MultipartFile file;
	private String name;
	private String disc;
	private int price;
}
