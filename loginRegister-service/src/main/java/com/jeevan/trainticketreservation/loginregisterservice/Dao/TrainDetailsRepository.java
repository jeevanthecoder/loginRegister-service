package com.jeevan.trainticketreservation.loginregisterservice.Dao;

import com.jeevan.trainticketreservation.loginregisterservice.Entity.TrainDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainDetailsRepository extends JpaRepository<TrainDetails,Integer> {

    public TrainDetails findTrainDetailsByTrainNumber(String trainNumber);
}
