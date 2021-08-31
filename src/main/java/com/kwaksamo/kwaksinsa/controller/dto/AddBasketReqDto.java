package com.kwaksamo.kwaksinsa.controller.dto;

import lombok.Data;

//장바구니 추가, 서버에 요청할 때 사용
@Data
public class AddBasketReqDto {
	private int userId;
	private int productId;
	private int count;
}
