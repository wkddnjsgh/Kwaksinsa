package com.kwaksamo.kwaksinsa.repository;

import com.kwaksamo.kwaksinsa.controller.respdto.ProductRespDto;
import com.kwaksamo.kwaksinsa.model.Product;

import java.util.List;

public interface ProductRepository {
	List<Product> findAll();
	List<ProductRespDto> findAllJoin();
	
	// 상품등록
	void productSave(Product product);
	// 수정시 정보를 들고감
	Product findById(int id);
	// 상품수정
	void update(Product product);
	// 상품삭제
	void deleteById(int id);
}
