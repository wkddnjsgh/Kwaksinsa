package com.kwaksamo.kwaksinsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kwaksamo.kwaksinsa.controller.dto.AddProductReqDto;
import com.kwaksamo.kwaksinsa.controller.dto.UpdateProductReqDto;
import com.kwaksamo.kwaksinsa.controller.respdto.CommonRespDto;
import com.kwaksamo.kwaksinsa.service.AdminService;
import com.kwaksamo.kwaksinsa.service.OrdersService;
import com.kwaksamo.kwaksinsa.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;
	private final ProductService productService;
	private final OrdersService ordersService;
	
	// 주문 관리
	@GetMapping("/admin/orders")
	public String ordersList(Model model) {
		model.addAttribute("orders", ordersService.모든주문조회());
		return "/admin/orders/ordersList";
	}
	
	// 주문 관리에서 id 클릭
	@GetMapping("/admin/orders/detail/{id}/{userId}")
	public String ordersDetail(@PathVariable int id,@PathVariable int userId, Model model) {
		System.out.println(id+"///////"+userId);
		model.addAttribute("dto", ordersService.유저주문상세보기(id,userId));
		return "/admin/orders/ordersDetail";
	}
	
	// 주문 관리에서 username 클릭
	@GetMapping("/admin/orders/search/{userId}")
	public String ordersSearch(@PathVariable int userId,Model model) {
		model.addAttribute("orders", ordersService.유저주문조회(userId));
		return "/admin/orders/ordersList";
	}
	
	
	// 유저 관리
	@GetMapping("/admin/user")
	public String userList(Model model) {
		model.addAttribute("users",adminService.모든유저조회());
		return "/admin/user/userList";
	}
	
	// 상품 리스트 보기
	@GetMapping("/admin/product")
	public String productList(Model model) {
		model.addAttribute("products", productService.모든상품());
		return "/admin/product/productList";
	}
	// 상품등록 페이지 이동
	@GetMapping("/admin/productForm")
	public String addProductForm() {
		return "admin/product/productForm";
	}
	
	// 상품 등록
	@PostMapping("/admin/product")
	public String addProduct(AddProductReqDto product) {
		System.out.println("product : "+product);
		adminService.상품등록(product);
		return "redirect:/admin/product";
	}
	
	// 상품 수정 페이지 이동
	@GetMapping("/admin/productUpdateForm/{id}")
	public String updateProductForm(@PathVariable int id, Model model) {
		model.addAttribute("product", adminService.상품수정가기(id));
		return "admin/product/productEditForm";
	}
	
	// 상품 수정
	@PostMapping("/admin/productUpdate")
	public String updateProduct(UpdateProductReqDto product) {
		System.out.println("product : "+product);
		adminService.상품수정(product);
		return "redirect:/admin/product";
	}
	
	// 상품 삭제
	@DeleteMapping("/admin/product")
	public @ResponseBody CommonRespDto<?> deleteProduct(int id) {
		adminService.상품삭제(id);
		return new CommonRespDto<String>(1, "삭제완료");

	}
	
}
