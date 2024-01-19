package com.jeevan.trainticketreservation.loginregisterservice.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

import java.sql.Time;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TrainDetails {

    @Id
    @GeneratedValue
    public int t_Id;

    @Column(unique = true)
    public String trainNumber;

    @Column(unique = true)
    public String trainName;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    public Set<ClassType> classType;

    public String source;
    public String dest;

    public Date dateOfJourney;

    public Time time;


    public TrainDetails(String trainNumber, String trainName, Set<ClassType> classType, String source, String dest, Date dateOfJourney) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.source = source;
        this.dest = dest;
        this.dateOfJourney = dateOfJourney;
    }
}
