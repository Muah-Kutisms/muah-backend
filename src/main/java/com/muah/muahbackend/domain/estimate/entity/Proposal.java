package com.muah.muahbackend.domain.estimate.entity;

import com.muah.muahbackend.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="proposals")
public class Proposal {

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

    @Column(name="proposal_context", nullable = false, columnDefinition = "TEXT")
    private String context;

    // TODO : Add update functions

    @Builder
    public Proposal(Sheet sheet, User writer, Integer price, String context){
        this.sheet = sheet;
        this.writer = writer;
        this.price = price;
        this.context = context;
    }


}
