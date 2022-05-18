package com.muah.muahbackend.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.muah.muahbackend.domain.community.entity.Comment;
import com.muah.muahbackend.domain.community.entity.Post;
import com.muah.muahbackend.domain.instructor.entity.Instructor;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.store.entity.Order;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.store.entity.Review;
import com.muah.muahbackend.global.entity.Base;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name="users")
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name = "user_email", nullable = false, unique = true)
    @Email
    private String email;

    @Column
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_phone")
    private String phone;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "City", column = @Column(name = "user_city")),
            @AttributeOverride(name = "County", column = @Column(name = "user_county")),
            @AttributeOverride(name = "District", column = @Column(name = "user_district")),
    })
    private Address address;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name="user_code")
    private String code;

    @Column(name="user_brn")
    private String brn;

    @Column(name="user_is_approved", columnDefinition = "boolean default false")
    private Boolean isApproved;

    @Column(name="user_is_new", columnDefinition = "boolean default true")
    private Boolean isNew;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "buyer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Review> reviews = new ArrayList<>();


    @OneToOne(mappedBy = "instructor")
    private Instructor instructor;

    @Column(name="refresh_token")
    private String refreshToken;



    @Builder
    public User(String email, String password){
        Address address = Address.builder()
                .City("")
                .District("")
                .County("")
                .build();

        this.email = email;
        this.password = password;
        this.isApproved = false;
        this.isNew = true;
        this.role = UserRole.ROLE_USER;
        this.address = address;

    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateAddress(String city, String district, String country) {
        Address address = Address.builder()
                .City(city)
                .District(district)
                .County(country)
                .build();
        this.address = address;
    }

}
