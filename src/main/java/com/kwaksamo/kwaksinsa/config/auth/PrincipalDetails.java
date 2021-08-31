package com.kwaksamo.kwaksinsa.config.auth;

import com.kwaksamo.kwaksinsa.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*
시큐리티가 로그인 요청이 오면 낚아채서 로그인을 진행
로그인 진행이 완료가 되면 session 생성 (Security ContextHolder)
Authentication 타입의 객체만 들어갈 수 있음
*/

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private User user;
	
	private Map<String,Object> attributes;
	
	// 일반 로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	
	// OAuth 로그인
	public PrincipalDetails(User user,Map<String,Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	
	
	public User getUser() {
		return user;
	}


	// 해당 유저의 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return (String) attributes.get("sub");
	}

}
