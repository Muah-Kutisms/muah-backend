package com.muah.muahbackend.domain.community.entity;

import com.muah.muahbackend.domain.store.entity.ProductImage;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
public class Post extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    // TODO : Constraint by user role(USER)
    private User writer;

    @Column(name="post_category")
    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @Column(name="post_title")
    private String title;

    @Column(name= "post_content")
    @Lob
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(User writer, PostCategory category, String title, String content){
        this.writer = writer;
        this.category = category;
        this.title = title;
        this.content = content;
    }

}
