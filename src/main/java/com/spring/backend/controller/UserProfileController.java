package com.spring.backend.controller;


import com.spring.backend.models.UserProfile;
import com.spring.backend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api/user/")
public class UserProfileController {


    private UserProfileRepository userProfileRepository;


    @Autowired
    public void userProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }



    @RequestMapping(method = RequestMethod.POST)
    public UserProfile createUser(@RequestBody UserProfile userProfile){

        System.out.println("Here is the data info: "+userProfile.toString());

        return userProfileRepository.save(userProfile);

    }



    @RequestMapping(method = RequestMethod.GET)
    public List<UserProfile> getAllUsers(){


        var userList =  userProfileRepository.findAll();


        System.out.println("Here is the getAllUsers called: "+userList.toString() );

        return userList;
    }
}
