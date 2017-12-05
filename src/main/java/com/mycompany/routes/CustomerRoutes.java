/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.routes;

import com.mycompany.models.Customer;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Stephen Kearns
 */

@Path("/customer")
public class CustomerRoutes {
    
    @GET
    @Path("/test")
    public Response test(){
        return Response.status(Response.Status.OK).entity("Hello Bitch!").build();
    }
    
    @GET
    @Path("/{custId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCustomers(@PathParam("custId") int id){
        return null;
    }
    
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createCustomer(Customer c){
        return null;
    }
    
    @PUT
    @Path("/{custId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editCustomer(@PathParam("custId") int id){
        return null;
    }
    
    @DELETE
    @Path("/{custId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteCustomer(@PathParam("custId") int id){
        return null;
    }
    
    
    
    
         
}
