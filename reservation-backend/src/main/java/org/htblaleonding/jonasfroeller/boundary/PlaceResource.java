package org.htblaleonding.jonasfroeller.boundary;

import io.quarkus.panache.common.Sort;
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
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok(String.format("%s is alive!", PlaceResource.class.getSimpleName())).build();
    }

    @GET
    @Path("")
    public List<Place> getPlaceList() {
        return placeRepository.listAll(Sort.by("location"));
    }

    @GET
    @Path("{id}")
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
    @Path("{id}")
    @Transactional
    public Response updatePlace(@PathParam("id") Long id, Place updatedPlace) {
        Place existingPlace = placeRepository.findById(id);

        if (existingPlace == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Place not found").build();
        }

        if (updatedPlace.getPlaceType() != null) {
            existingPlace.setPlaceType(updatedPlace.getPlaceType());
        }

        if (updatedPlace.getLocation() != null) {
            existingPlace.setLocation(updatedPlace.getLocation());
        }

        placeRepository.update(existingPlace);

        return Response.ok().entity(existingPlace).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePlace(@PathParam("id") Long id) {
        placeRepository.deleteById(id);
        return Response.noContent().build();
    }
}
