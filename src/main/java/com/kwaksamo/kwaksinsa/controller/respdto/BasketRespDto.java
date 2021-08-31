package com.kwaksamo.kwaksinsa.controller.respdto;

import lombok.Data;

// 장바구니 보기
@Data
public class BasketRespDto {
	private int id;
	private String imgUrl;
	private String name;
	private int price;
	private int count;
}