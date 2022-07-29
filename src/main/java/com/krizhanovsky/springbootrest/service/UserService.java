package com.krizhanovsky.springbootrest.service;

import com.krizhanovsky.springbootrest.Entity.User;
import com.krizhanovsky.springbootrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(@Autowired UserRepository repository){
        this.repository=repository;
    }

    public User getUserById(int id){
        return repository.findUserById(id);
    }

    public boolean deleteUser(int id){
        return repository.deleteUserById(id);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User updateUser(int id,User newUser){
        User oldUser = repository.findUserById(id);
        oldUser.setEmail(newUser.getEmail());
        oldUser.setName(newUser.getName());
        return oldUser;
    }

}
