package com.kwaksamo.kwaksinsa.repository;

import com.kwaksamo.kwaksinsa.controller.respdto.UpdateRespDto;
import com.kwaksamo.kwaksinsa.model.User;

import java.util.Optional;

public interface UserRepository {
	User findByProviderAndProviderId(String provider, String providerId);
	Optional<User> findByUsername(String username);
	void save(User user);
	UpdateRespDto updateForm(int userId);
	void update(User user);
}
