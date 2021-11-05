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

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@ApplicationScoped
public class ProductsResource {


    @Inject ProductsRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> readAll() {
        List<Products> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Products> read(@PathParam("id") String id) {
        return repository.findByItemId(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Products create(Products body) {
        return repository.insert(body);
    }

    @PUT
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Products update(@PathParam("id") String id, Products body) {
        return repository.update(body);
    }

    @DELETE
    @Path("/{id}/")
    @Transactional
    public void delete(@PathParam("id") String id) {
        repository.deleteByItemId(id);
    }

    @DELETE
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    
    
}
