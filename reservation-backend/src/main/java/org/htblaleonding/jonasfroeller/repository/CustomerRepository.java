package org.htblaleonding.jonasfroeller.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.htblaleonding.jonasfroeller.model.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    @Transactional
    public void update(Customer customer) {
        getEntityManager().merge(customer);
    }
}
