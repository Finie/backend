package com.spring.backend.controller;


import com.spring.backend.dto.NetworkResponseDTO;
import com.spring.backend.models.UserProfile;
import com.spring.backend.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping(path = "/api/user/")
public class UserProfileController {


    private UserProfileService userProfileService;


    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public NetworkResponseDTO<UserProfile> createUser(@Valid @RequestBody UserProfile userProfile) {
        return userProfileService.createUser(userProfile);
    }


    @RequestMapping(method = RequestMethod.GET)
    public NetworkResponseDTO<List<UserProfile>> getAllUsers() {
        return userProfileService.fetchAllUsers();
    }


    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public NetworkResponseDTO<UserProfile> getUserById(@PathVariable String id) {
        return userProfileService.findUserById(id);
    }


    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public NetworkResponseDTO<UserProfile> updateUser(@RequestBody UserProfile userProfile, @PathVariable String id) {
        return userProfileService.updateUserById(userProfile, id);
    }


    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
      userProfileService.deleteUserById(id);
    }


}
