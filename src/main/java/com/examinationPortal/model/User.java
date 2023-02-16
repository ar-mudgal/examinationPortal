package com.examinationPortal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "profile")
    private String profile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> userRoles = new HashSet<>();
}
