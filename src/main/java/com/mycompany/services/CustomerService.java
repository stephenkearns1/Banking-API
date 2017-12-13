package com.mycompany.services;

import com.mycompany.exceptions.NotFoundException;
import com.mycompany.models.Customer;
import com.mycompany.storage.DBPresistance;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Stephen Kearns
 * For add account, get the customer first using the customer class then add then account
 * To the customer and then add the customer to the account 
 */
public class CustomerService {
    DBPresistance presistance;
    public CustomerService(){
        presistance = new DBPresistance();
    }
    
    public List getCustomers(){
        return null;
    }
    
    public Customer getCustomer(int id){
        Customer customer = (Customer) presistance.Find(Customer.class, id);
        return customer;
    }
    
    public Customer getCustomer(Customer c){
        Customer customer = (Customer) presistance.Find(Customer.class, c);
        return customer;
    }
    
    public String CreateCustomer(Customer c){
        if(!CustomerAlreadyExists(c)){
             //Acquire a connection, even tho a new instance will be created on each request 
             presistance.OpenEntityManagerInstance();
             presistance.Begin();
             presistance.Presist(c);
             presistance.Commit();
             
             /* Close the connection unless, pooling is implemented */
             presistance.Close();
             return "Customer Created";
        }
       else{
           return "Customer already exists";
       }
       
    }
    
    public Customer EditCustomer(int custId, Customer c){
        Customer customer = getCustomer(custId);
        if(customer != null){
            presistance.Begin();
            customer.setAddress(c.getAddress());
            customer.setEmail(c.getEmail());
            customer.setFname(c.getFname());
            customer.setSname(c.getSname());
            customer.setSecurityQ(c.getSecurityQ());
            customer.setSecurityAns(c.getSecurityAns());
            
            presistance.Commit();
            presistance.Close();
        }
        
        return customer;
    }
    
    public boolean DeleteCustomer(int custId){
        Customer customer = getCustomer(custId);
        boolean deleted = false;
        if(customer != null){
            presistance.Begin();
            presistance.Remove(customer);
            presistance.Commit();
            presistance.Close();
            deleted = true;
            return deleted;
        }
        
        return deleted;
    }
    /*
    private boolean CustomerAlreadyExists(Customer c) {
        boolean exists = false;
        Customer customer = (Customer) presistance.Find(Customer.class, c);
        if(customer != null){
            return exists = true;
        }
        
        return exists;
    }
    */
    
    private boolean CustomerAlreadyExists(Customer c) {
        boolean exists = false;
        
        
        try {
            
            CriteriaBuilder criteriaBuilder = presistance.GetCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> from = criteriaQuery.from(Customer.class);
            criteriaQuery.select(from);
            criteriaQuery.where(criteriaBuilder.equal(from.get("email"), criteriaBuilder.parameter(String.class, "email")));
            TypedQuery<Customer> typedQuery = presistance.getEntityManager().createQuery(criteriaQuery);
            typedQuery.setParameter("email", c.getEmail());
            Customer customer = typedQuery.getSingleResult();
        }catch(final NoResultException exc) {
            return exists;
        }
        
        return exists = true;
    }
   
}
