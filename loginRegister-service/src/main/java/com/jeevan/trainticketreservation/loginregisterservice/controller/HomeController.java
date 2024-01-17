package com.jeevan.trainticketreservation.loginregisterservice.controller;

import com.jeevan.trainticketreservation.loginregisterservice.Dao.*;
import com.jeevan.trainticketreservation.loginregisterservice.Entity.*;
import com.jeevan.trainticketreservation.loginregisterservice.service.ReservationService;
import com.jeevan.trainticketreservation.loginregisterservice.service.UserService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrainDetailsRepository trainDetailsRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;
//    @GetMapping("/users")
//    public List<Users> getUser(){
//        System.out.println("Getting users.");
//        return userService.getUsers();
//    }

    @PostMapping(value = "/reserve-ticket/{trainNumber}")
    public ResponseEntity<Reservation> reserveTheTicket(Principal principal,@PathVariable("trainNumber")String trainNumber, @RequestBody ClassType classType){
       if(trainNumber==null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }else{
           boolean flag=false;
           TrainDetails trainDetails=this.trainDetailsRepository.findTrainDetailsByTrainNumber(trainNumber);
           Users user = this.userRepository.findUsersByUserName(principal.getName());
           if(trainDetails==null){
               return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
           }
           ClassType classType1 = classType;
           String cType=classType1.getClasstype();
           Long numberOfSeats = classType1.getNumberOfSeats();

           Set<ClassType> classTypeSet = trainDetails.getClassType();
           System.out.println(flag);
           for(ClassType classType2: classTypeSet){
               if(classType2.getClasstype().equals(cType) && numberOfSeats<=classType2.getNumberOfSeats()){
                   System.out.println(classType2.getClasstype()+" "+classType2.getNumberOfSeats());
                   flag=true;
                   break;
               }

           }
           if(flag==true){
               Reservation reservation = new Reservation();
                reservation.setPnrNumber(reservationService.PNRGeneration());
                reservation.setTrainName(trainDetails.getTrainName());
                reservation.setTrainNumber(trainNumber);
                reservation.setClasstype(cType);
                reservation.setNumberOfSeats(numberOfSeats);
                reservation.setSource(trainDetails.getSource());
                reservation.setDest(trainDetails.getDest());
                reservation.setDateOfJourney(trainDetails.getDateOfJourney());
                if(user.getReservation()==null){
                    Set<Reservation> reservationSet = new TreeSet<>();
                    reservationSet.add(reservation);
                    user.setReservation(reservationSet);
                }else
                user.getReservation().add(reservation);
                return ResponseEntity.ok(this.reservationRepository.save(reservation));

           }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @GetMapping("/delete-reservation/{PNR}")
public ResponseEntity<Void> deleteReservationbypnr(@PathVariable("PNR")Long PNR,Principal principal){

        if(PNR==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            String message=this.reservationService.DeleteReservationByPNR(PNR, principal.getName());
            System.out.println(message);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
}

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){

        return principal.getName();
    }


}
