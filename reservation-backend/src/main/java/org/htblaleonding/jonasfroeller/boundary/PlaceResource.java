package org.htblaleonding.jonasfroeller.boundary;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.htblaleonding.jonasfroeller.model.Place;
import org.htblaleonding.jonasfroeller.repository.PlaceRepository;

import java.net.URI;
import java.util.List;

@Path("/places")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlaceResource {

    @Inject
    PlaceRepository placeRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok(String.format("%s is alive!", PlaceResource.class.getSimpleName())).build();
    }

    @GET
    @Path("/")
    public List<Place> getPlaceList() {
        return placeRepository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Place getPlaceById(@PathParam("id") Long id) {
        return placeRepository.findById(id);
    }

    @POST
    @Transactional
    public Response createPlace(Place place) {
        placeRepository.persist(place);

        URI UriContext = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(place.id))
                .build();

        return Response.created(UriContext).entity(place).build();
    }

    @PUT
    @Transactional
    public Response replacePlace(Place place) {
        placeRepository.update(place);
        return Response.ok().entity(place).build();
    }

    @PATCH
    @Transactional
    public Response updatePlace(Place place) {
        placeRepository.update(place);
        return Response.ok().entity(place).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePlace(@PathParam("id") Long id) {
        placeRepository.deleteById(id);
        return Response.noContent().build();
    }
}
