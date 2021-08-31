package com.kwaksamo.kwaksinsa.controller.dto;

import lombok.Data;

// 장바구니의 담긴 물품의 총액 계산
@Data
public class BasketSumDto {
	private int count;
	private int price;
}
