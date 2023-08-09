package com.example.demo.service;

import com.example.demo.constants.DomConstants;
import com.example.demo.model.Address;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ProfileService {
    @Autowired
    UserRepository userRepository;

    public User updateUserProfile(User user, Profile profile){
        user.setName(profile.getName());
        user.setEmail(profile.getEmail());
        user.setMobileNumber(profile.getMobileNumber());

        if (user.getAddress() == null || !(user.getAddress().getAddressId() > 0)) {
            user.setAddress(new Address());
        }
        user.getAddress().setAddress1(profile.getAddress1());
        user.getAddress().setAddress2(profile.getAddress2());
        user.getAddress().setCity(profile.getCity());
        user.getAddress().setState(profile.getState());
        user.getAddress().setZipCode(profile.getZipCode());
        user.getAddress().setCreatedAt(LocalDateTime.now());
        user.getAddress().setCreatedBy(DomConstants.CLIENT_ROLE);

        return userRepository.save(user);
    }

    public User updateAdminProfile(User user, Profile profile){
        user.setName(profile.getName());
        user.setEmail(profile.getEmail());
        user.setMobileNumber(profile.getMobileNumber());

        if (user.getAddress() == null || !(user.getAddress().getAddressId() > 0)) {
            user.setAddress(new Address());
        }
        user.getAddress().setAddress1(profile.getAddress1());
        user.getAddress().setAddress2(profile.getAddress2());
        user.getAddress().setCity(profile.getCity());
        user.getAddress().setState(profile.getState());
        user.getAddress().setZipCode(profile.getZipCode());
        user.getAddress().setCreatedAt(LocalDateTime.now());
        user.getAddress().setCreatedBy(DomConstants.ADMIN_ROLE);

        return userRepository.save(user);
    }

    public User getUserProfileDetails(String email){
        return userRepository.readByEmail(email);
    }

    public Profile getUserProfileForEdition(String email){
        User user = userRepository.readByEmail(email);
        Profile profile = new Profile();
        profile.setName(user.getName());
        profile.setMobileNumber(user.getMobileNumber());
        profile.setEmail(user.getEmail());

        if(user.getAddress() != null && user.getAddress().getAddressId()>0){
            profile.setAddress1(user.getAddress().getAddress1());
            profile.setAddress2(user.getAddress().getAddress2());
            profile.setCity(user.getAddress().getCity());
            profile.setState(user.getAddress().getState());
            profile.setZipCode(user.getAddress().getZipCode());
        }

        return profile;
    }

    public Profile getUserProfileDorDisplay(String email){
        User user = userRepository.readByEmail(email);
        Profile profile = new Profile();
        profile.setName(user.getName());
        profile.setMobileNumber(user.getMobileNumber());
        profile.setEmail(user.getEmail());

        if(user.getAddress() != null && user.getAddress().getAddressId()>0){
            profile.setAddress1(user.getAddress().getAddress1());
            profile.setAddress2(user.getAddress().getAddress2());
            profile.setCity(user.getAddress().getCity());
            profile.setState(user.getAddress().getState());
            profile.setZipCode(user.getAddress().getZipCode());
        }

        return profile;
    }
}
