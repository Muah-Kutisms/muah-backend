package com.muah.muahbackend.domain.community.repository;

import com.muah.muahbackend.domain.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
