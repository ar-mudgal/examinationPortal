package com.examinationPortal.repository;

import com.examinationPortal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findByEmail(String email);

//    Optional<User> findByEmailAndPassword(String email, String password);

    User findByEmailAndPassword(String email, String password);

}
