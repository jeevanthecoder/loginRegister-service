package com.jeevan.trainticketreservation.loginregisterservice.Dao;

import com.jeevan.trainticketreservation.loginregisterservice.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    public Reservation findReservationByPnrNumber(Long pnrNumber);

    public void deleteReservationByPnrNumber(Long pnr);
}
