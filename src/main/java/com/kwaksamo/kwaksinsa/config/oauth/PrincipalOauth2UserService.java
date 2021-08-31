package com.kwaksamo.kwaksinsa.config.oauth;

import com.kwaksamo.kwaksinsa.config.auth.PrincipalDetails;
import com.kwaksamo.kwaksinsa.config.auth.dto.LoginUser;
import com.kwaksamo.kwaksinsa.config.oauth.provider.*;
import com.kwaksamo.kwaksinsa.model.User;
import com.kwaksamo.kwaksinsa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${kdh.key}")
	private String kdhKey;

	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// 어떤 OAuth로 로그인했는지 확인 
		System.out.println("userRequest :"+userRequest.getClientRegistration());
		System.out.println("getAccessToken :"+userRequest.getAccessToken());
		
		OAuth2User oauth2User = super.loadUser(userRequest);

		System.out.println("getAttributes :"+oauth2User.getAttributes());
		
		// 회원가입 강제 진행
		OAuth2UserInfo oAuth2UserInfo = null;
		
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("카카오 로그인 요청");
			oAuth2UserInfo = new KaKaoUserInfo((String)oauth2User.getAttributes().get("id").toString(), (Map)oauth2User.getAttributes().get("properties"));
		}else {
			System.out.println("구글, 페이스북, 네이버, 카카오만 지원합니다.");
		}
		
		
		String provider = oAuth2UserInfo.getProvider();
		String providerId = oAuth2UserInfo.getProviderId();
		String username = provider+"_"+providerId;
		String password = bCryptPasswordEncoder.encode(kdhKey);
		String email = oAuth2UserInfo.getEmail();
		String name = oAuth2UserInfo.getName();
		UUID uuid = UUID.randomUUID();
		if(email==null) email="이메일 없음_"+uuid;
		String role = "ROLE_USER";
		
		User userEntity = userRepository.findByProviderAndProviderId(provider, providerId);
		if(userEntity==null) {
			System.out.println("userEntity==null");
			userEntity = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId)
					.name(name)
					.address("입력필요")
					.phone("입력필요")
					.gender("성별")
					.build();
			userRepository.save(userEntity);
			userEntity = userRepository.findByProviderAndProviderId(provider, providerId);
		}

		session.setAttribute("loginUser", new LoginUser(userEntity));
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}
} 

