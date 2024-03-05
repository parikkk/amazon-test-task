package com.amazon.testtask.service.impl;

import com.amazon.testtask.domain.entity.User;
import com.amazon.testtask.repository.UserRepository;
import com.amazon.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional getByUserId(Long userId) {
        return repository.findById(userId);
    }

    /**
     * Create new User
     *
     * @param user
     * @return User
     */
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with that username already exist");
        }
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with that email already exist");
        }

        return save(user);
    }

    /**
     * Get user by username
     *
     * @param username
     * @return User
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    /**
     * Get current user
     *
     * @return User
     */
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    /**
     * Get UserDetailsService
     *
     * @return UserDetailsService
     */
    public UserDetailsService getUserDetailsService() {
        return this::getByUsername;
    }

    /**
     * Save user to DB
     *
     * @param user
     * @return User
     */
    private User save(User user) {
        return repository.save(user);
    }
}
