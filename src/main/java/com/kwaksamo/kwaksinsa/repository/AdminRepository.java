package com.kwaksamo.kwaksinsa.repository;

import com.kwaksamo.kwaksinsa.model.User;

import java.util.List;

public interface AdminRepository {
	List<User> findAll();
}
