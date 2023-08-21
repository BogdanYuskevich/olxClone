package com.buyit.olxclone.services;

import com.buyit.olxclone.enums.Role;
import com.buyit.olxclone.models.User;
import com.buyit.olxclone.repos.UserRepos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepos userRepos;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepos.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.getRoleSet().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with email: {}", email);
        userRepos.save(user);
        return true;
    }

}
