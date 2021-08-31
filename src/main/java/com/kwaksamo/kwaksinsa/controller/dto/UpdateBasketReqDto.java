package com.kwaksamo.kwaksinsa.controller.dto;

import lombok.Data;

// 장바구니 수량을 바꿔 서버에 요청
@Data
public class UpdateBasketReqDto {
	private int id;
	private int count;
}
