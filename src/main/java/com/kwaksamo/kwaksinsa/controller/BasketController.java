package com.kwaksamo.kwaksinsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kwaksamo.kwaksinsa.config.auth.LoginUserAnnotation;
import com.kwaksamo.kwaksinsa.config.auth.dto.LoginUser;
import com.kwaksamo.kwaksinsa.controller.dto.AddBasketReqDto;
import com.kwaksamo.kwaksinsa.controller.dto.BasketListReqDto;
import com.kwaksamo.kwaksinsa.controller.dto.UpdateBasketReqDto;
import com.kwaksamo.kwaksinsa.controller.respdto.CommonRespDto;
import com.kwaksamo.kwaksinsa.service.BasketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BasketController {
	
	private final BasketService basketService;
	
	//장바구니 조회하기
	@GetMapping("/basket/list/")
	public String viewBasket(@LoginUserAnnotation LoginUser loginUser, Model model) {
		model.addAttribute("baskets", basketService.장바구니조회(loginUser.getId()));
		return "basket/basketList";
	}
	
	//장바구니 등록하기
	@PostMapping("/basket")
	public @ResponseBody CommonRespDto<?> addBasket(AddBasketReqDto addBasketReqDto) {
		System.out.println("product : "+addBasketReqDto);
		basketService.장바구니추가(addBasketReqDto);
		return new CommonRespDto<String>(1,"성공");
	}
	
	//장바구니 수정하기
	@PutMapping("/basket")
	public @ResponseBody CommonRespDto<?> updateBasket(UpdateBasketReqDto basketReqDto) {
		if(basketReqDto.getCount()>10) {
			return new CommonRespDto<String>(2,"실패");
		}
		basketService.장바구니수정(basketReqDto);
		return new CommonRespDto<String>(1,"성공");
	}
	
	//장바구니 삭제하기
	@DeleteMapping("/basket")
	public @ResponseBody CommonRespDto<?> deleteBasket(int id) {
		basketService.장바구니삭제(id);
		return new CommonRespDto<String>(1,"성공");
	}
	
	//장바구니들 삭제하기
	@DeleteMapping("/basket/list")
	public @ResponseBody CommonRespDto<?> deleteBasketList(@RequestBody BasketListReqDto dto) {
		basketService.장바구니목록삭제(dto);
		return new CommonRespDto<String>(1,"성공");
	}
}
