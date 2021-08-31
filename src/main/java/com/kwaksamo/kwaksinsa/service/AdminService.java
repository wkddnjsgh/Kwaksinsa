package com.kwaksamo.kwaksinsa.service;

import com.kwaksamo.kwaksinsa.controller.dto.AddProductReqDto;
import com.kwaksamo.kwaksinsa.controller.dto.UpdateProductReqDto;
import com.kwaksamo.kwaksinsa.model.Product;
import com.kwaksamo.kwaksinsa.model.Stock;
import com.kwaksamo.kwaksinsa.model.User;
import com.kwaksamo.kwaksinsa.repository.AdminRepository;
import com.kwaksamo.kwaksinsa.repository.ProductRepository;
import com.kwaksamo.kwaksinsa.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;
	private final ProductRepository productRepository;
	private final StockRepository stockRepository;
	
	// YML에서 설정한 값을 가져옴
	@Value("${file.path}")
	private String uploadFolder;
	
	public List<User> 모든유저조회(){
		return adminRepository.findAll();
	}

	@Transactional
	public void 상품등록(AddProductReqDto addProductReqDto) {

		UUID uuid = UUID.randomUUID();

		String imageFileName = uuid + "" + addProductReqDto.getFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		try {
			Files.write(imageFilePath, addProductReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Product product = Product.builder()
				.name(addProductReqDto.getName())
				.imgUrl(imageFileName)
				.disc(addProductReqDto.getDisc())
				.price(addProductReqDto.getPrice())
				.build();
		productRepository.productSave(product);
		Stock stock =Stock.builder()
				.productId(product.getId())
				.count(0)
				.build();
		stockRepository.save(stock);
	}

	public Product 상품수정가기(int id) {
		Product product = productRepository.findById(id);
		return product;
	}

	@Transactional
	public void 상품수정(UpdateProductReqDto updateProductReqDto) {
		UUID uuid = UUID.randomUUID();

		String imageFileName = uuid + "" + updateProductReqDto.getFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		try {
			Files.write(imageFilePath, updateProductReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Product product = Product.builder()
				.id(updateProductReqDto.getId())
				.name(updateProductReqDto.getName())
				.imgUrl(imageFileName)
				.disc(updateProductReqDto.getDisc())
				.price(updateProductReqDto.getPrice())
				.build();
		productRepository.update(product);
	}

	@Transactional
	public void 상품삭제(int id) {
		productRepository.deleteById(id);
	}
}
