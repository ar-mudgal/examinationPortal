package com.examinationPortal.service;

import com.examinationPortal.config.Response;
import com.examinationPortal.dto.UserDto;
import com.examinationPortal.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface UserService {

        //create user
        Response createUser(UserDto userDto);

        //find user by id
        Response getUserById(@PathVariable Long userId);

        //fetch all users
        List<UserDto> fetchAll(UserDto userDto);

        Response deleteUser(Long userId);

        Response updateUser(UserDto userDto);

        Response loginUser(UserDto userDto) throws Exception;
}

