/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.serviceserver.GeneralServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cynthia
 */
public interface IServer {
    
/**
 * Method to take HttpServletRequest and HttpServletResponse and return HeepServletResponse
 * @param httprequest
 * @param response
 * @return 
 */    
    
    public HttpServletResponse takeRequest(HttpServletRequest httprequest,HttpServletResponse response);
    /**
     * Method to authenticate service with platform
     * @return 
     */
    
    public String serverLogin();
    /**
     * Method to authorize service with platform
     * @return 
     */
    
    public String serverAuthorization();
    
}
