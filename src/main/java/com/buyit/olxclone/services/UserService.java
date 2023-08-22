package com.buyit.olxclone.services;

import com.buyit.olxclone.enums.Role;
import com.buyit.olxclone.models.User;
import com.buyit.olxclone.repos.UserRepos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepos userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        String email = user.getEmail();


        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoleSet().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);

    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }



    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}