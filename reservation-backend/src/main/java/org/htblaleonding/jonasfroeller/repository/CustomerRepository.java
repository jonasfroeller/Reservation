package org.htblaleonding.jonasfroeller.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.htblaleonding.jonasfroeller.model.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public void update(Customer customer) {
        getEntityManager().merge(customer);
    }
}
