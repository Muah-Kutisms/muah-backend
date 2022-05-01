package com.muah.muahbackend.domain.store.entity;

import com.muah.muahbackend.domain.estimate.entity.Funeral;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "order_products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_products_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="order_products_count")
    private Integer count ;

    public OrderProduct(Order order, Product product, Integer count){
        this.product = product;
        this.order = order;
        this.count = count;
    }
}
