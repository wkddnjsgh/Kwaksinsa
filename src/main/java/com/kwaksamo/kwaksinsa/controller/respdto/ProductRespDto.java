package com.kwaksamo.kwaksinsa.controller.respdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

// 상품 페이지
@Getter
@Setter
@Builder
public class ProductRespDto {
	private int id;
	private String name;
	private String imgUrl;
	private String disc;
	private int price;
	private Timestamp createDate;
	private int stockId;
	private int productId;
	private int count;
}
