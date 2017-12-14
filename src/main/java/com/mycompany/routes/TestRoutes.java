/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.routes;

import com.mycompany.exceptions.NotFoundException;
import com.mycompany.models.Account;
import com.mycompany.models.Transaction;
import com.mycompany.services.AccountService;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
 * @author Stephen Kearns
 */

@Path("/test")
public class TestRoutes {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Testing");
        private EntityManager em = emf.createEntityManager();
        private EntityTransaction tr = em.getTransaction(); 
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


    @GET
    @Path("/{cust_id}/list")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> getAccounts(@PathParam("cust_id") int cust_id){
        return service.getAccounts(cust_id);
    }

    
    //This is for transactions with the initial account we need to manipulate 
    public List<Account> OneAccount(int accountId) {
        Query query = em.createQuery("Select Account from Account where ID = " + accountId + "");
        List<Account> account = query.getResultList();
        return account;
    }
    
    //Lodgement
    
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/lodgement/{amount}/{card}")
    public Account Lodgement(
            @PathParam("amount") double amount,
            @PathParam("card") String card,
            Account b) {
       List<Account> account = OneAccount(b.getAccountId());
        if (account != null) {
            
           // b = account.set(0.0, b);
            double balance = b.getBalance();
            double bal = balance + amount;
            b.setBalance(bal);
            Date date = new Date();

            Transaction tran1 = new Transaction();
            tran1.setTransID(tran1.getTransID());
            tran1.setType("Credit");
            tran1.setCard(card);
            tran1.setDescription("lodgement of " + amount + " to account");
            tran1.setAmount(amount);
            tran1.setnBalance(bal);
            tran1.setDate(date);

            tr.begin();
            em.persist(tran1);
            b.setAccountId(b.getAccountId());
            b.getTransactions().add(tran1);
            em.merge(b);
            tr.commit();
            em.close();

        }

        return b;
    }
    
    //Withdrawal
    
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/withdrawal/{amount}/{card}")
   public Account Withdrawal(
            @PathParam("amount") double amount,
            @PathParam("card") String card,
            Account b) {
       
            List<Account> account = OneAccount(b.getAccountId());
            
        if (account != null) {
          // b = account.set(0.0, b);
            double balance = b.getBalance();
            double bal = balance - amount;
            b.setBalance(bal);
            Date date = new Date();

            Transaction tran1 = new Transaction();
            tran1.setTransID(tran1.getTransID());
            tran1.setType("Debit");
            tran1.setDate(date);
            tran1.setDescription("withdrawing: " + amount + "!");
            tran1.setAmount(amount);
            tran1.setnBalance(bal);
            tran1.setCard(card);
         
            tr.begin();
            em.persist(tran1);
            b.setAccountId(b.getAccountId());
            b.getTransactions().add(tran1);
            em.merge(b);
            tr.commit();
            em.close();

        }

        return b;
    }
    
   //Transfer
   
   @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/Transfer/{ida}/{idb}/{amount}")
   public Account Transfer(
            @PathParam("ida") int id1,
            @PathParam("idb") int id2,
            @PathParam("amount") int amount,
            Account b) {
     
       
            List<Account>account1 = OneAccount(id1);
            List<Account> account2 = OneAccount(id2);
            
        if (account1 != null && account2 != null) {
              
         Account a = null;
        
           a = account1.set(0, a);
          
           b = account2.set(0, b);
           
            double balance = a.getBalance();
            double bal = balance - amount;
            a.setBalance(bal);
            
            double balance2 = b.getBalance();
            double bal2 = balance2 + amount;
            b.setBalance(bal2);
            
            
            Date date = new Date();
            Transaction tran1 = new Transaction();
            tran1.setTransID(tran1.getTransID());
            tran1.setType("Transfer");
            tran1.setDate(date);
            tran1.setDescription("Transfer of " + amount + "");
            tran1.setAmount(amount);
            tran1.setnBalance(bal);
            
            Transaction tran2 = new Transaction();
            tran2.setType("Transfer");
            tran2.setDate(date);
            tran2.setDescription("Transfer of " + amount + "");
            tran1.setAmount(amount);
            tran1.setnBalance(bal2);;
            
            
            tr.begin();
            em.persist(tran1);
            a.getTransactions().add(tran1);
            em.merge(a);
            em.persist(tran2);
            b.getTransactions().add(tran2);
            em.merge(b);
            tr.commit();
            em.close();

        }

        return b;

    }
    
}
