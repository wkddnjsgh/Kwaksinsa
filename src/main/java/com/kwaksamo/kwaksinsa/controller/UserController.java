package com.kwaksamo.kwaksinsa.controller;

import com.kwaksamo.kwaksinsa.config.auth.LoginUserAnnotation;
import com.kwaksamo.kwaksinsa.config.auth.dto.LoginUser;
import com.kwaksamo.kwaksinsa.controller.dto.UserJoinReqDto;
import com.kwaksamo.kwaksinsa.controller.dto.UserUpdateReqDto;
import com.kwaksamo.kwaksinsa.controller.respdto.CommonRespDto;
import com.kwaksamo.kwaksinsa.model.User;
import com.kwaksamo.kwaksinsa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	
	private final AuthenticationManager authenticationManager;
	private final UserService userService;

	
	@Value("${kdh.key}")
	private String kdhKey;

	//로그인하는곳
	@GetMapping("/auth/login")
	public String login() {
		System.out.println("login");
		return "/auth/login";
	}
	
	//회원가입하는곳
	@GetMapping("/auth/join")
	public String join() {
		System.out.println("join");
		return "/auth/join";
	}
	
	//회원가입 (수정필요)
	@PostMapping("/auth/join")
	public CommonRespDto<?> joinProc(UserJoinReqDto reqUser) {
		
		User userEntity = User.builder()
				.username(reqUser.getUsername())
				.email(reqUser.getEmail())
				.password(reqUser.getPassword())
				.name("이름")
				.address("입력필요")
				.phone("입력필요")
				.gender("성별")
				.role("ROLE_USER")
				.provider("일반")
				.build();
		
		userService.회원가입(userEntity);
		return new CommonRespDto<String>(1, "회원가입성공");
	}
	@GetMapping("/user/update")
	public String update(@LoginUserAnnotation LoginUser loginUser,Model model) {
		model.addAttribute("user", userService.회원수정이동(loginUser.getId()));
		return "/user/update";
	}
	
	@PutMapping("/user/update")
	public CommonRespDto<?> updateProc(@LoginUserAnnotation LoginUser loginUser,UserUpdateReqDto dto){
		if(loginUser.getId()!=dto.getId()) {
			return null;
		}
		userService.회원수정(dto);
		return new CommonRespDto<String>(1, "회원수정성공");
	}
}
