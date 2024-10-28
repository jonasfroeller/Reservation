package org.htblaleonding.jonasfroeller.boundary;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.htblaleonding.jonasfroeller.model.Customer;
import org.htblaleonding.jonasfroeller.repository.CustomerRepository;
import org.htblaleonding.jonasfroeller.validator.PasswordValidator;

import java.net.URI;
import java.util.List;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok(String.format("%s is alive!", CustomerResource.class.getSimpleName())).build();
    }

    @GET
    @Path("")
    public List<Customer> getCustomerList() {
        return customerRepository.listAll(Sort.by("email"));
    }

    @GET
    @Path("{id}")
    public Customer getCustomerById(@PathParam("id") Long id) {
        return customerRepository.findById(id);
    }

    @POST
    @Transactional
    public Response createCustomer(Customer customer) {
        customerRepository.persist(customer);

        URI UriContext = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(customer.id))
                .build();

        return Response.created(UriContext).entity(customer).build();
    }

    @PUT
    @Transactional
    public Response replaceCustomer(Customer customer) {
        customerRepository.update(customer);
        return Response.ok().entity(customer).build();
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id);

        if (existingCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }

        if (updatedCustomer.getFirstName() != null) {
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
        }
        if (updatedCustomer.getLastName() != null) {
            existingCustomer.setLastName(updatedCustomer.getLastName());
        }
        if (updatedCustomer.getPassword() != null) {
            if (!new PasswordValidator().isValid(updatedCustomer.getPassword(), null)) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid password: must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character!")
                        .build();
            }

            existingCustomer.setPassword(updatedCustomer.getPassword());
        }

        customerRepository.update(existingCustomer);

        return Response.ok().entity(existingCustomer).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerRepository.deleteById(id);
        return Response.noContent().build();
    }
}
