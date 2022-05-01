package com.muah.muahbackend.domain.user.entity;

import com.muah.muahbackend.domain.instructor.entity.Instructor;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.global.entity.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    @Column(name = "user_nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "user_phone", nullable = false)
    private String phone;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "City", column = @Column(name = "user_city", nullable = false)),
            @AttributeOverride(name = "County", column = @Column(name = "user_county")),
            @AttributeOverride(name = "District", column = @Column(name = "user_district", nullable = false)),
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

    @OneToOne(mappedBy = "instructor")
    private Instructor instructor;


    @Builder
    public User(String email, String nickname, String phone, UserRole role, Address address){
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.role = role;
        this.address = address;
    }


}
