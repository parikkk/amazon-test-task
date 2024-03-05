package com.amazon.testtask.controller;

import com.amazon.testtask.domain.entity.Role;
import com.amazon.testtask.domain.entity.User;
import com.amazon.testtask.repository.UserRepository;
import com.amazon.testtask.service.FileService;
import com.amazon.testtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get User by Id
     *
     * @param id user Id
     * @return User
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        log.info("Get user by Id");
        Optional optional = userService.getByUserId(id);
        if(optional.isPresent()) {
            optional.get();
        }
        return null;
    }
}
