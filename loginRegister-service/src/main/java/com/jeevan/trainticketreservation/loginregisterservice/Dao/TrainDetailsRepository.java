package com.jeevan.trainticketreservation.loginregisterservice.Dao;

import com.jeevan.trainticketreservation.loginregisterservice.Entity.TrainDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface TrainDetailsRepository extends JpaRepository<TrainDetails,Integer> {

    public TrainDetails findTrainDetailsByTrainNumber(String trainNumber);

    @Query(value = "select * from train_details",nativeQuery = true)
    public Set<TrainDetails> getAllTrains();

}
