package com.kwaksamo.kwaksinsa.config.oauth.provider;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

	// getAttributes();
	private Map<String,Object> attributes;
	
	public NaverUserInfo(Map<String,Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getProviderId() {
		return (String) attributes.get("id");
	}

	@Override
	public String getProvider() {
		return "naver";
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	public String profile_image() {
		return (String) attributes.get("profile_image");
	}
}
