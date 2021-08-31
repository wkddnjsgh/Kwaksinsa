package com.kwaksamo.kwaksinsa.repository;

import com.kwaksamo.kwaksinsa.controller.dto.BasketSumDto;
import com.kwaksamo.kwaksinsa.controller.respdto.OrdersDetailDto;
import com.kwaksamo.kwaksinsa.model.Orders;
import com.kwaksamo.kwaksinsa.model.Orders_detail;

import java.util.List;

public interface OrdersRepository {
	// 관리자가 특정 유저의 결제내역 조회
	List<Orders> findAllByUserIdJoin(int userId);
	//관리자가 모든 유저의 결제내역 조회
	List<Orders> findAllJoin();
	

	Orders findByOrdersId(int ordersId);
	
	//해당 유저의 모든 주문내역 조회
	List<Orders> findByUserId(int userId);
	
	List<OrdersDetailDto> findByUserIdOrdersIdJoin(int userId, int ordersId);
	
	// 장바구니에서 선택된 상품들의 가격 더하기
	BasketSumDto sum(int userId, int basketId);
	
	// Orders 저장
	void saveOrders(Orders orders);
	// Orders_detail 저장
	void saveDetails(Orders_detail detail);
}
