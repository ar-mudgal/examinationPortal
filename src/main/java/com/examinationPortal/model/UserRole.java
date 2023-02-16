package com.examinationPortal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "userRole")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRole {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @ManyToOne
    private Role role;
}
