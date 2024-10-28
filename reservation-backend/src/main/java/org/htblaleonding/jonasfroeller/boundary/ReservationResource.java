package org.htblaleonding.jonasfroeller.boundary;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.htblaleonding.jonasfroeller.model.Reservation;
import org.htblaleonding.jonasfroeller.repository.ReservationRepository;

import java.net.URI;
import java.util.List;

@Path("/reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    @Inject
    ReservationRepository reservationRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok(String.format("%s is alive!", ReservationResource.class.getSimpleName())).build();
    }

    @GET
    @Path("/")
    public List<Reservation> getReservationList() {
        return reservationRepository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Reservation getReservationById(@PathParam("id") Long id) {
        return reservationRepository.findById(id);
    }

    @POST
    @Transactional
    public Response createReservation(Reservation reservation) {
        reservationRepository.persist(reservation);

        URI UriContext = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(reservation.id))
                .build();

        return Response.created(UriContext).entity(reservation).build();
    }

    @PUT
    @Transactional
    public Response replaceReservation(Reservation reservation) {
        reservationRepository.update(reservation);
        return Response.ok().entity(reservation).build();
    }

    @PATCH
    @Transactional
    public Response updateReservation(Reservation reservation) {
        reservationRepository.update(reservation);
        return Response.ok().entity(reservation).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteReservation(@PathParam("id") Long id) {
        reservationRepository.deleteById(id);
        return Response.noContent().build();
    }
}