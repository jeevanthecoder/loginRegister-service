package com.jeevan.trainticketreservation.loginregisterservice.service;
import com.jeevan.trainticketreservation.loginregisterservice.Dao.UserRepository;
import com.jeevan.trainticketreservation.loginregisterservice.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    public Users createCustomer(Users user){
        user.setUserPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
