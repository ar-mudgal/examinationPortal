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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public Response createUser(UserDto userDto) {
        User userOptional = userRepository.findByUsername(userDto.getUsername());
        if(userOptional!=null){
            log.info("user is alresdy exist -{}", userDto.getUsername());
            return new Response("user is already exist", HttpStatus.BAD_REQUEST);
        }
        User user =new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setProfile(userDto.getProfile());
        user.setPhoneno(userDto.getPhoneno());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        Set<UserRole>userRoleSet=new HashSet<>();

        // set role
        Role role  = new Role();
        role.setRoleName(userDto.getRole().getRoleName());
        log.info("");

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
        dt.setLastname(user.getLastname());
        dt.setUserId(user.getUserId());
        dt.setUsername(user.getUsername());
        dt.setProfile(user.getProfile());
        dt.setEmail(user.getEmail());
        dt.setFirstname(user.getFirstname());
        dt.setPhoneno(user.getPhoneno());
        return new Response("user get user data successfully",dt,HttpStatus.OK);
    }

    @Override
    public List<UserDto> fetchAll(UserDto userDto) {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User  usr: users){
            UserDto dto =  new UserDto();
            dto.setLastname(usr.getLastname());
            dto.setUserId(usr.getUserId());
            dto.setUsername(usr.getUsername());
            dto.setProfile(usr.getProfile());
            dto.setEmail(usr.getEmail());
            dto.setFirstname(usr.getFirstname());
            dto.setPhoneno(usr.getPhoneno());
            userDtos.add(dto);
        }
        return userDtos;
    }

    @Override
    public Response deleteUser(Long userId) {
        Optional<User>  userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            log.info("user not found for this id -{}",userId);
            return new Response("user not found for this id -{}",userId,HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(userId);
        log.info("user deleted successfully for userId -{}",userId);
        return new Response("user deleted successfully for userId -{}",userId,HttpStatus.OK);
    }

    @Override
    public Response updateUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getUserId());
        if(!userOptional.isPresent()){
            return  new Response("user not exixt for this id",userDto.getUserId(),HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        user.setLastname(userDto.getLastname());
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setProfile(userDto.getProfile());
        user.setFirstname(userDto.getFirstname());
        user.setPassword(userDto.getPassword());
        user.setPhoneno(userDto.getPhoneno());
        user.setEmail(userDto.getEmail());

        // set role
        Role role  = new Role();
        role.setRoleName(userDto.getRole().getRoleName());

        userRepository.save(user);
        return new Response("user get user data successfully",user,HttpStatus.OK);
    }


    //lOGIN USER
    @Override
    public Response loginUser(UserDto userDto) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        if(userOptional.isEmpty()){
            throw new Exception("USER CREDENTIALS NOT VALID !");
        }
        User user = userOptional.get();
        if(BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
            userDto.setFirstname(user.getFirstname());
            userDto.setLastname(user.getUsername());
            userDto.setPhoneno(user.getPhoneno());
            userDto.setUserId(user.getUserId());
            userDto.setEnabled(true);
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setProfile(user.getProfile());
            return new Response("LOGIN SUCCESSFULLY",userDto,HttpStatus.OK);
        }
        else {
            return new Response("Password incorrect", HttpStatus.BAD_REQUEST);
        }
    }
//    private String decryptPassword(UserDto  userDto){
//        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            String Pass = String.valueOf(encoder.matches(user.getPassword(),userDto.getPassword()));
//        }
//        return null;
//    }
}
