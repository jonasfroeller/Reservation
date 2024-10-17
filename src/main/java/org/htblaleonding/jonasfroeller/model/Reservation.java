package org.htblaleonding.jonasfroeller.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import org.htblaleonding.jonasfroeller.validator.TimeDifference;
import org.htblaleonding.jonasfroeller.validator.TimeRange;

import java.sql.Date;

@Entity
@TimeDifference
@TimeRange
public class Reservation {

    @Id
    private Long id;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Place place;

    @Future
    @Column(name = "reservation_start")
    private Date reservationStart;
    @Future
    @Column(name = "reservation_end")
    private Date reservationEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public @Future Date getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(@Future Date reservationStart) {
        this.reservationStart = reservationStart;
    }

    public @Future Date getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(@Future Date reservationEnd) {
        this.reservationEnd = reservationEnd;
    }
}
