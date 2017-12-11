/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.storage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Stephen Kearns
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
    
    public void Presist(Object obj){
       entityManager.persist(obj);
    }
    
    public void Commit(){
        emTransaction.commit();
    } 
    
    public void Begin(){
        emTransaction.begin();
    }
    
    public void Rollback(){
        emTransaction.rollback();
    } 
    
    public void Close(){
        entityManager.close();
        emFactory.close();
    }
    
    
    
    
    
}
