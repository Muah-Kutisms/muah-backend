package com.muah.muahbackend.domain.estimate.entity;

import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="proposals")
public class Proposal extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sheet_id")
    private Sheet sheet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    // TODO : Constraint by user role(company)
    private User writer;

    @Column(name="proposal_price", nullable = false)
    private Integer price;

    @Column(name="proposal_content", nullable = false, columnDefinition = "TEXT")
    private String content;

    // TODO : Add update functions

    @Builder
    public Proposal(Sheet sheet, User writer, Integer price, String content){
        this.sheet = sheet;
        this.writer = writer;
        this.price = price;
        this.content = content;
    }


}
