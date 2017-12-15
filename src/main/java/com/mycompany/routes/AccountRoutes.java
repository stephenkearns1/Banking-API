

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.routes;
import com.mycompany.exceptions.NotFoundException;
import com.mycompany.services.AccountService;
import com.mycompany.models.Account;
import com.mycompany.models.Transaction;
import java.util.List;
import javax.ws.rs.Consumes;
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
    
    //Lodgement
    
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/lodgement/{accountId}/{cardNum}/{amount}")
    public Response Lodgement(
            @PathParam("accountId") int id1,
            @PathParam("cardNum") String id2,
            @PathParam("amount") double amount) {
        Transaction trans = service.Lodgement(id1, id2, amount);
        if(trans != null){
    return Response.status(Response.Status.OK).entity(trans).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
        }
     
    }
    
     //Withdrawal
    
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/withdrawal/{accountId}/{cardNum}/{amount}")
    public Response Withdrawal(
            @PathParam("accountId") int id1,
            @PathParam("cardNum") String id2,
            @PathParam("amount") double amount) {
        Transaction trans = service.Withdraw(id1, id2, amount);
        if(trans != null){
    return Response.status(Response.Status.OK).entity(trans).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request!").build();
        }
}
    
      //Transer
    
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/transfer/{accountFrom}/{accountTo}/{amount}")
    public Response Transfer(
            @PathParam("accountFrom") int id1,
            @PathParam("accountTo") String id2,
            @PathParam("amount") double amount) {
      String trans = service.Transfer(id1, id1, amount);
        if(trans != null){
    return Response.status(Response.Status.OK).entity(trans).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
        }
}
}