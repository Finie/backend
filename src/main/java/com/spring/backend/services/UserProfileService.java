package com.spring.backend.services;


import com.spring.backend.dto.NetworkResponseDTO;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.models.UserProfile;
import com.spring.backend.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileService.class);

    private UserProfileRepository userProfileRepository;

    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }


    public NetworkResponseDTO<UserProfile> createUser(UserProfile userProfile) {

             var result = userProfileRepository.save(userProfile);
              NetworkResponseDTO<UserProfile> response = new NetworkResponseDTO<UserProfile>();
              response.setError(null);
              response.setMessage("User created successfully");
              response.setData(result);
              response.setStatus(HttpStatus.CREATED.value());
              return  response;

    }


    public NetworkResponseDTO<List<UserProfile>> fetchAllUsers() {

        var result = userProfileRepository.findAll();

        NetworkResponseDTO<List<UserProfile>> networkResponse = new NetworkResponseDTO<>();
        networkResponse.setMessage("Success");
        networkResponse.setData(result);
        networkResponse.setStatus(HttpStatus.OK.value());
        networkResponse.setError(null);

        return networkResponse;
    }


    public NetworkResponseDTO<UserProfile> findUserById(String id) {
        var result = userProfileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource with id: "+id+" not found!"));


        NetworkResponseDTO<UserProfile> profileResponse = new NetworkResponseDTO<>();
        profileResponse.setError(null);
        profileResponse.setStatus(HttpStatus.OK.value());
        profileResponse.setMessage("Success");
        profileResponse.setData(result);

        return profileResponse;

    }


    public NetworkResponseDTO<UserProfile> updateUserById(UserProfile userProfile, String id) {
        var userAvailable = userProfileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource with id: "+id+" not found!"));

        if (userAvailable != null) {
            userAvailable.setName(userProfile.getName());
            userAvailable.setType(userProfile.getType());
            userAvailable.setBalance(userProfile.getBalance());
            userAvailable.setDescription(userProfile.getDescription());
            var result = userProfileRepository.save(userAvailable);

            NetworkResponseDTO<UserProfile> profileResponse = new NetworkResponseDTO<>();
            profileResponse.setError(null);
            profileResponse.setStatus(HttpStatus.OK.value());
            profileResponse.setMessage("Success");
            profileResponse.setData(result);
            return profileResponse;

        }

        throw new RuntimeException("User update failed, try again later");
    }


    public void deleteUserById(String id) {

        var userAvailable = userProfileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource with id: "+id+" not found!"));

        if (userAvailable != null){
            userProfileRepository.delete(userAvailable);
        }

    }


}
