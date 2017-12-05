/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Customer;
import java.util.List;

/**
 *
 * @author Stephen Kearns
 */
public class CustomerService {
    
    public CustomerService(){
        
    }
    
    public List getCustomers(){
        return null;
    }
    
    public Customer getCustomer(int id){
        return null;
    }
    
    public String CreateCustomer(Customer c){
        return "Created";
    }
    
    public Customer EditCustomer(int custId){
        
        return null;
    }
    
    public String DeleteCustomer(int custId){
        return "Deleted customer" + custId;
    }
}
