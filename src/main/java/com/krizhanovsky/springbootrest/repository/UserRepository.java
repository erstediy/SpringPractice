package com.krizhanovsky.springbootrest.repository;

import com.krizhanovsky.springbootrest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

     User findUserById(int id);

     boolean deleteUserById(int id);

     boolean existsByEmail(String email);

     boolean existsById(int id);

}

