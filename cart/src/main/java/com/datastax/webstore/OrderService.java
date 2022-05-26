package com.datastax.webstore;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RegisterRestClient
public interface OrderService {

    @POST
    @Path("/v2/keyspaces/{keyspaceName}/{tableName}")
    @Produces("application/json")
    Response post(@HeaderParam("X-Cassandra-Token") String token ,@PathParam("keyspaceName") String keyspace, @PathParam("tableName") String tablename , String order);
    
}
