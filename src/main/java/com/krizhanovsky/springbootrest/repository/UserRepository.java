package com.krizhanovsky.springbootrest.repository;

import com.krizhanovsky.springbootrest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findUserById(int id);

    public boolean deleteUserById(int id);

}

