/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.routes;
import com.mycompany.exceptions.NotFoundException;
import com.mycompany.services.AccountService;
import com.mycompany.models.Account;
import java.util.List;
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
 * @author barry, Stephen
 */


@Path("/account")
public class AccountRoutes {

  AccountService service;

     

    
 
public AccountRoutes(){
    service = new AccountService();
}    

/*
Path to test
*/
 
@GET  
@Path("/test")
public Response test(){
     return Response.status(Response.Status.OK).entity("Danny Gil is a nerd").build();
}

/*
Path to get account details
*/

@GET
@Path("/{accountId}")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response getAccount(@PathParam("accountId") int id){
    Account account = service.getAccount(id);
    if(account != null){
    return Response.status(Response.Status.OK).entity(account).build();
    }
    else 
        throw new NotFoundException("Account not Found");
}
/*
Path to create an account
*/
@POST
@Path("{cust_id}")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response createAccount(@PathParam("cust_id") int cust_id){
   service.CreateAccount(cust_id);
   return Response.status(Response.Status.CREATED).entity("Fuck you").build();
}
    
  /*
Path to get balance of account
*/    

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

 /*
    Path to delete an account
 */ 
    
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
    
    
    @GET
    @Path("/{cust_id}/list")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> getAccounts(@PathParam("cust_id") int cust_id){
        return service.getAccounts(cust_id);
    }
}
