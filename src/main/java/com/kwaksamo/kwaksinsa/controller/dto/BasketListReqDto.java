package com.kwaksamo.kwaksinsa.controller.dto;

import lombok.Data;

import java.util.List;

// 장바구니 목록을 서버에 보냄 -> id를 리스트로 받을 때 사용
@Data
public class BasketListReqDto {
	private List<String> idList;
}
