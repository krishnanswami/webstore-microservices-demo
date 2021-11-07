package com.datastax.webstore;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient
public interface ShoppingDocumentService {

    @GET
    @Path("/v2/namespaces/{namespace-id}/collections/{collection-id}/{document-id}")
    @Produces("application/json")
    ShoppingCartDocument get(@HeaderParam("X-Cassandra-Token") String token,  @PathParam("namespace-id") String namespace,  @PathParam("collection-id") String collection,  @PathParam("document-id") String documentid);

    @PUT
    @Path("/v2/namespaces/{namespace-id}/collections/{collection-id}/{document-id}")
    @Produces("application/json")
    Response put(@HeaderParam("X-Cassandra-Token") String token ,@PathParam("namespace-id") String namespace,  @PathParam("collection-id") String collection, @PathParam("document-id")  String documentid, ShoppingCart shoppingCart);
 
}
