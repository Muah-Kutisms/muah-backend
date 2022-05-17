package com.muah.muahbackend.domain.store.entity;

import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class Product extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    // TODO : Constraint by user role(SELLER)
    private User seller;


    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private Integer price;

    @Column(name="product_option", columnDefinition = "TEXT")
    private String option;

    @Column(name="product_description")
    private String description;

    @Column(name = "product_is_advertised")
    private Boolean isAdvertised;

    @OneToMany(mappedBy = "product")
    private List<ProductImage>images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Product(User seller, String name, Integer price, String option, String description){
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.option = option;
        this.description = description;
        this.isAdvertised = false;
    }
}
