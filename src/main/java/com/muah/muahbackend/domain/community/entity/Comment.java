package com.muah.muahbackend.domain.community.entity;

import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class
Comment extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    // TODO : Constraint by user role(USER)
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_content", columnDefinition = "TEXT")
    private String content;

    @Builder
    public Comment(User writer, Post post, String content){
        this.writer = writer;
        this.post = post;
        this.content = content;
    }
}
