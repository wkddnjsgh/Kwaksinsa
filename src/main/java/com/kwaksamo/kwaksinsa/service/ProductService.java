package com.kwaksamo.kwaksinsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwaksamo.kwaksinsa.controller.respdto.ProductRespDto;
import com.kwaksamo.kwaksinsa.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public List<ProductRespDto> 모든상품() {
		return productRepository.findAllJoin();
	}
}
