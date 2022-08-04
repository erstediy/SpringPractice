package com.krizhanovsky.springbootrest.controller;

import com.krizhanovsky.springbootrest.Entity.User;
import com.krizhanovsky.springbootrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest-api")
public class MyRestController {

    private final UserService userService;

    public MyRestController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("say-hello")
    public String sayHello(@RequestParam(required = false,defaultValue = "World!") String name){
        return "Hello, " + name;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("users/{id}")
    public boolean deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User user){
        return userService.updateUser(id,user);
    }

}
