package com.examinationPortal.dto;

import com.examinationPortal.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long userId;

    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phoneNo;

    private boolean enabled = true;

    private String profile;

    private Role role;
}
