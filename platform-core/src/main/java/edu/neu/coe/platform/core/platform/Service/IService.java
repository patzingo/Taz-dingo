package edu.neu.coe.platform.core.platform.Service;

import edu.neu.coe.platform.core.platform.Response;
import edu.neu.coe.platform.core.platform.workrequest.WorkRequest;

/**
 *
 * @author Cynthia
 */
public interface IService {

    /**
     * Method to pull work request and return response
     *
     * @param request
     * @return
     */
    public Response takeRequest(WorkRequest request);
    
}
