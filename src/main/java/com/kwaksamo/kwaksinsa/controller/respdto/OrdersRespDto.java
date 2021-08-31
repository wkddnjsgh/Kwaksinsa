package com.kwaksamo.kwaksinsa.controller.respdto;

import com.kwaksamo.kwaksinsa.config.auth.dto.LoginUser;
import com.kwaksamo.kwaksinsa.model.Orders;
import lombok.Builder;
import lombok.Data;

import java.util.List;

// 결제내역을 보여줄 때 날짜와 세부 정보들
@Data
@Builder
public class OrdersRespDto {
	private LoginUser user;
	private Orders orders;
	private List<OrdersDetailDto> details;
}
