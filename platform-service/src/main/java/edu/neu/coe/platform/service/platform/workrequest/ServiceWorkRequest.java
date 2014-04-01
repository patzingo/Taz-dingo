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
public class ServiceWorkRequest extends WorkRequest{
    
    private String sessionid;
    private String tempkey;
    
    public ServiceWorkRequest(String sessionid,Request request){
        super(request);
        this.sessionid=sessionid;
    }

    @Override
    public String getSessionid() {
        return sessionid;
    }

    public String getTempkey() {
        return tempkey;
    }

    public void setTempkey(String tempkey) {
        this.tempkey = tempkey;
    }
    
    
    
    
    
}
