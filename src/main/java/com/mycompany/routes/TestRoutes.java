/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.routes;

import com.mycompany.exceptions.NotFoundException;
import com.mycompany.models.Account;
import com.mycompany.services.AccountService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Stephen Kearns
 */

@Path("/test")
public class TestRoutes {
    AccountService service;
    public TestRoutes(){
        service = new AccountService();
    }
    
    @GET
    @Path("/hello")
    public Response Test() {
        return Response.status(Response.Status.OK).entity("Hello").build();
    }

    @POST
    @Path("{cust_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createAccount(@PathParam("cust_id") int cust_id) {
        service.CreateAccount(cust_id);
        return Response.status(Response.Status.CREATED).entity("Fuck you").build();
    }
    
    @GET
    @Path("/{accountId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAccount(@PathParam("accountId") int id) {
        Account account = service.getAccount(id);
        if (account != null) {
            return Response.status(Response.Status.OK).entity(account).build();
        } else {
            throw new NotFoundException("Account not Found");
        }
    }

    
@GET
@Path("/balance/{accountId}")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response getBalance(@PathParam("accountId") int id){
    Account account = service.getBalance(id);
    if(account != null){
    return Response.status(Response.Status.OK).entity(account).build();
    }
    else 
        throw new NotFoundException("Account not Found");
}
    
    @DELETE
 @Path("/{accountId}")
 @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteAccount(@PathParam("accountId") int id){
        boolean deleted = service.deleteAccount(id);
        if(deleted){
         return Response.status(Response.Status.OK).entity("Account Deleted").build();
        }
        
        return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
    }   




}
