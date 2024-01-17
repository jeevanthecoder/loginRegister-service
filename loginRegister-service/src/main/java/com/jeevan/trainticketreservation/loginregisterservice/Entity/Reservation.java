package com.jeevan.trainticketreservation.loginregisterservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue
    public int rId;

    @Column(unique = true)
    public Long pnrNumber;


    public String trainNumber;

    public String trainName;


    public String source;
    public String dest;

    public Date dateOfJourney;

    String classtype;

    public Long numberOfSeats;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    public Users users;
}
