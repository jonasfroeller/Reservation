package org.htblaleonding.jonasfroeller.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.htblaleonding.jonasfroeller.model.PlaceType;

@ApplicationScoped
public class PlaceTypeRepository implements PanacheRepository<PlaceType> {
    @Transactional
    public void update(PlaceType placeType) {
        getEntityManager().merge(placeType);
    }
}
