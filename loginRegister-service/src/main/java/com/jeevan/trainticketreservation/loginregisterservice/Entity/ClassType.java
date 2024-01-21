package com.jeevan.trainticketreservation.loginregisterservice.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClassType {

    @Id
    @GeneratedValue
    int cId;

    @Column(unique = true)
    String classtype;

    public Long numberOfSeats;

    public int costPerSeat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    public TrainDetails trainDetails;

    public int getCostPerSeat() {
        return costPerSeat;
    }

    public ClassType setCostPerSeat(int costPerSeat) {
        this.costPerSeat = costPerSeat;
        return this;
    }

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public ClassType setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    public String getClasstype() {
        return classtype;
    }

    public ClassType setClasstype(String classtype) {
        this.classtype = classtype;
        return this;
    }

    public int getcId() {
        return cId;
    }

    public ClassType setcId(int cId) {
        this.cId = cId;
        return this;
    }

    public TrainDetails getTrainDetails() {
        return trainDetails;
    }

    public ClassType setTrainDetails(TrainDetails trainDetails) {
        this.trainDetails = trainDetails;
        return this;
    }
}
