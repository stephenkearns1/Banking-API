package com.mycompany.routes;

import com.mycompany.exceptions.NotFoundException;
import com.mycompany.models.Customer;
import com.mycompany.services.CustomerService;
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
    private final CustomerService service;
    
    public CustomerRoutes(){
        service = new CustomerService();
    }

    @GET
    @Path("/test")
    public Response test(){
        return Response.status(Response.Status.OK).entity("Hello Bitch!").build();
    }
    
    @GET
    @Path("/{custId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCustomer(@PathParam("custId") int id){
        Customer customer = service.getCustomer(id);
        if(customer != null){
            return Response.status(Response.Status.OK).entity(customer).build();
        }
        else
           throw new NotFoundException("Customer not Found");
    }
    
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createCustomer(Customer c){
        System.out.println("FirstName: " + c.getFname() + " SecondName" + c.getSname());
        String response = service.CreateCustomer(c);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    
    @PUT
    @Path("/{custId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response editCustomer(@PathParam("custId") int id, Customer c){
        /* 
          * Temporary solution, customer would usually update parts of their information
          * Instead of updating everything at once 
        */
        Customer customer = service.EditCustomer(id, c);
        if(customer == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
        }
        
        return Response.status(Response.Status.OK).entity("Updated").build();
    }
    
    @DELETE
    @Path("/{custId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteCustomer(@PathParam("custId") int id){
        boolean deleted = service.DeleteCustomer(id);
        if(deleted){
         return Response.status(Response.Status.OK).entity("Customer Deleted").build();
        }
        
        return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
    }
    
    
    
    
         
}
