package com.examinationPortal.controller;

import com.examinationPortal.config.Response;
import com.examinationPortal.dto.RoleDto;
import com.examinationPortal.dto.UserDto;
import com.examinationPortal.model.Role;
import com.examinationPortal.model.User;
import com.examinationPortal.model.UserRole;
import com.examinationPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Response createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping("id/{userId}")
    public Response getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping
    public Response fetchAll(UserDto userDto){
        return new Response("Data fetch successfully",userService.fetchAll(userDto), HttpStatus.OK);
    }
}
