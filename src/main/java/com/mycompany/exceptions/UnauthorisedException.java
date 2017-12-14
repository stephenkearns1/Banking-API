/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Stephen Kearns 
 */
public class UnauthorisedException extends WebApplicationException {
    public UnauthorisedException(String message) {
         super(Response.status(Response.Status.UNAUTHORIZED)
             .entity(message).type(MediaType.TEXT_PLAIN).build());
     }
}
