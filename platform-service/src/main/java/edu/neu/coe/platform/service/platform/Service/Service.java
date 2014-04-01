/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.platform.Service;

import edu.neu.coe.platform.service.platform.Response;
import edu.neu.coe.platform.service.platform.workrequest.WorkQueue;
import edu.neu.coe.platform.service.platform.workrequest.WorkRequest;

/**
 *
 * @author Cynthia
 */
public abstract class Service implements IService{
    protected WorkQueue workqueue=new WorkQueue();
   /**
    * Method to pull work request and return response
    * @param request
    * @return 
    */
    @Override
    public Response takeRequest(WorkRequest request){
        workqueue.addWorkRequest(request.getSessionid(), request);
        return excuteRequest(request.getSessionid());
    }
    
    /**
     * Method to execute request and return response
     * @param sessionid
     * @return 
     */
    public abstract Response excuteRequest(String sessionid);

   

    public Service() {
    }
    
    
}
