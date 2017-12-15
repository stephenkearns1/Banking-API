package com.mycompany.storage;

import com.mycompany.models.Account;
import com.mycompany.models.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Stephen Kearns
 * @version 1.0
 * @Referances: 
 *      - https://www.tutorialspoint.com/jpa/jpa_criteria_api.htm
 */
public class DBPresistance {
    private EntityManagerFactory emFactory;
    private EntityManager entityManager;
    private EntityTransaction emTransaction;
    
    public DBPresistance(){
        emFactory = Persistence.createEntityManagerFactory("bank-connection");
        entityManager = emFactory.createEntityManager();
        emTransaction = entityManager.getTransaction();
    }
    
    public void OpenEntityManagerInstance(){
        if(!(entityManager.isOpen())){
            entityManager = emFactory.createEntityManager();
            emTransaction = entityManager.getTransaction();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public void Presist(Object obj){
       entityManager.persist(obj);
    }
    
    public void Commit(){
        emTransaction.commit();
    } 
    
    public void Begin(){
        emTransaction.begin();
    }
    
    public void Remove(Object obj){
        entityManager.remove(obj);
    }

    public void Rollback(){
        emTransaction.rollback();
    } 
    
    public void Close(){
        entityManager.close();
    }
    
    public Object Find(Class entity, int key){
        return entityManager.find(entity, key);
    }
    
    /*
      Error thrown here, due to the EntityManager.find() method expecting an primary key to locate a customer 
      @Doc: https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html
    */
    public Object Find(Class entity, Customer c){
        /*
        * TODO: Replace the following with a query to locate a specific Customer
        * Based on a number of properties such as Customer email
        */ 
        return entityManager.find(entity, c);
    }
    
    /*
     * Used
    */
    
    public CriteriaBuilder GetCriteriaBuilder(){
        return entityManager.getCriteriaBuilder();
    }

    public void refresh(Object acc) {
        entityManager.refresh(acc);
    }
    
    
}
