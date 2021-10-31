package com.datastax.webstore;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.stream.Collectors;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@ApplicationScoped
public class InventoryResource {


    @Inject InventoryRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Inventory> readAll() {
        List<Inventory> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Inventory> read(@PathParam("id") UUID id) {
        return repository.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Inventory create(Inventory body) {
        return repository.insert(body);
    }

    @PUT
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Inventory update(@PathParam("id") UUID id, Inventory body) {
        return repository.update(body);
    }

    @DELETE
    @Path("/{id}/")
    @Transactional
    public void delete(@PathParam("id") UUID id) {
        repository.deleteById(id);
    }

    @DELETE
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    
    
}
