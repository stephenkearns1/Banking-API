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
