package com.kwaksamo.kwaksinsa.repository;

import com.kwaksamo.kwaksinsa.controller.respdto.BasketRespDto;
import com.kwaksamo.kwaksinsa.model.Basket;

import java.util.List;

public interface BasketRepository {
	// 장바구니 id로 하나만 검색
	Basket findById(int id);
	
	// update시 해당 유저가 해당 상품을 들고있는지 검사하기 위함
	Basket findyByUserIdAndProductId(int userId, int productId);
	
	// 유저의 장바구니 페이지로 이동하고 장바구니에 담긴 상품을 보여줌
	List<BasketRespDto> findAllJoin(int userId);
	
	// 장바구니 저장
	void save(Basket basket);
	
	// 장바구니 수정
	void update(Basket basket);
	
	// 장바구니 삭제
	void deleteById(int id);
}
