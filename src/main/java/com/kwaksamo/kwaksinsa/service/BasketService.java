package com.kwaksamo.kwaksinsa.service;

import com.kwaksamo.kwaksinsa.controller.dto.AddBasketReqDto;
import com.kwaksamo.kwaksinsa.controller.dto.BasketListReqDto;
import com.kwaksamo.kwaksinsa.controller.dto.UpdateBasketReqDto;
import com.kwaksamo.kwaksinsa.controller.respdto.BasketRespDto;
import com.kwaksamo.kwaksinsa.model.Basket;
import com.kwaksamo.kwaksinsa.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {
	
	private final BasketRepository basketRepository;
	
	@Transactional
	public void 장바구니목록삭제(BasketListReqDto dto) {
		for (String id : dto.getIdList()) {
			basketRepository.deleteById(Integer.parseInt(id));
		}
	}
	
	public void 장바구니삭제(int id) {
		basketRepository.deleteById(id);
	}
	
	public List<BasketRespDto> 장바구니조회(int userId) {
		return basketRepository.findAllJoin(userId);
	}
	
	@Transactional
	public void 장바구니수정(UpdateBasketReqDto updateBasketReqDto) {
		Basket basketEntity = basketRepository.findById(updateBasketReqDto.getId());
		basketEntity.setCount(updateBasketReqDto.getCount());
		basketRepository.update(basketEntity);
	}
	
	@Transactional
	public void 장바구니추가(AddBasketReqDto addBasketReqDto) {
		
		Basket basketEntity = basketRepository.findyByUserIdAndProductId(addBasketReqDto.getUserId(),addBasketReqDto.getProductId());
		System.out.println(basketEntity);
		
		if(addBasketReqDto.getCount()>10) addBasketReqDto.setCount(10);
		
		if(basketEntity==null) {
			System.out.println("값이 없으므로 NULL 실행");
			basketEntity = Basket.builder()
					.userId(addBasketReqDto.getUserId())
					.productId(addBasketReqDto.getProductId())
					.count(addBasketReqDto.getCount())
					.build();
			basketRepository.save(basketEntity);
		}else if(basketEntity.getCount()<10){
			System.out.println("값이 있으므로 else if 실행");
			int sum = basketEntity.getCount()+addBasketReqDto.getCount();
			if(sum>10) sum=10;
			basketEntity.setCount(sum);
			basketRepository.update(basketEntity);
		}
	}
}
