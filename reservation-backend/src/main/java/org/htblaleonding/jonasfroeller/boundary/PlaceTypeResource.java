package org.htblaleonding.jonasfroeller.boundary;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.htblaleonding.jonasfroeller.model.PlaceType;
import org.htblaleonding.jonasfroeller.repository.PlaceTypeRepository;

import java.net.URI;
import java.util.List;

@Path("/places/types")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlaceTypeResource {

    @Inject
    PlaceTypeRepository placeTypeRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok(String.format("%s is alive!", PlaceTypeResource.class.getSimpleName())).build();
    }

    @GET
    @Path("")
    public List<PlaceType> getPlaceTypeList() {
        return placeTypeRepository.listAll(Sort.by("title"));
    }

    @GET
    @Path("{id}")
    public PlaceType getPlaceTypeById(@PathParam("id") Long id) {
        return placeTypeRepository.findById(id);
    }

    @POST
    @Transactional
    public Response createPlaceType(PlaceType placeType) {
        placeTypeRepository.persist(placeType);

        URI UriContext = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(placeType.id))
                .build();

        return Response.created(UriContext).entity(placeType).build();
    }

    @PUT
    @Transactional
    public Response replacePlaceType(PlaceType placeType) {
        placeTypeRepository.update(placeType);
        return Response.ok().entity(placeType).build();
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Response updatePlaceType(@PathParam("id") Long id, PlaceType updatedPlaceType) {
        PlaceType existingPlaceType = placeTypeRepository.findById(id);

        if (existingPlaceType == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("PlaceType not found").build();
        }

        if (updatedPlaceType.getTitle() != null) {
            existingPlaceType.setTitle(updatedPlaceType.getTitle());
        }

        if (updatedPlaceType.getDescription() != null) {
            existingPlaceType.setDescription(updatedPlaceType.getDescription());
        }

        placeTypeRepository.update(existingPlaceType);

        return Response.ok().entity(existingPlaceType).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePlaceType(@PathParam("id") Long id) {
        placeTypeRepository.deleteById(id);
        return Response.noContent().build();
    }
}
