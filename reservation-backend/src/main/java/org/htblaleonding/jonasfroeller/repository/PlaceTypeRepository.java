package org.htblaleonding.jonasfroeller.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.htblaleonding.jonasfroeller.model.PlaceType;

@ApplicationScoped
public class PlaceTypeRepository implements PanacheRepository<PlaceType> {
    public void update(PlaceType placeType) {
        getEntityManager().merge(placeType);
    }
}
