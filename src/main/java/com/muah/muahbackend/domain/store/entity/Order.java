package com.muah.muahbackend.domain.store.entity;

import com.muah.muahbackend.domain.estimate.entity.Sheet;
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
@Table(name="orders")
public class Order extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    // TODO : Constraint by user role(company)
    private User buyer;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name ="order_price")
    private Integer price;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> products = new ArrayList<>();

    @Builder
    public Order(User buyer, OrderStatus status){
        this.buyer = buyer;
        this.status = status;
        this.price = 0;
    }
}
