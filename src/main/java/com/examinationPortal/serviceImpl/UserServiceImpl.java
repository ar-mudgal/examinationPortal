package com.examinationPortal.serviceImpl;

import com.examinationPortal.config.Response;
import com.examinationPortal.dto.UserDto;
import com.examinationPortal.model.Role;
import com.examinationPortal.model.User;
import com.examinationPortal.model.UserRole;
import com.examinationPortal.repository.RoleRepository;
import com.examinationPortal.repository.UserRepository;
import com.examinationPortal.repository.UserRoleRepository;
import com.examinationPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Response createUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUserName(userDto.getUserName());
        if(userOptional.isPresent()){
            return new Response("user is already exist", HttpStatus.BAD_REQUEST);
        }
        User user =new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setProfile(userDto.getProfile());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        Set<UserRole>userRoleSet=new HashSet<>();

        // set role
        Role role  = new Role();
        role.setRoleName(userDto.getRole().getRoleName());

        //set userRole
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoleSet.add(userRole);

        for(UserRole ur : userRoleSet){
            roleRepository.save(ur.getRole());
        }
        user.getUserRoles().addAll(userRoleSet);
        User users = this.userRepository.save(user);
        return new Response("user created successfully",users, HttpStatus.OK);
    }

    @Override
    public Response getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            return new Response("user not found for this id: ", userId, HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        UserDto dt = new UserDto();
        dt.setLastName(user.getLastName());
        dt.setUserId(user.getUserId());
        dt.setUserName(user.getUserName());
        dt.setProfile(user.getProfile());
        dt.setEmail(user.getEmail());
        dt.setFirstName(user.getFirstName());
        dt.setPhoneNo(user.getPhoneNo());
        return new Response("user get user data successfully",dt,HttpStatus.OK);
    }

    @Override
    public List<UserDto> fetchAll(UserDto userDto) {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User  usr: users){
            UserDto dto =  new UserDto();
            dto.setLastName(usr.getLastName());
            dto.setUserId(usr.getUserId());
            dto.setUserName(usr.getUserName());
            dto.setProfile(usr.getProfile());
            dto.setEmail(usr.getEmail());
            dto.setFirstName(usr.getFirstName());
            dto.setPhoneNo(usr.getPhoneNo());
            userDtos.add(dto);
        }
        return userDtos;
    }


}
