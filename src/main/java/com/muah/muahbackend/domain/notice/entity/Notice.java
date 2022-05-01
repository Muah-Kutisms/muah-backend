package com.muah.muahbackend.domain.notice.entity;

import com.muah.muahbackend.global.entity.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="notices")
public class Notice extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "notice_title")
    private String title;

    @Column(name = "notice_content")
    @Lob
    private String content;

    @Builder
    public Notice(String title, String content){
        this.title = title;
        this.content = content;
    }
}
