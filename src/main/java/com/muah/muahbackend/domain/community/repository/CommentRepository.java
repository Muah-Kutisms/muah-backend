package com.muah.muahbackend.domain.community.repository;

import com.muah.muahbackend.domain.community.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
