package com.spring.backend.controller;


import com.spring.backend.models.UserProfile;
import com.spring.backend.repository.UserProfileRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return userProfileRepository.save(userProfile);
    }



    @RequestMapping(method = RequestMethod.GET)
    public List<UserProfile> getAllUsers(){
        return userProfileRepository.findAll();
    }


    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public UserProfile getUserById(@PathVariable String id){

        return  userProfileRepository.findById(id).get();

    }



    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public UserProfile updateUser(@RequestBody UserProfile userProfile, @PathVariable String id){

        var userAvailable = userProfileRepository.findById(id);

        if (userAvailable.isPresent()){
            var profile = userAvailable.get();
            profile.setName(userProfile.getName());
            profile.setType(userProfile.getType());
            profile.setBalance(userProfile.getBalance());
            profile.setDescription(userProfile.getDescription());

            return userProfileRepository.save(profile);
        }

        return userProfile;
    }


    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id){

        var userAvailable = userProfileRepository.findById(id);

        userAvailable.ifPresent(
                userProfile -> {
                   userProfileRepository.delete(userProfile);
                }
        );


    }






}
