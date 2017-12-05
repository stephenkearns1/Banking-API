/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


/**
 *
 * @author Stephen
 */


@Path("/banking")
public class BankingService {
    
    @GET
    @Path("/test")
    public Response test(){
        return Response.status(Response.Status.OK).entity("Hello Bitch!").build();
    }
}
