package com.krizhanovsky.springbootrest.service;

import com.krizhanovsky.springbootrest.Entity.User;
import com.krizhanovsky.springbootrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(@Autowired UserRepository repository){
        this.repository=repository;
    }

    public User getUserById(int id){
        return repository.findUserById(id);
    }

    public boolean deleteUser(int id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id=" + id + " not found");
        }
        return repository.deleteUserById(id);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User updateUser(int id,User newUser){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id=" + id + " not found");
        }
        User oldUser = repository.findUserById(id);
        oldUser.setEmail(newUser.getEmail());
        oldUser.setName(newUser.getName());
        return repository.save(oldUser);
    }

    public User addUser(User user){
        boolean emailExists = repository.existsByEmail(user.getEmail());
        if(emailExists) {throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "User with email: " + user.getEmail() + " already exists!");
        }
        return repository.save(user);
    }

}
