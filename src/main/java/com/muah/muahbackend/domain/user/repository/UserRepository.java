package com.muah.muahbackend.domain.user.repository;

import com.muah.muahbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
