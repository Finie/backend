package com.spring.backend.repository;

import com.spring.backend.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {


    //add custom queries



}
