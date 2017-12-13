package com.mycompany.routes;

import com.mycompany.exceptions.NotFoundException;
import com.mycompany.models.Transaction;
import com.mycompany.services.AccountService;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
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
 * @author Stephen, Barry & Daniel
 */
@Path("/transaction")
public class TransactionRoutes {

    private final AccountService service;

    public TransactionRoutes() {
        service = new AccountService();
    }

    @GET
    @Path("/test")
    public Response test() {
        return Response.status(Response.Status.OK).entity("Hello!").build();
    }

    @GET
    @Path("/{trans_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getTransaction(@PathParam("trans_id") int trans_id) {
        Transaction transaction = service.getTransactions(trans_id);
        if (transaction != null) {
            return Response.status(Response.Status.OK).entity(transaction).build();
        } else {
            throw new NotFoundException("Transactions not Found");
        }
    }

    @POST
    @Path("/{trans_type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response transaction(Transaction trans) {
       if(trans.getTransType().equalsIgnoreCase("Lodgement")){
            service.lodgement(trans.getAccountID(), trans.getAmount());
       }
       else if(trans.getTransType().equalsIgnoreCase("withdrawal")){
            service.withdrawal(trans.getAccountID(), trans.getAmount());
       }
       else if(trans.getTransType().equalsIgnoreCase("transfer")){
            service.transfer(trans.getAccountID_2(), trans.getAccountID(), trans.getAmount());
       }
        
        return Response.status(Response.Status.OK).entity("Your balance is now" +trans.getPost_bal()).build();

    }

}
