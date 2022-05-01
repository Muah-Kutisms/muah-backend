package com.muah.muahbackend.domain.faq.entity;

import com.muah.muahbackend.global.entity.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="faqs")
public class Faq extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id")
    private Long id;

    @Column(name = "faq_title")
    private String title;

    @Column(name = "faq_content")
    @Lob
    private String content;

    @Builder
    public Faq(String title, String content){
        this.title = title;
        this.content = content;
    }
}
