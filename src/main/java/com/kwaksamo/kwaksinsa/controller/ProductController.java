package com.kwaksamo.kwaksinsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kwaksamo.kwaksinsa.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	//상품 목록
	@GetMapping("/product")
	public String productList(Model model) {
		model.addAttribute("products", productService.모든상품());
		return "/product/productList";
	}
}
