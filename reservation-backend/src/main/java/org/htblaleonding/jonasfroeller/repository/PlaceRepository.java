package org.htblaleonding.jonasfroeller.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.htblaleonding.jonasfroeller.model.Place;

@ApplicationScoped
public class PlaceRepository implements PanacheRepository<Place> {
    public void update(Place place) {
        getEntityManager().merge(place);
    }
}
