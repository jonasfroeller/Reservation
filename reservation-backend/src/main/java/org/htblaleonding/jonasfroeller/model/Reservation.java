package org.htblaleonding.jonasfroeller.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import org.htblaleonding.jonasfroeller.validator.TimeDifference;
import org.htblaleonding.jonasfroeller.validator.TimeRange;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@TimeDifference
@TimeRange
public class Reservation extends PanacheEntity {
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Place place;

    @Future
    @Column(name = "reservation_start")
    @JsonProperty("reservation_start")
    private LocalDateTime reservationStart;
    @Future
    @Column(name = "reservation_end")
    @JsonProperty("reservation_end")
    private LocalDateTime reservationEnd;

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

    public @Future LocalDateTime getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(@Future LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }

    public @Future LocalDateTime getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(@Future LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }
}
