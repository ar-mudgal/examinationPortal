package com.examinationPortal.controller;

import com.examinationPortal.config.Response;
import com.examinationPortal.dto.UserDto;
import com.examinationPortal.model.User;
import com.examinationPortal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Response createUser(@RequestBody UserDto userDto){
        log.info("create user method successfully");
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

    @DeleteMapping("/{userId}")
    public Response deleteUser(@PathVariable Long userId){
        log.info("delete user method called successfully for userId -{}",userId);
       return userService.deleteUser(userId);
    }

    @PostMapping("/logIn")
    public Response loginUser(@RequestBody UserDto userDto) throws Exception{
        return userService.loginUser(userDto);
    }
}
