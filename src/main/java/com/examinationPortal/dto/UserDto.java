package com.examinationPortal.dto;

import com.examinationPortal.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long userId;

    private String username;

    private String firstname;

    private String lastname;

    private String password;

    private String email;

    private String phoneno;

    private boolean enabled = true;

    private String profile;

    private Role role;
}
