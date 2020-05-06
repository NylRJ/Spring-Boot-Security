package com.i9Developed.i9.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i9Developed.i9.db.UserRepository;
import com.i9Developed.i9.model.User;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PublicRestApiController {
    private UserRepository userRepository;

    public PublicRestApiController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public String test1(){return "API Test ";}

    @GetMapping("/manager/reports")
    public String reports(){
        return "Some report data";
    }

    @GetMapping("/admin/users")
    public List<User> users(){
        return this.userRepository.findAll();
    }

}
