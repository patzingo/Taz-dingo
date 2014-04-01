/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.platform.workrequest;

import edu.neu.coe.platform.service.platform.Request;

/**
 *
 * @author Cynthia
 */
public abstract class WorkRequest {
    
    private Request request;
    
    public WorkRequest(Request request){
        this.request=request;
    }
     
    public Request getRequest() {
        return request;
    }
    
    public abstract String getSessionid();
}
