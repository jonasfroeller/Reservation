package org.htblaleonding.jonasfroeller.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.htblaleonding.jonasfroeller.model.Reservation;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<Reservation> {
    public void update(Reservation reservation) {
        getEntityManager().merge(reservation);
    }
}
