/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.platform.Service;

import edu.neu.coe.platform.service.platform.Response;
import edu.neu.coe.platform.service.platform.workrequest.WorkRequest;

/**
 *
 * @author Cynthia
 */
public interface IService {
    
    /**
     * Method to pull work request and return response
     * @param request
     * @return 
     */
      public Response takeRequest(WorkRequest request);
    
}
