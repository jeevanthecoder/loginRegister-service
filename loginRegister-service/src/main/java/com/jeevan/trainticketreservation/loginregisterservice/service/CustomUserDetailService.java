package com.jeevan.trainticketreservation.loginregisterservice.service;

import com.jeevan.trainticketreservation.loginregisterservice.Dao.UserRepository;
import com.jeevan.trainticketreservation.loginregisterservice.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //load user from database
        Users user = userRepository.findUsersByUserName(username);

        if(user==null) {
            throw new RuntimeException("Customer not found!!!");
        }
        return user;
    }


}
