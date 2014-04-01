/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.platform.workrequest;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cynthia
 */
public class WorkQueue {
    
    private Map<String,WorkRequest> workrequestmap;

    public WorkQueue(){
        workrequestmap=new HashMap<>();
    }
    
    public void addWorkRequest(String sessionid,WorkRequest workrequest){
        workrequestmap.put(sessionid, workrequest);
    }
    
    public WorkRequest pullWorkRequest(String sessionid){
       WorkRequest request= workrequestmap.get(sessionid);
       workrequestmap.remove(sessionid);
       return request;
        
    };
    
}
