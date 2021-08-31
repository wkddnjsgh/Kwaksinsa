package com.kwaksamo.kwaksinsa.controller.dto;

import lombok.Data;

import java.util.List;

// 아임포트로 주문이 완료되었을때 받아온 값들을 서버에 요청
@Data
public class OrdersReqDto {
	private String impId;
	private String merchantId;
	private String applyNum;
	private int totalPay;
	private List<String> idList;
}
