package com.jeevan.trainticketreservation.loginregisterservice.Dao;

import com.jeevan.trainticketreservation.loginregisterservice.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {

    public Users findUsersByUserName(String userName);

}
