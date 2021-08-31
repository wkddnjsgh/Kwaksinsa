package com.kwaksamo.kwaksinsa.config.auth;

import com.kwaksamo.kwaksinsa.config.auth.dto.LoginUser;
import com.kwaksamo.kwaksinsa.model.User;
import com.kwaksamo.kwaksinsa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	private final HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername 실행됨");
		User userEntity = userRepository.findByUsername(username).get();
		if(userEntity != null) {
			System.out.println("loadUserByUsername - if문");
			//아이디가 존재하면 세션 등록
			session.setAttribute("loginUser", new LoginUser(userEntity));
		}
		// 없으면 새로 만듬
		System.out.println("loadUserByUsername - return 전");
		return new PrincipalDetails(userEntity);
	}
}
