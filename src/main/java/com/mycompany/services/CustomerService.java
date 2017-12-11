/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Customer;
import com.mycompany.storage.DBPresistance;
import java.util.List;

/**
 *
 * @author Stephen Kearns
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
        return null;
    }
    
    public String CreateCustomer(Customer c){
        //Acquire a connection, even tho a new instance will be created on each request 
        presistance.OpenEntityManagerInstance();
        presistance.Begin();
        presistance.Presist(c);
        presistance.Commit();
        
        /* Close the connection unless, pooling is implemented */
        presistance.Close();
        return "Customer Created";
    }
    
    public Customer EditCustomer(int custId){
        
        return null;
    }
    
    public String DeleteCustomer(int custId){
        return "Deleted customer" + custId;
    }
}
