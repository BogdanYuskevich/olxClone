package com.buyit.olxclone.repos;

import com.buyit.olxclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
