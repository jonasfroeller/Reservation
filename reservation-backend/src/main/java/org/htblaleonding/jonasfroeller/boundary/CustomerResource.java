package org.htblaleonding.jonasfroeller.boundary;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.htblaleonding.jonasfroeller.model.Customer;
import org.htblaleonding.jonasfroeller.repository.CustomerRepository;

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
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok(String.format("%s is alive!", CustomerResource.class.getSimpleName())).build();
    }

    @GET
    @Path("/")
    public List<Customer> getCustomerList() {
        return customerRepository.findAll().list();
    }

    @GET
    @Path("/{id}")
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
    @Transactional
    public Response updateCustomer(Customer customer) {
        customerRepository.update(customer);
        return Response.ok().entity(customer).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerRepository.deleteById(id);
        return Response.noContent().build();
    }
}
