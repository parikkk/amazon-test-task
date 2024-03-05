package com.amazon.testtask.service;

import com.amazon.testtask.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    Optional getByUserId(Long userId);

    User create(User user);

    User getByUsername(String username);

    User getCurrentUser();

    UserDetailsService getUserDetailsService();

}