package com.jeevan.trainticketreservation.loginregisterservice.service;

import com.jeevan.trainticketreservation.loginregisterservice.Dao.ReservationRepository;
import com.jeevan.trainticketreservation.loginregisterservice.Dao.TrainDetailsRepository;
import com.jeevan.trainticketreservation.loginregisterservice.Dao.UserRepository;
import com.jeevan.trainticketreservation.loginregisterservice.Entity.ClassType;
import com.jeevan.trainticketreservation.loginregisterservice.Entity.Reservation;
import com.jeevan.trainticketreservation.loginregisterservice.Entity.TrainDetails;
import com.jeevan.trainticketreservation.loginregisterservice.Entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainDetailsRepository trainDetailsRepository;


    public Long PNRGeneration(){
        Long PNR=0l;
            Random random = new Random();
            PNR = random.nextLong(1, 99999999);

        return PNR;
    }

    @Transactional
    public String DeleteReservationByPNR(Long PNR,String userName){

        Users user = this.userRepository.findUsersByUserName(userName);
        Reservation reservation = this.reservationRepository.findReservationByPnrNumber(PNR);
        if(reservation==null){
            return "Reservation details not found";
        }else if(user==null){
            return "User not found";
        }else{
            Reservation reservation1 = this.reservationRepository.findReservationByPnrNumber(PNR);
            TrainDetails trainDetails = this.trainDetailsRepository.findTrainDetailsByTrainNumber(reservation1.getTrainNumber());
            Set<ClassType> classTypeSet = trainDetails.getClassType();
            for(ClassType classType: classTypeSet){
                if(classType.getClasstype().equals(reservation1.getClasstype())){
                    classType.setNumberOfSeats(classType.getNumberOfSeats()+ reservation.getNumberOfSeats());
                }
            }
            user.getReservation().remove(reservation1);
            this.reservationRepository.deleteReservationByPnrNumber(PNR);
            return "Reservation with PNR : "+PNR+" delete successfully!!!";
        }
    }
}
