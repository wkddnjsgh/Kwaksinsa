package com.kwaksamo.kwaksinsa.controller.respdto;

import lombok.Data;

import java.sql.Timestamp;

//관리자가 결제내역 확인
@Data
public class AdminOrdersRespDto {
	private int id; 
	private int userId;
	private String username; 
	private String impId;
	private String merchantId; 
	private String applyNum;
	private int totalPay; 
	private Timestamp createDate;
}
