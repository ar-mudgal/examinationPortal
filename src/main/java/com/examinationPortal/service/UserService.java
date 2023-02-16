package com.examinationPortal.service;

import com.examinationPortal.config.Response;
import com.examinationPortal.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {

        //create user
        Response createUser(UserDto userDto);

        //find user by id
        Response getUserById(@PathVariable Long userId);

        //fetch all users
        List<UserDto> fetchAll(UserDto userDto);
}

